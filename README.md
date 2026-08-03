# Spring Boot Learning Log

Weekly Spring Boot assignments and mini-projects, one topic per week, as I learn backend development. Each week is a fully independent, runnable Spring Boot project.

## Weeks

| Week | Project | Focus |
|---|---|---|
| 1 | [CakeBaker](./week1-cakebaker) | Dependency Injection — constructor injection, `@Qualifier` for resolving multiple bean implementations |
| 2 | [Employee & Department](./week2-employee-department) | CRUD REST APIs, DTO ↔ Entity mapping, custom validation annotations, global exception handling, H2 database |

<!--
  Adding a new week? Just add one row above, following the same pattern:
  | X | [Project Name](./weekX-project-folder) | One-line summary of what it covers |

  If the new week introduces a new tool/library not already listed below,
  add it to the Tech stack section too.
-->

## Structure

```
spring-boot-learning-log/
├── week1-cakebaker/
├── week2-employee-department/
└── ...
```

Each `weekN-*` folder is a standalone Maven project with its own `pom.xml`, `src/`, and `README.md` — clone the repo and run any week on its own.

## Running any week's project

```bash
cd week<N>-<name>
./mvnw spring-boot:run
```

On Windows:
```bash
mvnw.cmd spring-boot:run
```

## Tech stack

Combined across all weeks so far:

- Java 21
- Spring Boot
- Maven
- Spring Data JPA
- H2 Database
- ModelMapper
- Jakarta Bean Validation
