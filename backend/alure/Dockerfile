FROM maven:3.9-amazoncorretto-21-alpine AS build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine-jdk
RUN addgroup -S spring && adduser -S spring -G spring 
USER spring:spring
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
