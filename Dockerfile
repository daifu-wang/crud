FROM openjdk:11
EXPOSE 9090
ADD target/crud.jar crud.jar
ENTRYPOINT ["java", "-jar", "crud.jar"]