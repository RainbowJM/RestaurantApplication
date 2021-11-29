FROM openjdk:17-alpine as builder
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} service.jar
RUN java -Djarmode=layertools -jar service.jar extract

FROM openjdk:17-alpine as runner
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]