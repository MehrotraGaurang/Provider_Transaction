FROM openjdk:8
EXPOSE 9020
ADD target/Provider_Transaction.jar Provider_Transaction.jar
ENTRYPOINT["java", "-jar", "/Provider_Transaction.jar"]