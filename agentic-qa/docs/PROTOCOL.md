# Protocole expérimental (à figer avant toute exécution sur le test)

## 1. Données et unité d'observation
- Source : copie de `registre.xlsx` (empreinte dans chaque manifeste). 83 lignes renseignées, 165 colonnes.
- Unité d'observation **provisoire** : la ligne (« dossier »). Les 83 lignes ne sont pas supposées être 83 personnes :
  8 groupes partagent la même clé de nom (17 lignes) ; ils sont signalés (S04), **jamais fusionnés**, et placés
  dans le même sous-ensemble pour éviter une fuite dev/test.

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
- Deux experts indépendants (A, B), **en aveugle** des sorties des systèmes (écran « Annotation en aveugle »,
  un fichier par expert). Étiquettes : anomalie confirmée / variation acceptable / non applicable / non résolu ;
  valeur attendue (JSON) pour la normalisation.
- Accord inter-annotateurs : kappa de Cohen avant adjudication, rapporté par famille.
- Adjudication des désaccords par un troisième expert ou par consensus (écran « Adjudication »).
- « Non résolu » dans la référence est **exclu** des dénominateurs et rapporté séparément.
- Pilote recommandé : 5 dossiers dev annotés par les deux experts pour stabiliser les consignes.

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
