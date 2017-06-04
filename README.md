
## Basic GET:
curl -X GET --header 'Accept: text/plain' 'http://localhost:8080/'

## Curl Response Body
RANDOM_ID 882 ip 127.0.1.1 hostname richard-Inspiron-7559

##  GET with delay:
http://localhost:8080/?delay=1000

## GET with kill me System.exit(0);
http://localhost:8080/?killMe=true

## Swagger
http://localhost:8080/swagger-ui.html

## Docker
### Build
clean package docker:build

### Pull
docker pull r3kdotio/simple-spring-boot

## Kubernetes
kubectl create -f simple-spring-boot-deployment.yaml
kubectl expose deployment simple-spring-boot

