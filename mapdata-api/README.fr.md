# MapData App

## Vue d'ensemble
L'**application MapData** est une application Angular qui sert d'interface utilisateur pour gérer les données cartographiques et leurs métadonnées. Elle consomme l'API RESTful `mapdata-api` pour effectuer des opérations CRUD sur les données et les métadonnées.

## Fonctionnalités
- Interface conviviale pour gérer les données cartographiques et les métadonnées.
- Opérations CRUD pour les données cartographiques et les métadonnées.
- Intégration avec l'API `mapdata-api`.
- Entièrement conteneurisé avec le support de Docker.

## Principales bibliothèques et frameworks
- **Angular** : Framework pour construire l'interface utilisateur.
- **RxJS** : Pour gérer les flux asynchrones.
- **Angular Forms** : Pour la gestion des formulaires.
- **Angular HttpClient** : Pour la communication avec l'API.
- **Bootstrap** (optionnel) : Pour le style.

## Points de terminaison consommés
L'application consomme les points de terminaison suivants de l'API `mapdata-api` :

### Données cartographiques
- **GET `/api/mapdata/all`** : Récupère toutes les données cartographiques.
- **GET `/api/mapdata/{id}`** : Récupère des données cartographiques spécifiques par ID.
- **POST `/api/mapdata`** : Crée de nouvelles données cartographiques.
- **PUT `/api/mapdata/{id}`** : Met à jour des données cartographiques existantes.
- **DELETE `/api/mapdata/{id}`** : Supprime des données cartographiques spécifiques.

### Métadonnées
- **GET `/api/mapdata/{id}/metadata`** : Récupère toutes les métadonnées associées à des données cartographiques spécifiques.
- **POST `/api/mapdata/{id}/metadata`** : Crée de nouvelles métadonnées associées à des données cartographiques spécifiques.
- **PUT `/api/mapdata/{id}/metadata/{metadataId}`** : Met à jour des métadonnées existantes.
- **DELETE `/api/mapdata/{id}/metadata/{metadataId}`** : Supprime des métadonnées spécifiques.

## Configuration Docker
Le projet inclut le support de Docker pour faciliter le déploiement.

### Fichiers Docker
- **`docker-compose.yml`** : Définit les services et configurations pour exécuter l'application dans un environnement conteneurisé.
- **`Dockerfile`** : Construit l'image Docker de l'application.

### Exécution avec Docker
1. Construisez l'image Docker :
   ```bash
   docker build -t mapdata-app .
   ```
2. Démarrez les services avec Docker Compose :
   ```bash
   docker-compose up -d
   ```
3. Accédez à l'application à l'adresse `http://localhost:<port>` (le port par défaut est défini dans le fichier `docker-compose.yml`).

### Arrêt des services
Pour arrêter les conteneurs en cours d'exécution :
```bash
docker-compose down
```

## Prérequis
- Node.js 16 ou supérieur.
- Angular CLI.
- Docker et Docker Compose.

## Comment construire et exécuter
1. Clonez le dépôt :
   ```bash
   git clone <repository-url>
   cd mapdata-app
   ```
2. Installez les dépendances :
   ```bash
   npm install
   ```
3. Exécutez l'application en mode développement :
   ```bash
   ng serve
   ```
4. Accédez à l'application à l'adresse `http://localhost:4200`.