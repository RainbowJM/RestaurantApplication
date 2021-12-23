# syntax=docker/dockerfile:1

FROM openjdk:17-alpine as base
WORKDIR /app/
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN dos2unix ./mvnw
RUN chmod +x ./mvnw
RUN ["./mvnw", "dependency:go-offline"]
COPY src/ ./src/

FROM base as test
CMD ["./mvnw", "clean", "test"]

FROM base as build
RUN ["./mvnw", "clean", "package", "-Dmaven.test.skip=true"]

FROM openjdk:17-alpine as extract
COPY --from=build /app/target/*.jar ./extract/service.jar
RUN ["java", "-Djarmode=layertools", "-jar", "./extract/service.jar", "extract", "--destination", "./extract/layers/"]

FROM openjdk:17-alpine as deploy
COPY --from=extract ./extract/layers/dependencies/ ./
COPY --from=extract ./extract/layers/snapshot-dependencies/ ./
COPY --from=extract ./extract/layers/spring-boot-loader/ ./
COPY --from=extract ./extract/layers/application/ ./
CMD ["java", "org.springframework.boot.loader.JarLauncher", "--spring.data.mongodb.host=mongo", "--spring.rabbitmq.host=rabbitmq"]
