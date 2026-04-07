FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/api-0.0.1-SNAPSHOT.jar data_futbolpy.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "data_futbolpy.jar"]