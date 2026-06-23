# Pasul 1: Construirea aplicației cu Maven și Java 17
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Pasul 2: Rularea aplicației folosind Amazon Corretto Java 17 (stabilă și activă)
FROM amazoncorretto:17-alpine-jdk
COPY --from=build /target/AnimalShelter-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]