# Use a imagem base oficial do Maven para construir o projeto
FROM maven:3.8.4-openjdk-17 AS build

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo pom.xml e as dependências do projeto
COPY pom.xml .
COPY src ./src

# Execute o comando de build do Maven para criar o artefato
RUN mvn clean package -DskipTests

# Use a imagem base oficial do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o artefato gerado pelo Maven para o diretório de trabalho
COPY --from=build /app/target/vertical-logistica-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]