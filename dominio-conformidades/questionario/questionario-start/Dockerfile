FROM adoptopenjdk/openjdk14:alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} questionario-start-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","--enable-preview","-jar","/questionario-start-0.0.1-SNAPSHOT.jar"]