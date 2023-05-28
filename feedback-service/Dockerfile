FROM maven:3.6.3-jdk-11-slim
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
RUN mkdir /app
COPY ./target/*.jar /app/feedback-service.jar
WORKDIR /app
CMD "java" "-jar" "feedback-service.jar"