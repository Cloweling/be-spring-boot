# Dependencies

* Docker v20
* Docker Comopse v2.2.3
* Java v1.8
* Maven v3.6.3
* Visual Studio Code

# Recommendation

## Install Maven and Java 
* [SDKMan](https://sdkman.io/install)

# Commands

## Run docker command
```sh
$ docker compose up -d 
````

Or
```sh
$ docker-compose up -d
```

## Install Dependencies and plugins in Project
```sh
$ mvn clean install
```

## Migrate MySQL Database
```sh
mvn flyway:clean flyway:migrate
```

## Run Api
```sh
mvn spring-boot:run
```