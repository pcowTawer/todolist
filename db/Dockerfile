FROM adoptopenjdk:11-jre-hotspot

ENV H2-DATA /opt/h2-data
ENV H2_ZIP https://github.com/h2database/h2database/releases/download/version-2.3.232/h2-2024-08-11.zip
ENV H2_PORT 1521
ENV DATABASE tasks

RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y unzip && apt-get install -y wget
RUN wget ${H2_ZIP}
RUN unzip h2*.zip -d /opt/
RUN rm h2*.zip
RUN mkdir -p ${H2_DATA}

EXPOSE ${H2_PORT}

CMD  java -cp /opt/h2/bin/h2*.jar org.h2.tools.Server \
    -pg -pgAllowOthers -pgPort ${H2_PORT} \
    -baseDir ${H2_DATA}

