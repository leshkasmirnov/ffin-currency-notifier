FROM openjdk:17-alpine

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/ffin-currency-notifier-0.0.1-SNAPSHOT.jar"]
