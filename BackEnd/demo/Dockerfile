FROM openjdk:11

ENV USERNAME="root" \
    PASSWORD="25164420Ziad" \
    URL="host.docker.internal:3306" \
    PORT=8080
COPY target/demo-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-Durl=${URL}","-Dusername=${USERNAME}","-Dpassword=${PASSWORD}","-jar","backend.jar","--serve.port=${PORT}"]
EXPOSE ${PORT}
#