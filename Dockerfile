FROM openjdk:14-jdk-alpine
MAINTAINER Aleksander
COPY target/pastebox-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java","-jar","/pastebox.jar"]