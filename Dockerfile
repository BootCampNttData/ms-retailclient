# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY "src" "app/src"
COPY "pom.xml" "app/"
RUN target=/root/.m2 mvn -f "app/pom.xml" clean package

# Package stage
FROM openjdk:11-jre-slim
COPY --from=build "app/target/retailclient*.jar" "retailclient.jar"
EXPOSE 6000/tcp
ENTRYPOINT ["java","-jar","retailclient.jar"]