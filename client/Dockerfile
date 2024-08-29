FROM mobiledevops/android-sdk-image:latest

WORKDIR /app

USER root

COPY . .

RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]