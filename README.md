# Project Name

Blogging Application

# Installation

for installation of this project in your system 

1. You must have **Java 8, Maven and Mysql 8** installed in your system.

2. Take checkout by using cmd 

	git checkout "https://github.com/rajeevloghade/Blogging_Application"

# Usage

3. Import it in eclipce/Intellij and do update all project modules by maven update

4. Run the project. 

# Framework/Concepts used in Blogging Application : 

- **Spring Boot (Rapid Application Development)**
- **Spring Security (JWT Implementation)**
- **Swagger Implementation**
- **Hibernate (To connect Java Application with Database)**
- **MYSQL8 (Database)**

# Project Description

The application is a typical blogging platform where users can create and publish blog posts, as well as comment on other users' posts.

### Features
The Blogging Application includes the following features:

- User registration and login
- Create and manage blog posts
- Comment on blog posts
- View other users' blog posts
- Search functionality to find blog posts by Category and User
- Swagger API documentation

The application is built using the Spring Boot framework, which allows for rapid application development and simplifies many common development tasks.

To secure the application, Spring Security is used to implement JWT-based authentication and authorization. This means that when a user logs in to the application, they receive a JWT token, which is used to authenticate subsequent requests to the server. This approach is more efficient than traditional session-based authentication, as it reduces the number of round trips required between the client and server.

Swagger is used to provide API documentation for the application. With Swagger, developers can quickly understand the various endpoints available in the application and their expected inputs and outputs. Additionally, Swagger allows developers to generate client libraries in various programming languages, which makes it easier to consume the API from other applications.

Hibernate is used to connect the Java application to a MySQL8 database. Hibernate is an ORM tool that simplifies database access by mapping Java objects to database tables. This makes it easier to perform CRUD operations on the database without having to write complex SQL queries.

Overall, the Blogging Application is an excellent example of how to build a monolithic application using Spring Boot. By incorporating Spring Security, Swagger, and Hibernate, developers can quickly create a secure and functional application that meets the needs of their users.

# Future Enhancements

We can enhance this project by incorporating various cutting-edge technologies, such as **Microservices, Docker, Kubernetes, Kafka, and AWS.**
