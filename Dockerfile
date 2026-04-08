FROM ubuntu:latest
LABEL authors="luisf"

ENTRYPOINT ["top", "-b"]

FROM eclipse-temurin:17-alpine-3.20

WORKDIR /app

COPY build/libs/agendador-tarefas-0.0.1-SNAPSHOT.jar /app/agendador-tarefas.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/agendador-tarefas.jar"]