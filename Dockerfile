# ... if you want run newest jdk
# FROM openjdk:latest
# RUN microdnf install findutils

FROM openjdk:17-jdk-slim as build-env

ENV ARTIFACT_NAME=easyms-0.0.1-SNAPSHOT.jar

RUN mkdir /app
WORKDIR /app
COPY ./gradlew /app
COPY ./build.gradle /app
COPY ./settings.gradle /app
COPY ./src /app/src
COPY ./gradle /app/gradle

RUN  ./gradlew build 

EXPOSE 8080

ENTRYPOINT exec java -jar ./build/libs/${ARTIFACT_NAME}
# ENTRYPOINT ["sh", "./gradlew", "bootRun"]