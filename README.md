# HireStack

A backend-focused job posting application built with **Spring Boot** and **MongoDB Atlas**, featuring full-text search across job listings using **MongoDB Atlas Search**.

This project is currently backend-only — all APIs have been tested via **Postman**. Frontend integration is planned for a future phase.

## Features

- Create and retrieve job posts (CRUD via REST APIs)
- Full-text search across job fields (title, description, tech stack, profile) using MongoDB Atlas Search with wildcard field matching
- Search works on any keyword, not limited to a fixed set of terms
- Results sortable by experience level

## Tech Stack

- **Java** (Spring Boot)
- **MongoDB Atlas** + **Atlas Search** (Lucene-based full-text search)
- **Maven** for dependency management
- **Postman** for API testing

## API Endpoints

| Method | Endpoint         | Description                          |
|--------|------------------|---------------------------------------|
| GET    | `/posts`         | Get all job posts                     |
| POST   | `/post`          | Create a new job post                 |
| GET    | `/posts/{text}`  | Search job posts by any keyword       |

### Example

```
GET /posts/spring
```

Returns all job posts where any field (title, description, tech stack, profile) contains the word "spring".

## Setup

### Prerequisites
- Java 17+ (or your configured JDK version)
- Maven
- A MongoDB Atlas cluster with a database and collection set up

### Steps

1. Clone the repo
   ```bash
   git clone https://github.com/<your-username>/HireStack.git
   cd HireStack
   ```

2. Copy the example properties file and add your own credentials
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
   Then edit `application.properties` and add your MongoDB Atlas connection string:
   ```properties
   spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/<database>?retryWrites=true&w=majority
   server.port=8080
   ```

3. Create an Atlas Search index on your collection (via MongoDB Atlas UI → Search tab):
   ```json
   {
     "mappings": {
       "dynamic": true
     }
   }
   ```
   Wait for the index status to become **Active** before testing search.

4. Run the application
   ```bash
   mvn spring-boot:run
   ```

5. Test the APIs using Postman or your browser:
   ```
   http://localhost:8080/posts
   http://localhost:8080/posts/java
   ```

## Project Structure

```
src/main/java/com/jai/HireStack/
├── controller/       # REST controllers
├── model/             # Data models (e.g. Post)
├── repository/        # MongoDB repositories, including Atlas Search logic
└── HireStackApplication.java
```

## Roadmap

- [ ] Add frontend (React/Angular — TBD)
- [ ] Add pagination and filtering to search results
- [ ] Add authentication for posting jobs
- [ ] Add relevance-based sorting using Atlas Search score

