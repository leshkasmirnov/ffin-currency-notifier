FROM openjdk:17-alpine

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

CMD ["java", "-jar", "target/ffin-currency-notifier-0.0.1-SNAPSHOT.jar"]
