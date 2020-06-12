FROM oracle/graalvm-ce:20.1.0-java8 as graalvm
RUN gu install native-image

COPY . /home/app/micronaut-elasticsearch-graal
WORKDIR /home/app/micronaut-elasticsearch-graal

RUN native-image --no-server -cp build/libs/micronaut-elasticsearch-graal-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/micronaut-elasticsearch-graal/micronaut-elasticsearch-graal /app/micronaut-elasticsearch-graal
ENTRYPOINT ["/app/micronaut-elasticsearch-graal"]
