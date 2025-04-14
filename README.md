# Quarkus with otel demo for grafana cloud

## Deploy quarkus services  on kubernetes

Review QUARKUS_OTEL_EXPORTER_OTLP_ENDPOINT environment variable in yaml file


```sh
kubectl apply -f https://raw.githubusercontent.com/gpocs/quarkus-otel-example/refs/heads/master/bookservice/src/main/kube/bookservice.yaml

kubectl apply -f https://raw.githubusercontent.com/gpocs/quarkus-otel-example/refs/heads/master/orderservice/src/main/kube/orderservice.yaml

```

## Deploy k6 for continuos testing

```sh

```

## Ingress deployment (optional)

```sh
kubectl apply -f https://raw.githubusercontent.com/gpocs/quarkus-otel-example/refs/heads/master/bookservice/src/main/kube/bookservice-ingress.yaml

kubectl apply -f https://raw.githubusercontent.com/gpocs/quarkus-otel-example/refs/heads/master/orderservice/src/main/kube/orderservice-ingress.yaml
```


## Simple test

```sh
curl --location 'http://orderservice.codersandbox.com/orders' \
--header 'Content-Type: application/json' \
--data '{
    "bookId": "cleancode",
    "quantity": 2
}'
```


## Rebuild from source

```sh
mvn clean package -f bookservice/pom.xml
docker build -f bookservice/src/main/docker/Dockerfile.jvm -t alainpham/bookservice:1.0.3 bookservice
docker push alainpham/bookservice:1.0.3


mvn clean package -f orderservice/pom.xml
docker build -f orderservice/src/main/docker/Dockerfile.jvm -t alainpham/orderservice:1.0.1 orderservice
docker push alainpham/orderservice:1.0.1
```
