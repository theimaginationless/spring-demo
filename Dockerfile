FROM alpine:edge
ARG JAR_FILE=springDemo-0.0.1-SNAPSHOT.jar
COPY . /spring-demo
RUN apk add --no-cache \
    openjdk11 \
    maven \
    busybox-extras
WORKDIR /spring-demo
RUN mvn clean install
WORKDIR /spring-demo/target
ENTRYPOINT [ "/usr/bin/java" ]
CMD [ "-jar", "springDemo-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8080