## Recommendation System for a take-home interview

#### Intentionally being vague to prevent searching for the solution by other candidates

# Technologies

- Java 21
- Spring Boot
- JUnit 5

# Structure

The root of the project contains the Spring application, with the application config and rest endpoints

- Ideally this would be in its own `app` submodule, but I'm not going spend time restructuring the project, I
  used [Spring Intializr](https://start.spring.io)

- `root`
    - Spring application
    - Contains the application config and rest endpoints
    - depends on the `core` and `details` modules
- `core` - contains the core domain models and interfaces
    - Ideally has no dependencies on other modules
    - I was going to tightly couple the model files in core to the spring api, but I felt the urge to rename variables
      and separate serialization annotations,
      so spent a little bit more time here than I perhaps should have.
- `details` - contains implementation details of the interfaces defined in `core`
    - depends on the `core` module
