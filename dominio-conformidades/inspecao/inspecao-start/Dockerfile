FROM adoptopenjdk/openjdk14:alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} inspecao-start-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","--enable-preview","-jar","/inspecao-start-0.0.1-SNAPSHOT.jar"]