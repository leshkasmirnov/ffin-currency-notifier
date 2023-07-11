FROM openjdk:17-alpine

WORKDIR /app

COPY . /app

RUN ./mvnw spring-boot:run
