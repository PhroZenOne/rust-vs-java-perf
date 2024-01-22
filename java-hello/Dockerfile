# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-alpine as build

WORKDIR /hello

COPY build.gradle settings.gradle gradlew /hello/

COPY gradle /hello/gradle

RUN ./gradlew build || return 0 

COPY . /hello

RUN ./gradlew bootJar

FROM eclipse-temurin:17-jre-alpine AS final

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

# Copy the executable from the "build" stage.
COPY --from=build /hello/build/libs /

# What the container should run when it is started.
ENTRYPOINT [ "java", "-jar", "/demo-0.0.1-SNAPSHOT.jar" ]
