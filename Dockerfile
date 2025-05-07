
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/subscription-service-0.0.1-SNAPSHOT.jar subscription-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "subscription-service.jar"]