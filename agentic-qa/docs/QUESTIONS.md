# Informations à confirmer (à faire valider par l'équipe clinique)

Une fois confirmée, chaque réponse se reporte dans `dictionary/fields.yaml` (`status: confirmed`,
`approved_by`) ou dans `dictionary/rules.yaml` (`status: approved`).

## 1. Signification des codes
- Pour chaque colonne codée (sexe H, consanguinité J, cas familiaux L, forme clinique AK, sévérité AL,
  hémarthrose AO/AP/AQ/AR, circoncision CA, inhibiteur DU, répondeur DV…) : que valent 1, 2, 3 ? Le codage
  est-il identique d'une colonne à l'autre ?
- Une cellule vide signifie-t-elle « non », « non recherché », « inconnu » ou « non applicable » ?
- Valeurs textuelles rencontrées : « pas encore » (×5), « asymptomatique », « 2 » sans unité de durée.

## 2. Sens de DDN (colonne FF)
- Date de dernière nouvelle ? de dernière consultation ? Elle ne correspond pas à la date de calcul de l'âge
  (écart +1 an dans 45 cas, +2 dans 13). Que signifient les valeurs « 23-24 » ?

## 3. Unité d'observation
- Une ligne = un patient, ou un patient peut-il avoir plusieurs lignes (épisodes, mises à jour) ?
- Les 8 groupes de lignes partageant la même clé de nom (dates de naissance différentes) sont-ils des
  homonymes, des frères, ou des doublons ?

## 4. Unités des mesures
- Facteurs VIII/IX : % ou UI/ml (ex. 0,12 avec sévérité 3) ?
- Titre d'inhibiteur : unités Bethesda ? (16 valeurs sans unité)
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
