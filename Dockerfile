FROM openjdk:12-alpine
EXPOSE 8080
COPY /build/sales-system-0.0.0-runner.jar sales-system.jar
ENTRYPOINT ["java", "-jar", "/sales-system.jar"]