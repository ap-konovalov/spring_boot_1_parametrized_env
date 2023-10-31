FROM openjdk:17.0.1-jdk-slim
EXPOSE 8081
ADD build/libs/parametrized_services-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]