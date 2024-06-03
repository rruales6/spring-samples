FROM amazoncorretto:17-alpine

RUN apk update && apk add vim

ENV TZ=America/Guayaquil

WORKDIR /app

COPY *.jar /app/my-application.jar

CMD ["java", "-jar", "/app/my-application.jar"]
