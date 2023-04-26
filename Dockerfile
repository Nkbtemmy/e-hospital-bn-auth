FROM maven:3.9.1-sapmachine-11 AS build

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Set the JAVA_HOME environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/

# Set the PATH environment variable
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /home/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn clean package

# Package stage
FROM openjdk:21-slim-buster

COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]