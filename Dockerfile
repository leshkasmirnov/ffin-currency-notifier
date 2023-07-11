FROM openjdk:17-alpine
WORKDIR /app
COPY . .
RUN ./mvnw clean package

FROM openjdk:17-alpine
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]
