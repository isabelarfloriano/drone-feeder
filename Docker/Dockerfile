FROM openjdk:11

COPY dronefeeder.jar dronefeeder.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080

CMD java -jar dronefeeder.jar

# Dependências do MySQL
RUN mkdir -p /usr/share/man/man1 && \
    apt-get update && \
    apt-get install -y --no-install-recommends default-mysql-client && \
    rm -rf /var/lib/apt/lists/*
    