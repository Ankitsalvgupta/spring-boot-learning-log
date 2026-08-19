# Spring Boot Learning Log

Weekly Spring Boot assignments and mini-projects, one topic per week, as I learn backend development. Each week is a fully independent, runnable Spring Boot project.

## Prerequisites

- JDK 21+ (some weeks may require a newer version — check each project's own README)
- Maven (or use the included `./mvnw` wrapper — no local Maven install needed)
- A running database instance where required (H2 is in-memory and needs no setup; PostgreSQL-based weeks need a local Postgres server)

## Weeks

| Week | Project | Focus |
|---|---|---|
| 1 | [CakeBaker](./week1-cakebaker) | Dependency Injection — constructor injection, `@Qualifier` for resolving multiple bean implementations |
| 2 | [Employee & Department](./week2-employee-department) | CRUD REST APIs, DTO ↔ Entity mapping, custom validation annotations, global exception handling, H2 database |
| 3 | [Library Management](./week3-librarymanagement) | Entity relationships (`@OneToMany`/`@ManyToOne`), custom JPQL queries, `@EntityGraph` to avoid N+1 queries, PostgreSQL, pagination & sorting |

## Structure

```
spring-boot-learning-log/
├── week1-cakebaker/
├── week2-employee-department/
├── week3-librarymanagement/
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

- Java 21 / 26
- Spring Boot
- Maven
- Spring Data JPA
- H2 Database
- PostgreSQL
- ModelMapper
- Jakarta Bean Validation
