# Run Stage
FROM eclipse-temurin:17-jre-jammy
# This copy command is more flexible to find the jar regardless of the name
COPY --from=build /target/*.jar app.jar
EXPOSE 12000
# We add "server.port" directly into the start command to be 100% sure
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
