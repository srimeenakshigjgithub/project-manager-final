FROM openjdk:8
ADD /project-manager-server-0.0.1-SNAPSHOT.jar project-manager-server-0.0.1-SNAPSHOT.jar
EXPOSE 8686
RUN bash -c 'touch /project-manager-server-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","project-manager-server-0.0.1-SNAPSHOT.jar"]