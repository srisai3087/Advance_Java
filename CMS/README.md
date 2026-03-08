# Course Management System

A Spring Boot REST API for managing online courses, enrollments, and course materials.

## Tech Stack
- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Swagger / OpenAPI
- Spring Cache
- Lombok

## Setup

### 1. Configure PostgreSQL
Create a database:
```sql
CREATE DATABASE course_management;
```

Update `src/main/resources/application.properties`:
```
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 2. Run the App
```bash
mvn spring-boot:run
```

### 3. Access Swagger UI
```
http://localhost:8080/swagger-ui/index.html
```

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register user |
| POST | /api/auth/login | Login & get JWT |

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/users/{id} | Get user by ID |

### Courses
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/courses | Create course |
| PUT | /api/courses/{id} | Update course |
| DELETE | /api/courses/{id} | Delete course |
| GET | /api/courses | List courses (paginated) |
| GET | /api/courses/{id} | Get course by ID |

#### Pagination example:
```
GET /api/courses?page=0&size=10&sort=title&direction=asc
```

### Enrollments
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/enrollments | Enroll student |
| GET | /api/enrollments/student/{id} | Get student enrollments |
| GET | /api/enrollments/course/{id} | Get course enrollments |
| PATCH | /api/enrollments/{id}/progress | Update progress |

### Course Materials
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/materials/upload | Upload material |
| GET | /api/materials/{id}/download | Download material |
| GET | /api/materials/course/{id} | List course materials |

## Project Structure
```
com.learning.cms
├── config          # Security, Swagger, CORS
├── controllers     # REST controllers
├── dto
│   ├── request     # Request DTOs
│   └── response    # Response DTOs
├── entity          # JPA entities
├── exception       # Custom exceptions & global handler
├── mapper          # Entity ↔ DTO mappers
├── repository      # JPA repositories
├── service         # Service interfaces
│   └── impl        # Service implementations
└── util            # JwtUtil
```
