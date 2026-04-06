

# Issue Tracker API

Backend API for a simple issue tracking system built with Spring Boot.

The project is focused on learning backend development in Java and Spring while building something close to a real product workflow: users can authenticate, create tickets, browse tickets, and filter them by status.

## Tech stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Docker Compose
- Lombok

## What this project does

The API allows authenticated users to work with tickets.

Current core flow:
- create a ticket
- get all tickets
- get a single ticket by id
- filter tickets by status
- get current authenticated user

Each ticket contains:
- `id`
- `title`
- `description`
- `status`
- `createdAt`
- `owner`

## Domain overview

### Ticket

A ticket represents a single issue/task in the system.

Fields visible in the current model:
- `id: UUID`
- `title: String`
- `description: String`
- `status: TicketStatus`
- `createdAt: Instant`
- `owner: UserEntity`

### Status

Tickets use an enum status, for example:
- `OPEN`

If you add more statuses later, good next options are:
- `IN_PROGRESS`
- `DONE`
- `CLOSED`

## Main endpoints

> Adjust paths if your controller mappings are different in your local code.

### User

#### Get current user

```http
GET /api/users/me
```

Returns the currently authenticated user.

### Tickets

#### Create ticket

```http
POST /api/tickets
```

Example request body:

```json
{
  "title": "Login page throws 500",
  "description": "Error appears after submitting valid credentials."
}
```

#### Get all tickets

```http
GET /api/tickets
```

#### Get ticket by id

```http
GET /api/tickets/{id}
```

#### Get tickets by status

```http
GET /api/tickets/status?status={status}
```

Example:

```http
GET /api/tickets/status?stauts=OPEN
```

## Example response

```json
{
  "id": "d290f1ee-6c54-4b01-90e6-d701748f0851",
  "title": "Login page throws 500",
  "description": "Error appears after submitting valid credentials.",
  "status": "OPEN",
  "createdAt": "2026-04-06T12:00:00Z"
}
```

## Running the project locally

### 1. Clone the repository

```bash
git clone https://github.com/SculptTechProject/Issue-Tracker-api.git
cd Issue-Tracker-api
```

### 2. Configure environment

Create your environment configuration if the project requires it.

Typical values for local development might look like this:

```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=issue_tracker
DB_USER=postgres
DB_PASSWORD=postgres
JWT_SECRET=change_me
```

If your project uses `application.properties` or `application.yml`, make sure PostgreSQL connection settings match your local database.

### 3. Start PostgreSQL

If you are using Docker Compose:

```bash
docker compose up -d
```

### 4. Run the application

If you use Maven wrapper:

```bash
./mvnw spring-boot:run
```

Or build and run:

```bash
./mvnw clean install
java -jar target/*.jar
```

## Development notes

### Current strengths of this project

- clear business domain
- practical CRUD-style backend learning
- good base for authentication and authorization
- strong portfolio direction for Java backend roles

### Good next steps

- add update ticket endpoint
- add delete ticket endpoint
- add pagination and sorting
- add validation with `@Valid`
- replace generic `RuntimeException` with custom exceptions
- add global exception handling
- add Swagger / OpenAPI documentation
- add roles such as `USER` and `ADMIN`
- add tests for service and controller layers
- add ticket comments and attachments

## Example workflow for testing

### 1. Get authenticated
Use your configured auth flow to log in and get access to protected endpoints.

### 2. Create a ticket
Send `POST /api/tickets` with title and description.

### 3. Fetch all tickets
Call `GET /api/tickets`.

### 4. Open one ticket by id
Copy the id and call `GET /api/tickets/{id}`.

### 5. Filter by status
Call `GET /api/tickets/status/OPEN`.

## Project goal

This project is meant to be more than a tutorial CRUD app. The goal is to grow it into a cleaner, production-style backend with authentication, authorization, ticket lifecycle management, and better engineering practices.

## Author

Mateusz