# Lesson

## Initiate the Lesson project

### 0. Spring CLI

Initiate the project using spring cli with these dependencies.

- **web**: Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **data-jpa**: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- **postgresql**: A JDBC and R2DBC driver that allows Java programs to connect to a PostgreSQL database using standard, database independent Java code.
- **h2**: Provides a fast in-memory database that supports JDBC API and R2DBC access, with a small (2mb) footprint. Supports embedded and server modes as well as a browser based console application.
- **actuator**: Supports built in (or custom) endpoints that let you monitor and manage your application - such as application health, metrics, sessions, etc.
- **oauth2-resource-server**: Spring Boot integration for Spring Security's OAuth2 resource server features.

```sh
spring init \
    --build gradle \
    --java-version 17 \
    --dependencies web,data-jpa,postgresql,h2,actuator,oauth2-resource-server \
    --groupId ml.mizotakhteh \
    --artifactId lesson \
    --name lesson \
    --force ./
```

### 1. Prepare Dev Environment

- Set environment variable in a Unix environment

```sh
export spring_profiles_active=dev
```

- Create a docker network named ***mizotakhteh***

```sh
docker network create dev
```

- Run **PostgreSQL** in docker

```sh
docker run \
    -it --rm \
    --name postgresql \
    --network dev \
    -p 5432:5432 \
    -e POSTGRES_DB=lesson \
    -e POSTGRES_PASSWORD=postgres \
    postgres
```

- Run **MongoDB Express** in docker

```sh
docker run \
    -it --rm \
    --name mongo-express \
    --network dev \
    -p 8081:8081 \
    -e ME_CONFIG_MONGODB_ADMINUSERNAME="root" \
    -e ME_CONFIG_MONGODB_ADMINPASSWORD="root" \
    -e ME_CONFIG_MONGODB_SERVER="mongo" \
    mongo-express
```

- Run **Zookeeper** and **Kafka** in docker

```sh
docker run \
    -it --rm \
    --name zookeeper \
    --network dev \
    zookeeper
    
docker run \
    -it --rm \
    --name kafka \
    --network dev \
    -p 9092:9092 \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
    -e ALLOW_PLAINTEXT_LISTENER=yes \
    bitnami/kafka
```

### 2. Add ***Mongock*** Dependency

Add this two dependencies into the `build.gradle`

```groovy
dependencies {
    ...
    implementation "com.github.cloudyrock.mongock:mongock-spring-v5:4.+"
    implementation "com.github.cloudyrock.mongock:mongodb-springdata-v3-driver:4.+"
    ...
}
```

### 3. Add ***Jib*** Plugin Dependency

```groovy
plugins {
    ...
    id 'com.google.cloud.tools.jib' version '3.1.4'
    ...
}
```

## Test

### Create Order

```sh
curl \
  -s -v \
  -X POST \
  -H 'Content-Type: application/json' \
  -d '
    {
        "id": "125",
        "orderStatus": "PAID",
        "total": 12.87,
        "orderDate": "2021-12-01T14:23:34.237+00:00",
        "userId": "1253",
        "lineItems": []
    }' \
  localhost:8080/orders
```
