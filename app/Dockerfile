FROM openjdk:10

WORKDIR /

COPY build/distributions/hollywood-service.tar /tmp/hollywood-service.tar
RUN tar -xf /tmp/hollywood-service.tar -C /opt/

ENTRYPOINT /opt/hollywood-service/bin/hollywood-service
