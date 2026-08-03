# Week 2 — Employee & Department Management

A Spring Boot REST API for managing Employees and Departments, demonstrating CRUD operations, custom validation, DTO mapping, and centralized exception handling.

## What it does

Exposes REST endpoints to create, read, update (full and partial), and delete Employees and Departments. Employee and Department data is validated with both built-in and custom annotations before being persisted, and all responses (success and error) follow a consistent JSON structure via global response/exception handling. Data is stored in an H2 database.

## Project structure

```
src/main/java/com/ankit/employees/employeeDepartment/
├── EmployeeDepartmentApplication.java   # Entry point
├── advices/
│   ├── ApiResponse.java                  # Standard success response wrapper
│   ├── ApiError.java                     # Standard error response shape
│   ├── GlobalResponseHandler.java        # @ControllerAdvice — wraps all successful responses
│   └── GlobalExceptionHandler.java       # @RestControllerAdvice — catches exceptions, builds ApiError
├── annotations/
│   ├── EmployeeRoleValidation.java       # Custom validation annotation for Employee role
│   ├── EmployeeRoleValidator.java        # Validation logic for the above
│   ├── DepartmentTitleValidation.java    # Custom validation annotation for Department title
│   └── DepartmentTitleValidator.java     # Validation logic for the above
├── config/
│   └── MapperConfig.java                 # ModelMapper bean configuration
├── controller/
│   ├── EmployeeController.java           # REST endpoints for Employee
│   └── DepartmentController.java         # REST endpoints for Department
├── dto/
│   ├── EmployeeDTO.java                  # Request/response shape for Employee
│   └── DepartmentDTO.java                # Request/response shape for Department
├── entities/
│   ├── EmployeeEntity.java               # JPA entity — Employee table
│   └── DepartmentEntity.java             # JPA entity — Department table
├── exceptions/
│   └── ResourceNotFoundException.java    # Thrown when an Employee/Department isn't found
├── repositories/
│   ├── EmployeeRepository.java           # JpaRepository for Employee
│   └── DepartmentRepository.java         # JpaRepository for Department
└── services/
    ├── EmployeeService.java              # Business logic for Employee
    └── DepartmentService.java            # Business logic for Department
```

## Key concepts demonstrated

- **DTO ↔ Entity mapping** via ModelMapper, keeping API contracts separate from database structure
- **Custom validation annotations** (`@EmployeeRoleValidation`, `@DepartmentTitleValidation`) alongside standard Bean Validation (`@NotBlank`, `@Size`, `@PastOrPresent`, etc.)
- **Partial updates (PATCH)** using Java Reflection to update only the fields provided in the request
- **Centralized exception handling** via `@RestControllerAdvice`, converting exceptions like `ResourceNotFoundException` into a consistent `ApiError` JSON shape
- **Global response wrapping** via `@ControllerAdvice`, so every successful response follows the same `ApiResponse<T>`-style structure
- **H2 file-based database** for local persistence

## Requirements

- Java 21
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper)

## Setup

This project uses a local `application.yaml` (gitignored, since it contains a local file path and H2 credentials). Before running:

```bash
cp src/main/resources/application-example.yaml src/main/resources/application.yaml
```

Then edit `application.yaml` and fill in your own local database path, username, and password.

## Running the project

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The API will be available at `http://localhost:8080`, with the H2 console (if enabled) at `http://localhost:8080/h2-console`.

## Example endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/employees` | Create a new employee |
| `GET` | `/employees/{id}` | Get an employee by ID |
| `GET` | `/employees` | Get all employees |
| `PUT` | `/employees/{id}` | Fully update an employee |
| `PATCH` | `/employees/{id}` | Partially update an employee |
| `DELETE` | `/employees/{id}` | Delete an employee |
| `POST` | `/departments` | Create a new department |
| `GET` | `/departments/{id}` | Get a department by ID |

*(Adjust paths above if your actual `@RequestMapping` values differ.)*

## Sample request body

```json
{
  "title": "Engineering",
  "isActive": true,
  "createdAt": "2026-08-02T20:39:34"
}
```

## Tech stack

- Spring Boot
- Spring Data JPA
- H2 Database
- ModelMapper
- Jakarta Bean Validation
- Maven
