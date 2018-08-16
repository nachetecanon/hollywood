FROM docker-test.pibenchmark.com/pi-java:8
EXPOSE 8081

ADD build/libs/app.jar app.jar

# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
ENTRYPOINT ["java",  \
            "-Xms256m", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", "/app.jar"]
