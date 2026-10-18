Déposez ici un dump SQL de l'ancienne base JHipster (ex. `01-adhes2025.sql`) :
MariaDB l'importe automatiquement au **premier** démarrage (volume `db_data` vide).
Le backend Python rend ensuite les colonnes `id` AUTO_INCREMENT ; les données sont conservées.
