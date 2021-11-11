FROM openjdk:10-jre-slim
ADD ["build/libs/ng-demo-app-1.0-SNAPSHOT.jar", "ng-demo-app.jar"]
EXPOSE 8080 8082
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
RUN sh -c 'touch /ng-demo-app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /ng-demo-app.jar" ]