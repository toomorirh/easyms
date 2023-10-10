# ... if you want run newest jdk
# FROM openjdk:latest
# RUN microdnf install findutils

# ... And if you want downsize application
# You build customized-jre with jlink

FROM openjdk:17-jdk-slim as build-env

ENV ARTIFACT_NAME=easyms-0.0.1-SNAPSHOT.jar

RUN mkdir /app
WORKDIR /app

# Add Materials
COPY ./gradlew /app
COPY ./build.gradle /app
COPY ./settings.gradle /app
COPY ./gradle /app/gradle
COPY ./src /app/src

# Change Env
## Set backend api domain
RUN sed -e 's/localhost:8080/localhost:8080/g' ./src/main/resources/application.properties

# Build App
RUN  ./gradlew build 

EXPOSE 8080
#

ENTRYPOINT exec java -jar ./build/libs/${ARTIFACT_NAME}
# ENTRYPOINT ["sh", "./gradlew", "bootRun"]