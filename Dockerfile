FROM openjdk:17-jdk-slim

WORKDIR /app

COPY  target/*.jar countingapi.jar

EXPOSE 8080

CMD ["java", "-jar", "countingapi.jar"]