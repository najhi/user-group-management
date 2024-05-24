# User-Group Management System

## Overview
This project demonstrates a basic User-Group management system using Spring Boot, Hibernate, and JPA with a many-to-many relationship between users and groups.

## Requirements
- Java 18+
- Maven
- MySQL or PostgreSQL

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/najhi/Paraminfo.git
   cd user-group-management

2. Update database configuration in `src/main/resources/application.yml`.

3. Run the application:
    ```bash
   mvn spring-boot:run

4. The application will be available at http://localhost:8080.

## REST Endpoints
- User
    - `POST /api/users`: Create a new user
    - `GET /api/users`: Get all users
- Group
  - `POST /api/groups`: Create a new group
  - `GET /api/groups`: Get all groups
    
## Running Tests
To run the unit tests, execute:

```bash
mvn test
```

## Database Scripts
Database initialization scripts are located in `src/main/resources/data.sql`.





