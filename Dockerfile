FROM maven:3.6.3-openjdk-8-slim as builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jdk-alpine3.9
COPY --from=builder /usr/src/app/target/smsserver-0.4.jar /usr/app/smsserver.jar
COPY etc /etc/smsserver
EXPOSE 13003
ENTRYPOINT ["java","-Dlogback.configurationFile=/etc/smsserver/logback.xml","-jar","/usr/app/smsserver.jar","-c","/etc/smsserver/smsserver.xml"]
