# Protocole expérimental (à figer avant toute exécution sur le test)

## 1. Données et unité d'observation
- Source : copie de `registre.xlsx` (empreinte dans chaque manifeste). 83 lignes renseignées, 165 colonnes.
- Unité d'observation **confirmée** (2026-10-24) : une ligne = un patient, soit 83 patients. Les 8 groupes de même
  clé de nom (17 lignes, dates de naissance différentes) sont des personnes distinctes, probablement des fratries :
  ils restent **jamais fusionnés** et placés dans le même sous-ensemble dev/test (antécédents familiaux partagés).
  S04 (v2) ne signale plus qu'une même clé de nom ET une même date de naissance (double saisie possible).
- Règles actives par défaut : structurelles (S01–S04, N01) et **H11 v3 approuvée** (âge = 2024 ou 2025 − année de
  naissance). Les autres règles H et K restent des hypothèses, en attente de la signification des codes.

## 2. Découpage
- `python -m hqa.cli split --freeze` : graine 20270101, 25 dossiers dev, 58 test. Le fichier figé ne peut plus être écrasé.
- **Dev** : mise au point des prompts, du dictionnaire et des règles. **Test** : exécuté une seule fois par
  configuration figée (version des prompts, du dictionnaire, du modèle, paramètres).

## 3. Opportunités (définies avant toute exécution)
- `NORM:<dossier>:<champ>` (A, normalisation), `UNC:<dossier>:<champ>` (C, incertitude),
  `COH:<dossier>:<règle>` (B, cohérence, pour chaque règle applicable, actives ou non), `identity`, `N01`.
- Test : toutes les opportunités B et identity ; échantillon stratifié et reproductible de 6 cellules par dossier
  pour A/C (`--sample-cells-per-case 6`, 1 289 opportunités au lieu de 3 117). L'échantillon est tiré avant
  l'exécution des systèmes.

## 4. Annotation de référence
- **Un seul expert disponible (2026-10-24).** Il annote **en aveugle** des sorties des systèmes (écran
  « Annotation en aveugle », annotateur A). Étiquettes : anomalie confirmée / variation acceptable /
  non applicable / non résolu ; valeur attendue (JSON) pour la normalisation. « Non résolu » est préféré à une
  étiquette incertaine.
- Reproductibilité : `python -m hqa.cli reannotation-sample --subset test --fraction 0.2` tire, avec une graine fixe,
  environ 20 % des dossiers ; l'expert les réannote comme A **au moins deux semaines plus tard**, dans un ordre
  mélangé et sans voir sa première annotation. Kappa intra-expert rapporté par famille.
- Référence : `python -m hqa.cli single-reference --subset test` (l'annotation devient la référence ;
  `reference_test.json` trace le dispositif et l'accord intra-expert, recopiés dans les métriques).
- Si un second annotateur devient disponible, même sur l'échantillon de réannotation, le dispositif à deux experts
  (annotateur B, kappa inter-annotateurs, écran « Adjudication ») reste utilisable sans changement.
- « Non résolu » dans la référence est **exclu** des dénominateurs et rapporté séparément.
- Pilote recommandé : 5 dossiers dev annotés par l'expert pour stabiliser les consignes avant le test.

## 5. Systèmes comparés
B0 règles ; B1 agent unique + outils ; B2 workflow fixe ; B3 multi-agent adaptatif ; B3-noverif.
Même modèle, même température (0), même graine, mêmes prompts versionnés, même passage déterministe.
Règles en hypothèse et seuils proposés **inactifs** sauf approbation experte enregistrée (`approved_by`).

## 6. Appariement alertes ↔ opportunités
Une alerte compte pour l'opportunité dont la famille, le dossier et la cible (champ ou règle) correspondent.
Une alerte hors opportunité est listée à part (ni TP ni FP), examinée qualitativement. Sorties simulées ou
rejetées par la validation : exclues. Échec technique : « sans décision », compté comme manqué dans le rappel global.

## 7. Métriques
Par famille et globalement, avec dénominateurs : TP, FP, FN, TN, précision, rappel (décidés), rappel global,
F1, couverture ; exactitude de normalisation (valeur, comparateur, unité, précision) ; taux d'abstention ;
coûts (appels, tokens, latence, erreurs, sorties non conformes). Intervalles : bootstrap **apparié au niveau du
dossier** (2 000 tirages, graine 7) pour chaque système et les différences entre systèmes. NA, jamais 0, sans référence.

## 8. Revue humaine chronométrée
Deux relecteurs, conditions B0 vs B3 (ou le meilleur système), affectation en carré latin
(`review-assignment`) pour équilibrer ordre et dossiers. Mesures : temps par dossier, décisions, corrections
autorisées (enregistrées seulement).

## 9. Expérience artificielle (séparée)
`synthetic` : données entièrement artificielles avec perturbations variation / injectée / indécidable et vérité
terrain. Sert à valider le logiciel et à tester la robustesse ; **ne pas** la présenter comme une estimation des
erreurs naturelles du registre.

## 10. Confidentialité
Avant tout envoi au modèle : identifiant d'étude, dates au mois, noms expurgés des narratifs, contrôle des
identifiants résiduels. Modèle local de préférence. Aucun envoi sans confirmation écrite de l'environnement.
