# ZoneValuator

La documentation est disponible dans le /doc/ du projet. \
Le front-end est disponible dans le /client/ du projet. \
Le back-end est disponible dans le /server/ du projet. \

## Installation

⚠️ Il est nécessaire de déziper le fichier /server/src/main/resources/full.zip pour que le projet fonctionne correctement.


### Pour juste lancer le projet


#### Prérequis
- Docker

#### Lancement

```bash
📁zonevaluator/> docker-compose -f docker-compose-full.yml up
```
Ceci va lancer le front-end, le back-end et le serveur de fichier. \
Back-end : http://localhost:8080 \
Front-end : http://localhost:4173 \
Serveur de fichier : http://localhost:9001 (Login : ROOT, Password : Test_1234)


### Pour développer

#### Prérequis
- Docker
- NodeJS 20
- NPM
- Java 19

#### Lancement

```bash
📁zonevaluator/> docker-compose up
```
Ceci va lancer le serveur de fichier. \
Serveur de fichier : http://localhost:9001 (Login : ROOT, Password : Test_1234)

```bash
📁zonevaluator/> cd client/Zonevaluator && npm install && npm run dev
```
Ceci va lancer le front-end. \
Front-end : http://localhost:4173

```bash
📁zonevaluator/> cd server && mvn spring-boot:run
```
Ceci va lancer le back-end. \
Back-end : http://localhost:8080

### Problèmes connus

⚠️Vérifier ligne 36 dans PdfServiceImp.java que le chemin vers le serveur de fichier est correct. \
URL sous docker: http://minio:9000 \
URL en local: http://localhost:9000