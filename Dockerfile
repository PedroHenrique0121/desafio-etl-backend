# Use uma imagem do OpenJDK 11 como base
FROM  openjdk:11
# Copie os arquivos necessários para o diretório /app
COPY . /app

# Configure o diretório de trabalho para /app
WORKDIR /app

# Execute o comando de construção do Maven para gerar o arquivo .jar
RUN ./mvnw package -DskipTests

# Expõe a porta em que a aplicação está ouvindo
EXPOSE 8080

ENTRYPOINT exec java  -jar target/app.jar





