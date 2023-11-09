FROM openjdk

WORKDIR /app

COPY target/mapdata-api-0.0.1-SNAPSHOT.jar /app/mapdata-api.jar

ENTRYPOINT ["java","-jar", "mapdata-api.jar"]