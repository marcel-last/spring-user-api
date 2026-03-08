# User API

A RESTful API for managing user accounts, built with Spring Boot 4 and PostgreSQL.

## Tech Stack

| Layer | Technology |
|---|---|
| Runtime | Java 25 |
| Framework | Spring Boot 4.0.1 |
| Persistence | Spring Data JPA, Hibernate 7, PostgreSQL |
| Schema migrations | Flyway |
| Object mapping | MapStruct |
| API docs | SpringDoc OpenAPI (Swagger UI) |
| Build | Gradle 9 |
| Container | Docker / Podman |

## Prerequisites

- Java 25+
- Podman or Docker (for the database)

## Getting Started

**1. Start the database**

```bash
docker compose up postgres -d
# or
podman compose up postgres -d
```

**2. Run the application**

```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8080`.

Flyway automatically applies database migrations on startup. Seed data (20 test users) is loaded in the `dev` profile.

## API Reference

Interactive documentation is available via Swagger UI at:

```bash
http://localhost:8080/swagger-ui/index.html
```

### Endpoints

| Method | Path | Description |
|---|---|---|
| `POST` | `/api/v1/users` | Create a new user |
| `GET` | `/api/v1/users` | List users (paginated) |
| `GET` | `/api/v1/users?search=` | Search users by name or email |
| `GET` | `/api/v1/users?enabled=true\|false` | Filter users by enabled status |
| `GET` | `/api/v1/users?role=USER\|ADMIN` | Filter users by role |
| `GET` | `/api/v1/users/{id}` | Get a user by ID |
| `PUT` | `/api/v1/users/{id}` | Update a user |
| `DELETE` | `/api/v1/users/{id}` | Delete a user |
| `PATCH` | `/api/v1/users/{id}/enable` | Enable or disable a user account |
| `GET` | `/api/v1/users/export` | Export all users as a timestamped CSV (ADMIN only) |

### Pagination

List and search endpoints support standard Spring Data pagination parameters:

```
GET /api/v1/users?page=0&size=20&sort=id,asc
```

### Example: Create a user

```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "password": "S3cur3P@ssword!",
    "role": "USER"
  }'
```

## Configuration

The application uses Spring profiles for environment-specific configuration.

### Profiles

| Profile | Purpose |
|---|---|
| `dev` (default) | Local development — connects to `localhost:5432`, verbose logging, Flyway clean on validation error |
| `prod` | Production — datasource configured via environment variables |

### Production Environment Variables

| Variable | Description |
|---|---|
| `DATABASE_URL` | JDBC URL (e.g. `jdbc:postgresql://host:5432/user-api`) |
| `DATABASE_USERNAME` | Database username |
| `DATABASE_PASSWORD` | Database password |
| `SPRING_PROFILES_ACTIVE` | Set to `prod` |

## Running Tests

```bash
./gradlew test
```

Integration tests use Testcontainers to spin up a real PostgreSQL instance, so Docker or Podman must be available.

## Building a Container Image

```bash
docker build -t user-api .
```

To run the full stack (app + database) via Compose:

```bash
docker compose up
```

## Health & Metrics

Spring Boot Actuator endpoints are exposed at `/actuator`:

| Endpoint | URL |
|---|---|
| Health | `/actuator/health` |
| Info | `/actuator/info` |
| Metrics | `/actuator/metrics` |
