from datetime import datetime

from hqa.parsers import ParseFailure, date_bounds, duration_months_exact, is_unknown_marker, parse_code, parse_date, parse_duration, parse_number, parse_ratio_text
from hqa.schemas import Comparator, TemporalPrecision


def test_censored_value_keeps_operator_bound_unit():
    for raw in ["<1%", "< 1 %", "<1 %"]:
        p = parse_number(raw, "%")
        assert p.comparator == Comparator.LT and p.value == 1.0 and p.unit == "%"
    p = parse_number("<1", None)
    assert p.comparator == Comparator.LT and p.value == 1.0 and "unite_absente" in p.issues


def test_number_with_unit_and_decimal_comma():
    p = parse_number("1,5%", "%")
    assert p.value == 1.5 and p.unit == "%" and p.numeric_precision == 1 and p.comparator == Comparator.EQ
    assert isinstance(parse_number("beaucoup", "%"), ParseFailure)
    assert isinstance(parse_number(True, "%"), ParseFailure)


def test_year_only_is_not_turned_into_january_first():
    p = parse_date(2018)
    assert p.value == "2018" and p.temporal_precision == TemporalPrecision.YEAR
    p = parse_date("aout 2018")
    assert p.value == "2018-08" and p.temporal_precision == TemporalPrecision.MONTH
    p = parse_date(datetime(2024, 3, 16))
    assert p.value == "2024-03-16" and p.temporal_precision == TemporalPrecision.DAY
    assert "jour_egal_1_precision_peut_etre_mensuelle" in parse_date(datetime(2024, 3, 1)).issues
    assert parse_date("2022?").issues == ["marqueur_incertitude"]


def test_ambiguous_dates_are_failures():
    assert parse_date("1996 et 2014").reason == "multiple_values"
    assert isinstance(parse_date("23-24"), ParseFailure)
    lo, hi = date_bounds("2018-02")
    assert (lo.day, hi.day) == (1, 28)


def test_durations():
    assert parse_duration("1 an + 8 mois").value == {"years": 1, "months": 8, "days": 0}
    assert parse_duration("18 MOIS").value["months"] == 18
    assert parse_duration("1jour").value["days"] == 1
    assert duration_months_exact({"years": 1, "months": 8, "days": 0}) == 20
    assert duration_months_exact({"years": 0, "months": 0, "days": 1}) is None  # pas d'équivalence exacte inventée
    assert parse_duration("2").reason == "not_a_duration" or parse_duration(2).reason == "unit_missing"
    assert parse_duration(2).reason == "unit_missing"


def test_codes_undefined_and_multicode_ambiguity():
    assert parse_code(1, [1, 2]).value == 1
    assert "code_non_defini" in parse_code(7, [1, 2]).issues
    assert parse_code(12, [1, 2, 3, 4], multi=True).reason == "ambiguous_multicode"  # 12 : code 12 ou 1 et 2 ?
    assert parse_code("1 2", [1, 2], multi=True).value == [1, 2]
    assert parse_code("1 2", [1, 2]).reason == "multiple_values"
    assert parse_code("pas encore", [1, 2]).reason == "text_in_coded_field"


def test_unknown_markers_and_ratio():
    assert is_unknown_marker("???") and is_unknown_marker("NP") and not is_unknown_marker("0")
    r = parse_ratio_text("64/30")
    assert r.value == {"numerator": 64.0, "denominator": 30.0}
    assert isinstance(parse_ratio_text("32.2"), ParseFailure)
