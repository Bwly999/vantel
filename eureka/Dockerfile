FROM openjdk:11-jre-slim

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8761 \
    PROFILES="default" \
    JAR_NAME="eureka.jar"

ADD /target/*.jar /$JAR_NAME

RUN /bin/sh -c set -eux \
    apt-get update \
    apt-get install -y --no-install-recommends curl \
    rm -rf /var/lib/apt/list/*

HEALTHCHECK --interval=5s --timeout=30s CMD curl -f http://localhost:$PORT/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /$JAR_NAME --spring.profiles.active=$PROFILES"]

EXPOSE $PORT
