## Recommendation System for a take-home interview

#### Intentionally being vague to prevent searching for the solution by other candidates

# Technologies

- Java 21
- Spring Boot
- JUnit 5
- Postgres
- Flyway
- Jooq

# Structure

The root of the project contains the Spring application, with the application config and rest endpoints

- Ideally this would be in its own `app` submodule, but I'm not going spend time restructuring the project, I
  used [Spring Intializr](https://start.spring.io)

- `root`
    - Spring application
    - Contains the application config and rest endpoints
    - Depends on the `core` and `details` modules
    - Returned models should not be coupled to the `core` or `details` modules
- `core` - contains the core domain models and interfaces
    - Has no dependencies on other modules
- `details` - contains implementation details of the interfaces defined in `core`
    - Depends on the `core` module
    - Depends on Spring data for Postgres JPA configuration

# Running

- Run `docker compose up -d postges` to startup postgres
- Run `docker compose run flyway` to run migrations on the database
- Run `./gradlew generateJooq` to generate jooq classes from the database schema
- Run `./gradlew bootRun` to run the application

# Testing

- Run `./gradlew clean test` to run the tests
    - Note: There only exists one test suite for recommendations, this helped flush out configuration issues,
      but there was insufficient time to write additional tests.

# Struggles and Feedback

- Adding postgres took WAYYY longer than expected. Ended up copying flyway/jooq setup from a personal project. Not
  ideal... I wanted to get JPA working, but I already sunk too much time on this issue.
- Providing a starter template project could cut down the time a candidate has to spend on configuring their
  environment/project. I spent as much time on project setup and dependency management as I did on the task requirements
  themselves.
- Does the project even need to be runnable? I assumed so, but now I'm not sure if that's the case.
