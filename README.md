# Micronaut Elasticsearch GraalVM

Test application for Micronaut Elasticsearch and GraalVM.

To run Elasticsearch in Docker:
```
docker run -it --rm -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.8.0
```

To test the application:

```
curl -X POST -H 'Content-Type:application/json' -d '{"imdb":"matrix", "title":"The Matrix"}' localhost:8080/api/movies
curl "localhost:8080/api/movies?title=matrix"
```
