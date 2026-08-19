# Library Management System

A simple Spring Boot REST API for managing a library's books and authors, with basic CRUD operations, a one-to-many `Author`–`Book` relationship, and a few custom query endpoints.

## Tech Stack

- **Java 26**
- **Spring Boot 4.1.0** (Web MVC, Spring Data JPA)
- **PostgreSQL** — database
- **ModelMapper** — entity ↔ DTO mapping
- **Lombok** — boilerplate reduction
- **Maven** — build tool

## Project Structure

```
src/main/java/com/ankit/LibraryManagement/
├── controller/     # REST endpoints (BookController, AuthorController)
├── service/        # Business logic
├── repository/     # Spring Data JPA repositories
├── entity/         # JPA entities (Book, Author)
├── dto/            # Request/response DTOs
├── exception/      # Custom exceptions + global exception handler
└── config/         # App-level configuration (e.g. ModelMapper bean)
```

## Data Model

- **Author**: `id`, `name`, `email` (unique), `books` (one-to-many)
- **Book**: `id`, `title`, `isbn`, `publishedDate` (auto-set on creation via `@CreationTimestamp`), `author` (many-to-one)

Deleting an author cascades and removes their books (`cascade = CascadeType.ALL, orphanRemoval = true`).

## Setup

### Prerequisites
- JDK 26
- Maven (or use the included `./mvnw` wrapper)
- A running PostgreSQL instance

### 1. Create the database

```sql
CREATE DATABASE librarymgmt;
```

### 2. Configure the app

Copy the example config and fill in your own values:

```bash
cp src/main/resources/application-example.yaml src/main/resources/application.yaml
```

Then either edit `application.yaml` directly, or (recommended) set these environment variables and reference them from the yaml with `${DB_URL}`, `${DB_USERNAME}`, `${DB_PASSWORD}`:

```
DB_URL=jdbc:postgresql://localhost:5432/librarymgmt
DB_USERNAME=your_postgres_username
DB_PASSWORD=your_postgres_password
```

> `application.yaml` is gitignored and should never be committed — it's meant to hold your local/real credentials.

### 3. Run the app

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## API Endpoints

### Book (`/book`)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/book/create` | Create a new book (requires an existing `authorId`) |
| GET | `/book/all?sortBy=id&pageNo=0` | Paginated list of all books |
| GET | `/book/find/{id}` | Get a single book by ID |
| PUT | `/book/update/{id}` | Update a book's details |
| DELETE | `/book/delete/{id}` | Delete a book |
| GET | `/book/findByTitle/{title}` | Find books by (partial) title |
| GET | `/book/published-after-with-author/{date}` | Find books published after a date (`dd-MM-yyyy`), with author eagerly loaded |
| GET | `/book/author/{authorId}` | Find all books by a specific author |

### Author (`/author`)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/author/create` | Create a new author |
| GET | `/author/all?sortBy=id&pageNo=0` | Paginated list of all authors (with book titles) |
| GET | `/author/find/{id}` | Get a single author by ID (with their books) |
| PUT | `/author/update/{id}` | Update an author's details |
| DELETE | `/author/delete/{id}` | Delete an author (cascades to their books) |
| GET | `/author/findAuthor/{name}` | Find an author by (partial) name |

## Example Requests

**Create an author**
```bash
curl -X POST http://localhost:8080/author/create \
  -H "Content-Type: application/json" \
  -d '{"name": "George Orwell", "email": "orwell@example.com"}'
```

**Create a book**
```bash
curl -X POST http://localhost:8080/book/create \
  -H "Content-Type: application/json" \
  -d '{"title": "1984", "isbn": "9780451524935", "authorId": 1}'
```

**Find books published after a date**
```bash
curl http://localhost:8080/book/published-after-with-author/01-01-2020
```

## Notes

- `publishedDate` is set automatically at insert time via `@CreationTimestamp` rather than accepted as user input — this reflects when the record was added to the database, not necessarily the book's real-world publication date.
- Pagination is available on both `findAll` endpoints via `sortBy` and `pageNo` query params (5 results per page).
- `findAll` queries use `@EntityGraph` on the repositories to eagerly fetch the related entity (`author`/`books`) in a single query and avoid N+1 query issues.
- All "not found" cases return a structured JSON error response via a global exception handler (`ResourceNotFoundException` → 404).
