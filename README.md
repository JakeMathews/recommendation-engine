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
    - Depends on the `core` and `details` modules
    - Returned models should not be coupled to the `core` or `details` modules
- `core` - contains the core domain models and interfaces
    - Has no dependencies on other modules
- `details` - contains implementation details of the interfaces defined in `core`
    - Depends on the `core` module
