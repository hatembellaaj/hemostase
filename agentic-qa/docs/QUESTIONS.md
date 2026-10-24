# Informations à confirmer (à faire valider par l'équipe clinique)

Une fois confirmée, chaque réponse se reporte dans `dictionary/fields.yaml` (`status: confirmed`,
`approved_by`) ou dans `dictionary/rules.yaml` (`status: approved`).

## 1. Signification des codes
- Pour chaque colonne codée (sexe H, consanguinité J, cas familiaux L, forme clinique AK, sévérité AL,
  hémarthrose AO/AP/AQ/AR, circoncision CA, inhibiteur DU, répondeur DV…) : que valent 1, 2, 3 ? Le codage
  est-il identique d'une colonne à l'autre ?
- Une cellule vide signifie-t-elle « non », « non recherché », « inconnu » ou « non applicable » ?
- Valeurs textuelles rencontrées : « pas encore » (×5), « asymptomatique », « 2 » sans unité de durée.

## 2. Sens de DDN (colonne FF) — ✅ confirmé le 2026-10-24
- **DDN = date de dernière nouvelle.** Reste ouvert : que signifient les valeurs « 23-24 » (période ? année
  scolaire ?) — elles restent « non résolues ».

## 3. Unité d'observation — ✅ confirmé le 2026-10-24
- **Une ligne = un patient.** Les 8 groupes de même clé de nom (dates de naissance différentes) sont donc des
  personnes distinctes ; reste à savoir, pour l'interprétation des antécédents familiaux, s'il s'agit de fratries.

## 4. Unités des mesures — ✅ confirmé le 2026-10-24 (unités conventionnelles France / Tunisie)
- Facteurs VIII/IX en % (1 UI/dL = 1 %) ; titre d'inhibiteur en UB/mL ; TCA en secondes ; ratio sans unité.
- Reste ouvert : les valeurs décimales < 1 (ex. 0,12 avec sévérité 3) sont-elles bien des % ou une saisie en UI/mL ?
  Elles ne sont jamais converties automatiquement.
- Ratio TCA : troncature ou arrondi (49 cohérents par troncature, 6 divergences) ?

## 5. Dates de référence
- Date à laquelle l'âge (colonne F) a été calculé ou saisi (l'âge ≈ 2024 − année de naissance) ?
- Âge au diagnostic : texte (Y) ou mois (Z) fait-il foi ? (1 discordance « 11 ans » / 120)

## 6. Seuils et règles
- Validation des seuils ISTH (< 1 ; 1–5 ; > 5–< 40) et du seuil répondeur fort ≥ 5 UB pour ce registre.
- Validation des règles en hypothèse H01–H11.

## 7. Environnement LLM autorisé
- Le traitement des données pseudonymisées par un modèle est-il autorisé (comité d'éthique, DPO, autorisation
  de traitement) ? Modèle local uniquement, ou serveur hébergé dans l'établissement ? Aucune API externe d'ici là.
- Quel modèle et quelle infrastructure (GPU disponible) ?

## 8. Organisation de l'évaluation
- Deux experts pour l'annotation indépendante et un adjudicateur ; nombre de relecteurs pour la revue chronométrée.
- Accord sur l'échantillonnage stratifié (6 cellules par dossier en test).
