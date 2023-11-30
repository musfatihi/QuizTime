FROM openjdk:21-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/QuizTime-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]