FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 12000
ENTRYPOINT ["java", "-Dserver.port=12000", "-jar", "app.jar"]
