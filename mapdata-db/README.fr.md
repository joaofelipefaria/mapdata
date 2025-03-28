# mapdata-db

## Vue d'ensemble
Le **mapdata-db** est un projet de gestion de base de données conçu pour gérer la création et la maintenance de la base de données pour l'application **mapdata**. Il fournit des opérations pour créer des tables et des objets associés, tels que des séquences. La configuration du projet permet de spécifier l'opération souhaitée directement dans le fichier **pom.xml**.

## Fonctionnalités
- Création de tables de base de données et d'objets associés (par exemple, séquences)
- Configuration facile des opérations de base de données via Maven
- Prise en charge de la configuration et de l'initialisation structurées de la base de données

## Utilisation
### Configuration des opérations
Les opérations pour gérer la base de données sont configurées dans le fichier **pom.xml**. Vous pouvez spécifier quelle opération exécuter en modifiant les paramètres pertinents.

### Exécution de la configuration de la base de données
Pour exécuter les opérations de base de données, utilisez la commande Maven suivante :
```sh
mvn clean install
```
Assurez-vous que les paramètres appropriés sont configurés dans le fichier **pom.xml** avant d'exécuter cette commande.

## Prérequis
- Java (version JDK compatible avec Maven)
- Maven
- Système de base de données compatible avec l'application **mapdata**