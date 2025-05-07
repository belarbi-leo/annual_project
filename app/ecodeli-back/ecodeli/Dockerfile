FROM eclipse-temurin:21-jdk-alpine

# Installation Maven (nécessaire pour la construction)
RUN apk add --no-cache maven

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et les sources
COPY pom.xml .
COPY src ./src

# Construction du projet avec Maven
RUN mvn clean package -DskipTests

# Définir un volume pour les fichiers temporaires
VOLUME /tmp

# Exposer le port 8081 utilisé par l'application
EXPOSE 8081

# Définir la commande d'exécution de l'application
ENTRYPOINT ["java", "-jar", "/app/target/ecodeli-1.9.1.jar"]