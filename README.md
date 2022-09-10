# Spring Boot, Heroku Postgres, JPA, Hibernate Rest API 

A Restful CRUD API for a simple user management application using Spring Boot, Heroku Postgres, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/callicoder/spring-boot-mysql-rest-api-tutorial.git
```

**2. Create Mysql database**
```bash
create Heroku Postgres or any other PostgreSQL database
```

**3. Change postgresql url, username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.url`,`spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using using your preferred IDE**

The app will start running at <http://localhost:8080>.

## Explore Rest API

You can import the methods directly into <a href="https://www.postman.com/downloads/" target="_blank">Postman</a> by going to `Import > Link` and pasting the URL:

```bash
https://www.postman.com/collections/75c016560c1c097d1b73
```
