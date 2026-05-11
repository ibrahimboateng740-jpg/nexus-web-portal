# Build Stage
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run Stage - Using a more reliable image for 2026
FROM eclipse-temurin:17-jre-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 12000
ENTRYPOINT ["java", "-Dserver.port=12000", "-jar", "app.jar"]
