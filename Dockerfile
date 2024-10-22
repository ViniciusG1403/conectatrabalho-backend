ARG PLATFORM=linux/amd64


FROM --platform=$PLATFORM openjdk:21-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY target/quarkus-app /app

ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]
