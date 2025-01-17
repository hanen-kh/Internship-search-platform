FROM maven as build
WORKDIR /app
COPY . .
RUN mvn install

# Utiliser une image de base OpenJDK
FROM openjdk:17
# Définir le répertoire de travail
WORKDIR /app
# Copier l'artefact généré (JAR) dans le conteneur
COPY target/where-0.0.1-SNAPSHOT.jar /app/where.jar
# Exposer le port sur lequel l'application écoute
EXPOSE 8000
# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/where.jar"]


