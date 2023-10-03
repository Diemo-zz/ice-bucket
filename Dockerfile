FROM gradle:latest AS build
WORKDIR /app
COPY . .
RUN gradle build

FROM openjdk:21-jdk as base
WORKDIR /app
ENV jarfile=ice.jar
RUN groupadd -r -g 1000 user && useradd -r -g user -u 1000 user
COPY --from=build /app/build/libs/${jarfile} .
RUN chown -R user:user /app
USER user
ENTRYPOINT exec java -jar ${jarfile}

