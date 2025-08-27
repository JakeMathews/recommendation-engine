## Recommendation System for a take-home interview

# Technologies

- Java 21
- Spring Boot
- JUnit 5
- Postgres
- Flyway
- Jooq

# Structure

This project uses a hexagonal architecture to maintain a clean separation of concerns.

- `root` (API layer)
    - Spring application containing the application config and rest endpoints
    - Depends on the `core` and `details` modules
    - Returned models should not be coupled to the `core` or `details` modules
    - Ideally the API layer would not be in the root of the project, but would be in its own `app` submodule

- `core` (Interfaces / Business logic)
    - Contains the core domain models, interfaces, and business logic
    - Has no dependencies on other modules
- `details` (Implementation)
    - Contains implementation details of the interfaces defined in `core`
    - Depends on the `core` module
    - Database models were generated using Jooq

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

- Adding postgres and other dependencies took WAYYY longer than expected.
    - Ended up copying flyway/jooq setup from a personal project. Not
      ideal... I wanted to get JPA working, but I already sunk too much time on this issue.
- Providing a starter template project could cut down the time a candidate has to spend on configuring their
  environment/project.
- The deliverables state the project should be a working spring boot project, spent way too much time on that in my
  opinion.
- Deliverable also wanted a postman collection, but I didn't have time to write one.
- Definitely would have liked to have written more tests, and accomplish all the deliverables/strech-goals, but I
  overestimated what I could do in just 4 hours.