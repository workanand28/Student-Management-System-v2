# Student-Management-System-Backend-

A Spring Boot backend application for managing student records.

## Features & Endpoints

- **GET `/hello`** - Simple health check endpoint.
- **GET `/students`** - Retrieve all students.
- **GET `/students/{id}`** - Retrieve a student by ID.
- **GET `/students/search?name={name}`** - Search students by name.
- **POST `/students`** - Add a new student.
- **PUT `/students/{id}`** - Update student details by ID.
- **DELETE `/students/{id}`** - Delete a student by ID.

## Tech Stack
- Java 26 / Spring Boot 4
- Maven

## Getting Started

### Run the Application
```bash
./mvnw spring-boot:run
```

### Run Tests
```bash
./mvnw test
```
