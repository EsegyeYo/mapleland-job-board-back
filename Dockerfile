# build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src
RUN ./gradlew bootJar

# runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN groupadd --system java \
 && useradd --system --gid java --create-home java

COPY --from=build /app/build/libs/*.jar app.jar

USER java
ENTRYPOINT ["java", "-jar", "app.jar"]