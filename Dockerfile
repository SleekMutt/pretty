FROM eclipse-temurin:21-jdk as builder

ENV GLASSFISH_VERSION=7.0.19
ENV GLASSFISH_HOME=/glassfish7
ENV PATH="$GLASSFISH_HOME/bin:$PATH"

RUN apt-get update \
    && apt-get install -y wget unzip \
    && wget https://download.eclipse.org/ee4j/glassfish/glassfish-$GLASSFISH_VERSION.zip \
    && unzip glassfish-$GLASSFISH_VERSION.zip -d / \
    && mkdir -p $GLASSFISH_HOME \
    && apt-get remove -y wget unzip \
    && apt-get autoremove -y \
    && apt-get clean

COPY configure-jdbc.sh /opt/glassfish7/configure-jdbc.sh
RUN chmod +x /opt/glassfish7/configure-jdbc.sh && \
    /opt/glassfish7/configure-jdbc.sh

EXPOSE 8080 4848

COPY demo5-1.0-SNAPSHOT.war $GLASSFISH_HOME/domains/domain1/autodeploy/

CMD ["asadmin","start-domain", "--verbose"]
