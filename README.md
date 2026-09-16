# Jubby

Jubby is a full-stack job search and application management platform.

## Tech Stack

- Backend: Java, Spring Boot
- Frontend: React, TypeScript
- Database: PostgreSQL
- API: REST
- Authentication: Spring Security, JWT
- DevOps: Docker, GitHub Actions

## Project Structure

```text
jubby/
├── backend/
├── frontend/
├── docs/
└── README.md
```

## Backend Setup

Create a local PostgreSQL database:

```bash
createdb jubby_dev
```

Copy the example environment file:

```bash
cp .env.example .env
```

Update `.env` with your local database settings:

```env
DB_URL=jdbc:postgresql://localhost:5432/jubby_dev
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password
JPA_DDL_AUTO=update
JPA_SHOW_SQL=true
```

Load environment variables and start the backend:

```bash
set -a
source .env
set +a
cd backend
./mvnw spring-boot:run
```

Health check:

```bash
curl http://localhost:8080/api/health
```

Expected response:

```text
Jubby backend is running
```
