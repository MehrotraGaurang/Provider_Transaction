FROM openjdk:8
EXPOSE 8090
ADD target/provider_data.jar Provider_Transaction.jar
ENTRYPOINT ["java", "-jar", "/Provider_Transaction.jar"]