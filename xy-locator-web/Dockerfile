FROM openjdk:8-jre-alpine

ENV APP_TARGET target
ENV APP xy-locator-web-0.0.1-SNAPSHOT.jar

RUN mkdir -p /opt
COPY ${APP_TARGET}/${APP} /opt

CMD java -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-1024m} -jar /opt/${APP}
