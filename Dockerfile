FROM eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/ffin-currency-notifier-0.0.1-SNAPSHOT.jar", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", " -Xshareclasses", "-Xquickstart"]
