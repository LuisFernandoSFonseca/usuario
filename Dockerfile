FROM gradle:9.4.1-jdk17-alpine as build
WORKDIR /app
COPY . .
run gradle build --no-daemon

FROM eclipse-temurin:17-alpine-3.20

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/usuario.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/usuario.jar"]