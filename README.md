# Build

```sh
mvn clean package -f bookservice/pom.xml
docker build -f bookservice/src/main/docker/Dockerfile.jvm -t alainpham/bookservice:1.0.3 bookservice
docker push alainpham/bookservice:1.0.3


mvn clean package -f orderservice/pom.xml
docker build -f orderservice/src/main/docker/Dockerfile.jvm -t alainpham/orderservice:1.0.1 orderservice
docker push alainpham/orderservice:1.0.1
```


# Deploy kubernetes

```sh

```