FROM openjdk:21-slim-buster
EXPOSE 8081
ADD target/e-hospital-app.jar e-hospital-app.jar 
ENTRYPOINT ["java","-jar","/e-hospital-app.jar"]


# Build stage
# FROM maven:3.6.3-jdk-8-slim AS build
# FROM maven:3.9.1-sapmachine-11 AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean test package

# # Package stage
# FROM openjdk:21-slim-buster

# COPY --from=build /home/app/target/*.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","app.jar"]