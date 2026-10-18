#!/usr/bin/env python3
"""
Extrait un schéma JSON unique décrivant les 8 fiches à partir du projet JHipster d'origine.

Sources utilisées (dans le projet JHipster) :
  - .jhipster/<Entite>.json                       -> types, énumérations, validations
  - src/main/resources/config/liquibase/changelog -> noms de tables / colonnes réels
  - src/main/webapp/app/entities/<x>/update/*.html -> sections, ordre, libellés français,
                                                     libellés des options, champs conditionnels

Usage :
    python tools/extract_schema.py /chemin/vers/projet-jhipster  schema/entities.json
"""
from __future__ import annotations

import json
import re
import sys
import xml.etree.ElementTree as ET
from html.parser import HTMLParser
from pathlib import Path

ENTITIES = [
    # (Nom JHipster, slug d'URL, titre affiché, dossier Angular)
    ("FicheHemophilie", "hemophilie", "Hémophilie", "fiche-hemophilie"),
    ("FicheThrombopathie", "thrombopathie", "Thrombopathie", "fiche-thrombopathie"),
    ("FicheVonWillebrand", "von-willebrand", "Maladie de Willebrand", "fiche-von-willebrand"),
    ("FicheFacteurVII", "facteur-vii", "Déficit en facteur VII", "fiche-facteur-vii"),
    ("FicheFacteurVIII", "facteur-viii", "Déficit en facteur VIII", "fiche-facteur-viii"),
    ("FicheFacteurXI", "facteur-xi", "Déficit en facteur XI", "fiche-facteur-xi"),
    ("FicheFacteurXIII", "facteur-xiii", "Déficit en facteur XIII", "fiche-facteur-xiii"),
    ("FicheFacteurXVIII", "facteur-xviii", "Déficit en facteur XVIII", "fiche-facteur-xviii"),
]

TYPE_MAP = {
    "String": "string",
    "Integer": "integer",
    "Long": "integer",
    "Float": "float",
    "Double": "float",
    "BigDecimal": "float",
    "Boolean": "boolean",
    "LocalDate": "date",
    "Instant": "datetime",
    "ZonedDateTime": "datetime",
    "TextBlob": "text",
}

# Ajustements apportés au modèle JHipster d'origine.
# Champs de texte libre de la fiche hémophilie limités à 25 caractères dans JHipster : trop court
# pour le registre Excel (siège des hématomes, examens, interventions...). Portés à 255.
FIELD_OVERRIDES = {
    ("FicheHemophilie", n): {"maxLength": 255}
    for n in (
        "profession", "degreParenteConsanguins", "hematomeSuperficielSiege", "hemorragieVisceresExamen",
        "interventionChirurgicaleType", "causePriseEnCharge", "prophylaxieDoseFrequence",
        "typeTraitementSubstitutif", "posologieITI", "causeNonObservance", "chargeViraleVHC",
        "typeHandicap", "causeDeces",
    )
}

VOID_TAGS = {"input", "br", "img", "hr", "meta", "link", "col", "area", "base", "embed", "source", "track", "wbr"}


# --------------------------------------------------------------------------------------
# Liquibase
# --------------------------------------------------------------------------------------
def read_changelog_columns(changelog_dir: Path) -> dict[str, list[tuple[str, str]]]:
    ns = {"l": "http://www.liquibase.org/xml/ns/dbchangelog"}
    tables: dict[str, list[tuple[str, str]]] = {}
    for f in sorted(changelog_dir.glob("*.xml")):
        root = ET.parse(f).getroot()
        for ct in root.iter("{http://www.liquibase.org/xml/ns/dbchangelog}createTable"):
            cols = [(c.get("name"), c.get("type")) for c in ct.findall("l:column", ns)]
            tables[ct.get("tableName")] = cols
    return tables


def norm(s: str) -> str:
    return re.sub(r"[^a-z0-9]", "", s.lower())


def camel_to_snake(name: str) -> str:
    s = re.sub(r"(?<=[a-z0-9])([A-Z])", r"_\1", name)
    s = re.sub(r"(?<=[A-Z])([A-Z][a-z])", r"_\1", s)
    return s.lower()


# --------------------------------------------------------------------------------------
# Angular template parser
# --------------------------------------------------------------------------------------
INTERP_RE = re.compile(r"\{\{.*?\}\}", re.S)
CTRL_RE = re.compile(r"@let\s+(\w+)\s*=\s*([^;]+);|@(if|for|else\s+if|else|switch|case|default)\b|\}")


def balanced(text: str, start: int) -> tuple[str, int]:
    """Retourne le contenu entre parenthèses à partir de text[start] == '('."""
    depth = 0
    for i in range(start, len(text)):
        if text[i] == "(":
            depth += 1
        elif text[i] == ")":
            depth -= 1
            if depth == 0:
                return text[start + 1 : i], i + 1
    return text[start + 1 :], len(text)


def parse_option_dict(text: str) -> dict[str, str]:
    m = re.search(r"\{\s*(null\s*:\s*''\s*,)?(.*?)\}\s*\[", text, re.S)
    if not m:
        return {}
    out = {}
    for k, v in re.findall(r"(\w+)\s*:\s*'((?:[^'\\]|\\.)*)'", m.group(2)):
        out[k] = v.replace("\\'", "'")
    return out


def clean_text(s: str) -> str:
    s = INTERP_RE.sub(lambda m: m.group(0), s)
    return re.sub(r"\s+", " ", s).strip()


class TemplateParser(HTMLParser):
    def __init__(self) -> None:
        super().__init__(convert_charrefs=True)
        self.aliases: dict[str, str] = {}  # alias -> field (value)
        self.ctrl_stack: list[dict] = []  # blocs @if/@for
        self.tag_stack: list[tuple[str, str | None]] = []  # (tag, condition *ngIf)
        self.section = "Informations générales"
        self.sections: list[str] = []
        self.fields: list[dict] = []
        self.labels: dict[str, str] = {}  # id -> texte
        self.radio_labels: dict[str, str] = {}
        self.option_labels: dict[str, dict[str, str]] = {}
        self.multicheck: dict[str, list[str]] = {}
        self._in_accordion_btn = False
        self._btn_text = ""
        self._in_label: str | None = None
        self._label_text = ""
        self._label_free = False
        self._last_free_label = ""
        self._in_select: str | None = None
        self._in_option = False
        self._option_value: str | None = None
        self._option_text = ""
        self._pending_for_literal: list[str] | None = None
        self._last_label: tuple[str, int] = ("", -1)

    # ----- contrôles Angular (@if, @for, @let) dans les noeuds texte -----
    def handle_data(self, data: str) -> None:
        if self._in_accordion_btn:
            self._btn_text += data
        if self._in_label is not None:
            self._label_text += data
        if self._in_option:
            self._option_text += data
            return
        text = data
        # neutralise les interpolations {{ }} pour ne pas compter leurs accolades
        text = INTERP_RE.sub(lambda m: " " * len(m.group(0)), text)
        i = 0
        while i < len(text):
            m = CTRL_RE.search(text, i)
            if not m:
                break
            if m.group(1):  # @let
                self.register_alias(m.group(1), m.group(2))
                i = m.end()
                continue
            kw = m.group(3)
            if kw is None:  # '}'
                if self.ctrl_stack:
                    self.ctrl_stack.pop()
                i = m.end()
                continue
            j = m.end()
            expr = ""
            while j < len(text) and text[j].isspace():
                j += 1
            if j < len(text) and text[j] == "(":
                expr, j = balanced(text, j)
            k = text.find("{", j)
            if k == -1:
                i = j
                continue
            kw = re.sub(r"\s+", " ", kw)
            if kw == "if":
                self.ctrl_stack.append({"type": "if", "cond": self.resolve(expr)})
            elif kw == "else if":
                prev = self.ctrl_stack[-1]["cond"] if self.ctrl_stack else None
                self.ctrl_stack.append({"type": "if", "cond": self.resolve(expr), "not": prev})
            elif kw == "else":
                # le '}' précédent a déjà dépilé le @if : on ne peut pas le reconstituer, on garde visible
                self.ctrl_stack.append({"type": "else"})
            elif kw == "for":
                fm = re.match(r"\s*(\w+)\s+of\s+(.*?);", expr, re.S)
                entry = {"type": "for"}
                if fm:
                    var, src = fm.group(1), fm.group(2).strip()
                    if src.startswith("["):
                        try:
                            vals = json.loads(src.replace("'", '"'))
                        except Exception:
                            vals = None
                        if vals is not None:
                            entry.update(var=var, values=vals)
                self.ctrl_stack.append(entry)
            else:
                self.ctrl_stack.append({"type": kw})
            i = k + 1

    def register_alias(self, name: str, expr: str) -> None:
        m = re.search(r"editForm\.get\('(\w+)'\)", expr)
        if m:
            self.aliases[name] = m.group(1)

    def resolve(self, expr: str) -> str:
        """Remplace les accès Angular par des références $champ."""
        e = expr
        e = re.sub(r"editForm\.get\('(\w+)'\)\s*[?!]?\s*\.value", r"$\1", e)
        e = re.sub(r"editForm\.controls\.(\w+)\.value", r"$\1", e)

        def alias_sub(m: re.Match) -> str:
            name, prop = m.group(1), m.group(2)
            if name in self.aliases:
                return "$" + self.aliases[name]
            return m.group(0)

        e = re.sub(r"\b(\w+)\s*[?!]?\s*(\.value)", alias_sub, e)
        e = re.sub(r"(?<![$\w.'])\b([a-zA-Z_]\w*)\b(?!\s*[(:'])", lambda m: ("$" + self.aliases[m.group(1)]) if m.group(1) in self.aliases else m.group(0), e)
        return e.strip()

    # ----- balises -----
    def handle_starttag(self, tag: str, attrs_list) -> None:
        attrs = dict(attrs_list)
        cls = attrs.get("class", "") or ""
        ngif = attrs.get("*ngif")
        cond = self.resolve(ngif) if ngif else None
        if tag not in VOID_TAGS:
            self.tag_stack.append((tag, cond))

        if tag == "button" and "accordion-button" in cls:
            self._in_accordion_btn = True
            self._btn_text = ""
        elif tag == "label":
            self._in_label = attrs.get("for") or ""
            self._label_free = not attrs.get("for")
            self._label_text = ""
        elif tag == "select":
            fcn = self.fcn(attrs)
            if fcn:
                self._in_select = fcn
                self.add_field(fcn, "select", attrs, cond)
        elif tag == "option" and self._in_select:
            self._in_option = True
            self._option_text = ""
            self._option_value = attrs.get("value") if attrs.get("value") and "{" not in attrs.get("value", "") else None
        elif tag in ("input", "textarea"):
            fcn = self.fcn(attrs)
            if fcn:
                itype = (attrs.get("type") or ("textarea" if tag == "textarea" else "text")).lower()
                if itype == "radio":
                    val = attrs.get("[value]") or attrs.get("value") or ""
                    val = val.strip("'\"")
                    if attrs.get("id"):
                        self.radio_labels[attrs["id"]] = val
                    self.add_field(fcn, "radio", attrs, cond, radio_value=val)
                else:
                    self.add_field(fcn, itype, attrs, cond)
            elif (attrs.get("type") or "").lower() == "checkbox" and attrs.get("[checked]"):
                m = re.search(r"editForm\.get\('(\w+)'\)\?\.value\?\.includes", attrs["[checked]"])
                if m:
                    for blk in reversed(self.ctrl_stack):
                        if blk.get("type") == "for" and "values" in blk:
                            if m.group(1) not in self.multicheck:
                                self.multicheck[m.group(1)] = blk["values"]
                                self.add_field(m.group(1), "multicheck", {}, None)
                            break

    def handle_startendtag(self, tag, attrs):
        self.handle_starttag(tag, attrs)

    def handle_endtag(self, tag: str) -> None:
        if tag == "button" and self._in_accordion_btn:
            self._in_accordion_btn = False
            title = clean_text(self._btn_text)
            if title:
                self.section = title
                if title not in self.sections:
                    self.sections.append(title)
        elif tag == "label" and self._in_label is not None:
            txt = clean_text(self._label_text)
            if self._in_label:
                self.labels[self._in_label] = txt
            elif txt and "{{" not in txt:
                self._last_free_label = txt
            if txt and not (self._in_label and self._in_label in self.radio_labels):
                self._last_label = (txt, len(self.fields))
            self._in_label = None
        elif tag == "option" and self._in_option:
            self._in_option = False
            d = parse_option_dict(self._option_text)
            if d:
                self.option_labels.setdefault(self._in_select, {}).update(d)
            elif self._option_value:
                t = clean_text(self._option_text)
                if t:
                    self.option_labels.setdefault(self._in_select, {})[self._option_value] = t
        elif tag == "select":
            self._in_select = None
        if tag in VOID_TAGS:
            return
        # dépile jusqu'à la balise correspondante
        for idx in range(len(self.tag_stack) - 1, -1, -1):
            if self.tag_stack[idx][0] == tag:
                del self.tag_stack[idx:]
                break

    @staticmethod
    def fcn(attrs: dict) -> str | None:
        if attrs.get("formcontrolname"):
            return attrs["formcontrolname"]
        b = attrs.get("[formcontrolname]")
        if b:
            # 'cas' + i + 'Numero'  -> cas{{i}}Numero
            parts = [p.strip() for p in b.split("+")]
            out = ""
            for p in parts:
                if p.startswith("'"):
                    out += p.strip("'")
                else:
                    out += "{{" + p + "}}"
            return out
        return None

    def add_field(self, name: str, widget: str, attrs: dict, ngif_cond: str | None, radio_value: str | None = None) -> None:
        conds = [c for _, c in self.tag_stack if c]
        loop = None
        for blk in self.ctrl_stack:
            if blk.get("type") == "if" and blk.get("cond"):
                conds.append(blk["cond"])
            if blk.get("type") == "for" and "values" in blk and all(isinstance(v, (int, float)) for v in blk["values"]):
                loop = (blk["var"], blk["values"])
        name = re.sub(r"\{\{\s*(\w+)\s*\}\}", r"{{\1}}", name)
        self.fields.append(
            {
                "name": name,
                "widget": widget,
                "id": attrs.get("id"),
                "section": self.section,
                "conds": conds,
                "loop": loop,
                "radio_value": radio_value,
                "free_label": self._last_free_label,
                "prev_label": self._last_label[0] if self._last_label[1] == len(self.fields) else "",
            }
        )


# --------------------------------------------------------------------------------------
# Conditions -> structure JSON
# --------------------------------------------------------------------------------------
ATOM_RE = re.compile(r"^\$(\w+)\s*(===|==|!==|!=|>=|<=|>|<)\s*(.+)$")


def parse_literal(v: str):
    v = v.strip()
    if v.startswith("'") and v.endswith("'"):
        return v[1:-1]
    if v in ("true", "false"):
        return v == "true"
    if v in ("null", "undefined"):
        return None
    try:
        return int(v)
    except ValueError:
        try:
            return float(v)
        except ValueError:
            return "__unresolved__"


OPS = {"===": "eq", "==": "eq", "!==": "ne", "!=": "ne", ">=": "gte", "<=": "lte", ">": "gt", "<": "lt"}


def to_condition(expr: str, known: set[str], warn) -> dict | None:
    e = expr.strip()
    while e.startswith("(") and e.endswith(")") and balanced(e, 0)[1] == len(e):
        e = e[1:-1].strip()
    if "||" in e:
        parts = [to_condition(p, known, warn) for p in split_top(e, "||")]
        parts = [p for p in parts if p]
        return {"any": parts} if parts else None
    if "&&" in e:
        parts = [to_condition(p, known, warn) for p in split_top(e, "&&")]
        parts = [p for p in parts if p]
        return {"all": parts} if parts else None
    m = ATOM_RE.match(e)
    if m:
        field, op, lit = m.group(1), OPS[m.group(2)], parse_literal(m.group(3))
        if field not in known or lit == "__unresolved__":
            warn(f"condition ignorée: {expr}")
            return None
        if op == "ne" and lit is None:
            return {"field": field, "op": "filled"}
        return {"field": field, "op": op, "value": lit}
    m = re.match(r"^(!)?\s*\$(\w+)$", e)
    if m:
        field = m.group(2)
        if field not in known:
            warn(f"condition ignorée: {expr}")
            return None
        return {"field": field, "op": "falsy" if m.group(1) else "truthy"}
    warn(f"condition ignorée: {expr}")
    return None


def split_top(e: str, sep: str) -> list[str]:
    out, depth, cur, i = [], 0, "", 0
    while i < len(e):
        if e[i] == "(":
            depth += 1
        elif e[i] == ")":
            depth -= 1
        if depth == 0 and e.startswith(sep, i):
            out.append(cur)
            cur = ""
            i += len(sep)
            continue
        cur += e[i]
        i += 1
    out.append(cur)
    return out


def humanize(name: str) -> str:
    s = re.sub(r"(?<=[a-z])([A-Z0-9])", r" \1", name)
    return s[:1].upper() + s[1:].lower()


# --------------------------------------------------------------------------------------
# Construction du schéma
# --------------------------------------------------------------------------------------
def build(project: Path) -> dict:
    tables = read_changelog_columns(project / "src/main/resources/config/liquibase/changelog")
    entities = []
    report = []
    for jh_name, slug, title, ng_dir in ENTITIES:
        warns: list[str] = []
        warn = warns.append
        meta = json.loads((project / ".jhipster" / f"{jh_name}.json").read_text(encoding="utf-8"))
        table = camel_to_snake(jh_name).replace("_v_i_i_i", "_viii")
        # nom de table réel : on cherche dans les changelogs
        cand = [t for t in tables if norm(t) == norm(jh_name)]
        table = cand[0] if cand else table
        columns = {norm(c): (c, t) for c, t in tables.get(table, [])}

        json_fields = {}
        for f in meta["fields"]:
            name = f["fieldName"]
            enum = f.get("fieldValues")
            ftype = "enum" if enum else TYPE_MAP.get(f["fieldType"], "string")
            rules = f.get("fieldValidateRules", [])
            col = columns.get(norm(name))
            if not col:
                warn(f"colonne introuvable pour {name}")
                colname, coltype = camel_to_snake(name), None
            else:
                colname, coltype = col
            fd = {
                "name": name,
                "column": colname,
                "type": ftype,
                "required": "required" in rules,
            }
            if enum:
                fd["enumName"] = f["fieldType"]
                fd["options"] = [{"value": v.strip(), "label": v.strip()} for v in enum.split(",")]
            if "maxlength" in rules:
                fd["maxLength"] = int(f["fieldValidateRulesMaxlength"])
            elif ftype == "string":
                m = re.search(r"varchar\((\d+)\)", coltype or "")
                fd["maxLength"] = int(m.group(1)) if m else 255
            if "min" in rules:
                fd["min"] = float(f["fieldValidateRulesMin"])
            if "max" in rules:
                fd["max"] = float(f["fieldValidateRulesMax"])
            fd.update(FIELD_OVERRIDES.get((jh_name, name), {}))
            json_fields[name] = fd

        known = set(json_fields)
        html = (project / f"src/main/webapp/app/entities/{ng_dir}/update/{ng_dir}-update.component.html").read_text(encoding="utf-8")
        p = TemplateParser()
        p.feed(html)

        ordered: list[dict] = []
        seen: set[str] = set()
        for occ in p.fields:
            expansions = [(None, None)]
            if occ["loop"] and "{{" in occ["name"]:
                var, values = occ["loop"]
                expansions = [(var, v) for v in values]
            for var, val in expansions:
                name = occ["name"]
                conds = list(occ["conds"])
                fid = occ["id"] or ""
                if var:
                    name = name.replace("{{" + var + "}}", str(val))
                    fid = fid.replace("{{" + var + "}}", str(val))
                    conds = [re.sub(rf"\b{var}\b", str(val), c) for c in conds]
                if name == "id" or name not in json_fields:
                    if name != "id":
                        warn(f"champ du formulaire absent du modèle: {name}")
                    continue
                fd = json_fields[name]
                if occ["widget"] == "radio":
                    if occ["id"] and occ["id"] in p.labels and occ["radio_value"]:
                        lbl = p.labels[occ["id"]]
                        for o in fd.get("options", []):
                            if o["value"] == occ["radio_value"] and lbl:
                                o["label"] = lbl
                    fd["widget"] = "radio"
                if name in seen:
                    continue
                seen.add(name)
                if occ["widget"] == "radio":
                    label = p.labels.get(f"field_{name}") or occ["free_label"]
                else:
                    label = (
                        p.labels.get(fid)
                        or p.labels.get(occ["id"] or "")
                        or p.labels.get(f"field_{name}")
                        or p.labels.get(f"field_{occ['name']}")
                        or occ["prev_label"]
                    )
                fd["label"] = label or humanize(name)
                if var:
                    fd["label"] = fd["label"].replace("{{" + var + "}}", str(val))
                fd["section"] = occ["section"]
                parsed = [c for c in (to_condition(c, known, warn) for c in conds) if c]
                if parsed:
                    fd["visibleIf"] = parsed[0] if len(parsed) == 1 else {"all": parsed}
                if occ["widget"] == "textarea":
                    fd["widget"] = "textarea"
                ordered.append(fd)

        for name, labels in p.option_labels.items():
            if name in json_fields:
                for o in json_fields[name].get("options", []):
                    if labels.get(o["value"]):
                        o["label"] = labels[o["value"]]
        for name, values in p.multicheck.items():
            if name in json_fields:
                json_fields[name]["widget"] = "multicheck"
                json_fields[name]["choices"] = values
                # plusieurs articulations séparées par des virgules : 25 caractères ne suffisent pas
                json_fields[name]["maxLength"] = 255

        missing = [f for n, f in json_fields.items() if n not in seen]
        for fd in missing:
            fd["label"] = humanize(fd["name"])
            fd["section"] = "Autres informations"
            ordered.append(fd)

        sections = []
        for fd in ordered:
            if fd["section"] not in sections:
                sections.append(fd["section"])

        computed = []
        if "ageActuel" in json_fields and "dateNaissance" in json_fields:
            computed.append({"field": "ageActuel", "fn": "age", "args": ["dateNaissance"]})
        if {"ratioFvwactFvwag", "fvwact", "fvwag"} <= known:
            computed.append({"field": "ratioFvwactFvwag", "fn": "ratio", "args": ["fvwact", "fvwag"]})
        if {"ratioF8cFvwag", "f8c", "fvwag"} <= known:
            computed.append({"field": "ratioF8cFvwag", "fn": "ratio", "args": ["f8c", "fvwag"]})

        list_cols = [c for c in ["dossierNumber", "nom", "prenom", "sexe", "dateNaissance", "ageActuel", "origine", "serviceClinique"] if c in known]

        entities.append(
            {
                "name": jh_name,
                "slug": slug,
                "title": title,
                "table": table,
                "sections": sections,
                "listColumns": list_cols,
                "computed": computed,
                "fields": ordered,
            }
        )
        report.append(
            f"{jh_name}: {len(ordered)} champs, {len(sections)} sections, "
            f"{sum(1 for f in ordered if 'visibleIf' in f)} conditionnels, {len(missing)} hors formulaire, {len(warns)} avertissements"
        )
        for w in warns:
            report.append("   - " + w)
    return {"entities": entities}, report


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print(__doc__)
        sys.exit(1)
    schema, report = build(Path(sys.argv[1]))
    out = Path(sys.argv[2])
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(json.dumps(schema, ensure_ascii=False, indent=1), encoding="utf-8")
    print("\n".join(report))
    print(f"-> {out}")
