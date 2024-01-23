# syntax=docker/dockerfile:1

FROM rust:1.75-alpine as build
COPY . .
RUN apk add protoc
RUN apk add musl-dev
RUN cargo build --release

FROM alpine:latest AS final

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
COPY --from=build /target/release/hello /bin

# What the container should run when it is started.
ENTRYPOINT [ "/bin/hello" ]
