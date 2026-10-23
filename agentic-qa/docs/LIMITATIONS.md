# Limites

1. **Aucun résultat LLM réel.** Les modes B1–B3 n'ont tourné qu'avec un faux modèle étiqueté `[SIMULÉ]`.
   Aucune conclusion sur l'apport des agents n'est possible à ce stade.
2. **Aucune référence experte.** Les 43 signalements B0 sur le registre réel sont des candidats ; sans annotation
   on ne peut parler ni de précision ni de rappel.
3. **Dictionnaire hypothétique.** Toutes les correspondances de codes (ex. 1/2, 1/2/3) sont des hypothèses ;
   elles ne sont pas supposées identiques entre colonnes. Les règles qui en dépendent sont inactives.
4. **Seuils médicaux.** Les seuils ISTH de sévérité et le seuil de répondeur (≥ 5 UB) sont cités avec référence
   mais restent `proposed_reference` tant qu'un expert ne les a pas validés pour ce registre (unité des taux non confirmée :
   une valeur 0,12 suggère des UI/ml plutôt que des %).
5. **Date de référence de l'âge inconnue.** DDN n'est pas cette date (écarts de +1 à +2 ans) ; la règle âge/naissance
   n'est pas évaluable.
6. **Petit échantillon** (83 lignes, 58 en test) : intervalles larges ; un seul centre ; généralisation limitée.
7. **Banc artificiel** : les perturbations sont choisies par les auteurs ; leurs résultats valident le logiciel,
   pas la fréquence ni la nature des erreurs naturelles.
8. **Pseudonymisation ≠ anonymisation.** Dates au mois, ville, texte libre et combinaisons rares peuvent permettre
   une réidentification ; 17 cellules signalent des identifiants résiduels possibles à revoir manuellement.
9. **Coordinateur par politique par défaut.** Le mode LLM du coordinateur existe mais n'est pas évalué ;
   l'apport du routage adaptatif doit être mesuré, pas présumé.
10. **Variabilité des modèles** : même à température 0, les sorties peuvent varier selon le serveur et la version ;
    le manifeste trace modèle, prompts et paramètres, mais la reproductibilité exacte n'est pas garantie.
11. **Effet de l'annotation** : l'échantillonnage stratifié des cellules réduit la charge mais aussi la puissance
    pour les familles A/C.
