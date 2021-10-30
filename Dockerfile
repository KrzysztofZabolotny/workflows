FROM openjdk:15
ADD target/rest-api-0.0.1-SNAPSHOT.jar rest-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "rest-api-0.0.1-SNAPSHOT.jar"]