# Build Stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# This uses a wildcard to find your jar even if the name changes slightly
COPY --from=build /app/target/*.jar app.jar

EXPOSE 12000

# Using the shell-form for ENTRYPOINT to ensure ${PORT} is read correctly by Render
ENTRYPOINT java -Dserver.port=${PORT:-12000} -jar app.jar
