#FROM eclipse-temurin:17 as pre-builder
#WORKDIR /extracted
#
#COPY gradlew .
#COPY build.gradle.kts .
#COPY .gradle .gradle
#COPY gradle gradle
#COPY src src
#COPY libs libs
#COPY build build
#
#RUN ./gradlew build

FROM amazoncorretto:17-alpine as builder
WORKDIR /extracted
#COPY --from=pre-builder extracted/build/libs/*.jar app.jar
COPY /build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

RUN mkdir /attach # 첨부파일

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
