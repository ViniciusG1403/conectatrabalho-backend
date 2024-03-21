# Especificar a plataforma é uma boa prática se você estiver construindo imagens multi-arquitetura,
# mas neste caso, como você está herdando de imagens públicas, pode não ser necessário.
ARG PLATFORM=linux/amd64

# A primeira instrução FROM é desnecessária se você não está usando a imagem do Ubuntu diretamente.
# FROM --platform=$PLATFORM ubuntu:22.04

# Você deve especificar a versão da imagem do JDK que suporta a plataforma desejada diretamente.
# Isso assegura que você está utilizando a base correta.
FROM --platform=$PLATFORM openjdk:21-jdk-slim

EXPOSE 8080

WORKDIR /app

# Copie o diretório inteiro de construção da aplicação para o contêiner
COPY target/quarkus-app /app

ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]
