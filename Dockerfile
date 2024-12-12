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

ADD password_1.txt /tmp/password_1.txt
ADD password_2.txt /tmp/password_2.txt
RUN asadmin --user admin --passwordfile /tmp/password_1.txt change-admin-password --domain_name domain1 ; asadmin start-domain domain1 ; asadmin --user admin --passwordfile /tmp/password_2.txt enable-secure-admin ; asadmin stop-domain domain1
RUN rm /tmp/password_?.txt

COPY demo5-1.0-SNAPSHOT.war $GLASSFISH_HOME/domains/domain1/autodeploy/

CMD ["asadmin","start-domain", "--verbose"]
