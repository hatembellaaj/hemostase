# Architecture

```mermaid
flowchart TD
    X[(registre.xlsx<br/>copie, SHA-256)] --> W[workbook.py<br/>lecture seule + SourceRef]
    W --> P[privacy.py<br/>identifiant d'étude, table privée]
    P --> C[cases.py<br/>un dossier = une ligne]
    D[(dictionary/*.yaml<br/>champs + règles versionnés)] --> N
    C --> N[normalize.py<br/>déterministe]
    N --> R[rules.py<br/>règles actives]
    R --> B0{{B0 règles}}

    subgraph Agents [modes agentiques — même passage déterministe en entrée]
      CO[coordinateur<br/>politique ou LLM] -->|cellules non résolues| NA[agent normalisation]
      CO -->|narratif / nouvelle info| CA[agent cohérence]
      CA --> VA[agent vérification]
      VA -->|rejet corrigeable, 1 fois| CA
      CO -->|budget / pas de progrès| STOP[arrêt]
      T[tools.py<br/>outils déterministes] --- NA & CA & VA
    end
    R --> CO
    C -->|llm_view : sans identifiant,<br/>dates au mois, textes expurgés| Agents
    G[[garde-fou HQA_ALLOW_REGISTRY_DATA_TO_LLM]] -.-> Agents

    B0 --> E[exports.py<br/>désidentifiés + manifeste]
    Agents --> V[validate_* : preuve = source,<br/>règle active, pas de correction auto] --> E
    E --> H[review_app.py<br/>revue humaine chronométrée]
    E --> M[metrics.py<br/>opportunités, dénominateurs, bootstrap]
    A[(annotations A/B<br/>+ adjudication)] --> M
```

- **B1** agent unique avec boucle d'outils ; **B2** workflow fixe normalisation → cohérence → vérification
  sur toutes les cellules ; **B3** coordinateur adaptatif ; **B3-noverif** ablation sans vérificateur.
- Bornes : 2 normalisations, 1 révision, 6 appels et un budget de tokens par dossier, délai maximal, nombre limité
  de tentatives techniques, arrêt après absence de progrès. Une erreur technique ou une sortie invalide donne le
  statut `technical_failure`, jamais « aucune anomalie ».
- Les propositions sont des **actions de revue** ; le registre n'est jamais écrit.
