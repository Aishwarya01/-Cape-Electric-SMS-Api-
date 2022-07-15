FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 5005
ADD target/solve-sms.jar solve-sms.jar
ENTRYPOINT ["java", "-jar", "solve-sms.jar"]