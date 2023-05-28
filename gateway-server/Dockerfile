FROM maven:3.6.3-jdk-11-slim
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
RUN mkdir /app
COPY ./target/*.jar /app/gateway-server.jar
WORKDIR /app
CMD "java" "-jar" "gateway-server.jar"