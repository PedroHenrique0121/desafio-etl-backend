# Use uma imagem do OpenJDK 11 como base
FROM  openjdk:11
# Copie os arquivos necessários para o diretório /app
FROM maven:3.6.3-jdk-11-slim AS build
COPY pom.xml /usr/src/app/
WORKDIR /usr/src/app
RUN mvn -B dependency:resolve
COPY src /usr/src/app/src
RUN mvn -B package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /usr/src/app/target/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]





