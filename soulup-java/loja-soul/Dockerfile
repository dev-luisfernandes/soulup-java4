# Usa a imagem oficial do Java (Eclipse Temurin) versão 21 em Alpine
FROM eclipse-temurin:21-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia todos os arquivos do projeto para dentro do container
COPY . ./

# Dá permissão de execução ao Maven Wrapper
RUN chmod +x mvnw

# Faz o build do projeto Spring Boot com Maven
# -DskipTests ignora os testes durante o deploy
RUN ./mvnw -B -DskipTests clean package

# Expõe a porta utilizada pela aplicação
EXPOSE 8080

# Inicia a aplicação Spring Boot
# O Spring Boot gera o JAR executável dentro da pasta target
CMD ["sh", "-c", "java -jar target/*.jar"]