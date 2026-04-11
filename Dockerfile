FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY user-service/gradlew .
COPY user-service/gradle gradle
COPY user-service/build.gradle .
COPY user-service/settings.gradle .

COPY common common
COPY user-service user-service

RUN chmod +x gradlew
RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/user-service/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]