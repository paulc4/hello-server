FROM openjdk:8-jre
ADD target/hello-server-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
