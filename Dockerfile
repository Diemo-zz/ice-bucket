FROM gradle:7.2.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

FROM eclipse-temurin:17 as base
WORKDIR /app
ENV jarfile=ice.jar
RUN groupadd -r -g 1000 user && useradd -r -g user -u 1000 user
COPY --from=build /app/build/libs/ice-*.jar ./${jarfile}
RUN chown -R user:user /app
USER user
ENTRYPOINT exec java -jar ${jarfile}

