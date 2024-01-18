# ZoneValuator

La documentation est disponible dans le /doc/ du projet. \
Le front-end est disponible dans le /client/ du projet. \
Le back-end est disponible dans le /server/ du projet. \

## Installation

### Pour juste lancer le projet

#### Prérequis
- Docker

#### Lancement
⚠️Vérifier ligne 79 dans PdfServiceImp.java que le chemin vers le serveur de fichier est correct. \
```Java
MinioClient minioClient =
        MinioClient.builder()
                .endpoint(" http://minio:9000")
                .credentials("ROOT","Test_1234")
                .build();
```
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
📁zonevaluator/> cd client && npm install && npm run dev
```
Ceci va lancer le front-end. \
Front-end : http://localhost:4173

```bash
📁zonevaluator/> cd server && mvn spring-boot:run
```
Ceci va lancer le back-end. \
Back-end : http://localhost:8080