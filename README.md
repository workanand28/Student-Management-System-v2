# 🎓 Student Management System - Backend REST API

[![Java Version](https://img.shields.io/badge/Java-21%20%2F%2026-orange.svg?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Data MongoDB](https://img.shields.io/badge/Spring%20Data-MongoDB-green.svg?logo=mongodb)](https://www.mongodb.com/)
[![Build Status](https://img.shields.io/badge/Build-Passing-success.svg)]()
[![License](https://img.shields.io/badge/License-MIT-blue.svg)]()

A robust, production-ready RESTful Backend API for managing student records, built with **Spring Boot** and **MongoDB**. This project demonstrates standard enterprise design patterns including clean layered architecture, bean validation, custom repository queries, and MongoDB Atlas cloud database integration.

---

## Table of Contents

- [Overview](#overview)
- [Architecture & Design](#architecture--design)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Features & Validation Rules](#features--validation-rules)
- [Prerequisites](#prerequisites)
- [Configuration & Environment](#configuration--environment)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
  - [Health Check](#1-health-check)
  - [Get All Students](#2-get-all-students)
  - [Get Student by ID](#3-get-student-by-id)
  - [Search Students by Name](#4-search-students-by-name)
  - [Create Student](#5-create-student)
  - [Update Student](#6-update-student)
  - [Delete Student](#7-delete-student)
- [Running Automated Tests](#running-automated-tests)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

The **Student Management System Backend** provides full CRUD (Create, Read, Update, Delete) capabilities alongside advanced search features for student data management. It connects seamlessly to MongoDB Atlas and enforces validation constraints on all incoming data payloads.

---

## Architecture & Design

The application follows a **Layered Architecture** with strict separation of concerns:

```text
[ Client (Postman / Frontend / cURL) ]
                 │  HTTP / JSON
                 ▼
     [ Controller Layer ]  ──> Handles HTTP requests, path variables & validation (@Valid)
                 │
                 ▼
      [ Service Layer ]    ──> Contains business logic & transaction handling
                 │
                 ▼
     [ Repository Layer ]  ──> Extends MongoRepository for database operations
                 │
                 ▼
      [ MongoDB Atlas ]    ──> Cloud NoSQL Database ("students" collection)
```

---

## Tech Stack

- **Framework**: Spring Boot 4.x / Spring Web MVC
- **Database**: MongoDB / MongoDB Atlas Cloud
- **Persistence**: Spring Data MongoDB (`MongoRepository`)
- **Validation**: Jakarta Bean Validation (`hibernate-validator`)
- **Testing**: JUnit 5, Spring Boot Test
- **Build Tool**: Apache Maven (Maven Wrapper included)
- **Language**: Java 21 / 26

---

## Project Structure

```text
student-management/
├── .mvn/wrapper/                  # Maven wrapper binaries & properties
├── src/
│   ├── main/
│   │   ├── java/com/example/student_management/
│   │   │   ├── controller/
│   │   │   │   ├── HelloController.java       # Health check controller
│   │   │   │   └── StudentController.java     # REST endpoints for student resources
│   │   │   ├── model/
│   │   │   │   └── Student.java               # MongoDB Document entity with validations
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java     # Spring Data Mongo repository interface
│   │   │   ├── service/
│   │   │   │   ├── StudentService.java        # Service interface definition
│   │   │   │   └── StudentServiceImpl.java    # Business logic implementation
│   │   │   └── StudentManagementApplication.java # Spring Boot entry point
│   │   └── resources/
│   │       └── application.properties         # Database and server configuration
│   └── test/
│       └── java/com/example/student_management/
│           └── StudentManagementApplicationTests.java # End-to-end integration tests
├── mvnw                           # Linux/macOS Maven wrapper script
├── mvnw.cmd                       # Windows Maven wrapper script
├── pom.xml                        # Project dependencies & build config
└── README.md                      # Project documentation
```

---

## Features & Validation Rules

- **Input Validation**: Automatically validated on `POST` and `PUT` requests:
  - `name`: Must not be blank (`@NotBlank`).
  - `email`: Must not be blank and must follow a valid email format (`@Email`).
  - `age`: Must be an integer greater than or equal to 18 (`@Min(18)`).
  - `course`: Must not be blank (`@NotBlank`).
- **Flexible ID Handling**: Clean creation with automatic MongoDB `ObjectId` generation.
- **Case-Insensitive Search**: Custom derived query method `findByNameIgnoreCase`.
- **Standard HTTP Status Codes**: Returns `200 OK`, `201 CREATED`, `204 NO CONTENT`, `400 BAD REQUEST`, `404 NOT FOUND`.

---

## Prerequisites

Before running the application, ensure you have:

- **Java Development Kit (JDK)**: JDK 17, 21, or 26 installed.
- **MongoDB**: A running local MongoDB instance (`mongodb://localhost:27017`) OR a [MongoDB Atlas](https://www.mongodb.com/atlas) cloud cluster.
- **Git**: For version control.

---

## Configuration & Environment

Configuration is maintained in [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
spring.application.name=student-management

# Server Configuration
server.port=8080

# MongoDB Connection (Replace with your Atlas connection string or local URI)
spring.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-address>/student-management?retryWrites=true&w=majority
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-address>/student-management?retryWrites=true&w=majority
spring.data.mongodb.database=student-management
```

> **Security Tip**: Never commit real database passwords to public repositories. You can pass the URI as an environment variable:
> ```bash
> export SPRING_DATA_MONGODB_URI="mongodb+srv://user:pass@cluster.mongodb.net/student-management"
> ```

---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/workanand28/Student-Management-System-Backend-.git
cd Student-Management-System-Backend-
```

### 2. Build and Run

#### Using Maven Wrapper (Recommended)

**On Windows (PowerShell / Command Prompt):**
```powershell
.\mvnw.cmd spring-boot:run
```

**On macOS / Linux:**
```bash
chmod +x ./mvnw
./mvnw spring-boot:run
```

#### Using Packaged JAR File
```bash
./mvnw clean package
java -jar target/student-management-0.0.1-SNAPSHOT.jar
```

The application will start on **`http://localhost:8080`**.

---

## API Documentation

### Base URL
`http://localhost:8080`

### Summary of Endpoints

| Method | Endpoint | Description | Success Code |
| :--- | :--- | :--- | :--- |
| `GET` | `/hello` | Basic Health Check | `200 OK` |
| `GET` | `/students` | Retrieve all student records | `200 OK` |
| `GET` | `/students/{id}` | Retrieve a student by ID | `200 OK` |
| `GET` | `/students/search?name={name}` | Search students by name (case-insensitive) | `200 OK` |
| `POST` | `/students` | Create a new student | `201 CREATED` |
| `PUT` | `/students/{id}` | Update an existing student | `200 OK` |
| `DELETE` | `/students/{id}` | Delete a student by ID | `204 NO CONTENT` |

---

### Detailed Endpoint Specifications

#### 1. Health Check
- **URL**: `/hello`
- **Method**: `GET`
- **Response**:
  ```text
  Hello World
  ```

---

#### 2. Get All Students
- **URL**: `/students`
- **Method**: `GET`
- **Response** (`200 OK`):
  ```json
  [
    {
      "id": "66c4c0bf10a12e4f0a99c0d1",
      "name": "Alex Johnson",
      "email": "alex.johnson@example.com",
      "age": 21,
      "course": "Computer Science"
    }
  ]
  ```

---

#### 3. Get Student by ID
- **URL**: `/students/{id}`
- **Method**: `GET`
- **Path Parameter**: `id` (String)
- **Response** (`200 OK`):
  ```json
  {
    "id": "66c4c0bf10a12e4f0a99c0d1",
    "name": "Alex Johnson",
    "email": "alex.johnson@example.com",
    "age": 21,
    "course": "Computer Science"
  }
  ```
- **Error Response** (`404 Not Found`): If the student ID does not exist.

---

#### 4. Search Students by Name
- **URL**: `/students/search?name={name}`
- **Method**: `GET`
- **Query Parameter**: `name` (String, e.g. `/students/search?name=alex`)
- **Response** (`200 OK`):
  ```json
  [
    {
      "id": "66c4c0bf10a12e4f0a99c0d1",
      "name": "Alex Johnson",
      "email": "alex.johnson@example.com",
      "age": 21,
      "course": "Computer Science"
    }
  ]
  ```

---

#### 5. Create Student
- **URL**: `/students`
- **Method**: `POST`
- **Headers**: `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "age": 22,
    "course": "Information Technology"
  }
  ```
- **Response** (`201 Created`):
  ```json
  {
    "id": "66c4c23f10a12e4f0a99c0d2",
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "age": 22,
    "course": "Information Technology"
  }
  ```

---

#### 6. Update Student
- **URL**: `/students/{id}`
- **Method**: `PUT`
- **Path Parameter**: `id` (String)
- **Headers**: `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "name": "Jane Smith",
    "email": "jane.updated@example.com",
    "age": 23,
    "course": "Software Engineering"
  }
  ```
- **Response** (`200 OK`): Returns the updated student document.
- **Error Response** (`404 Not Found`): If the student ID does not exist.

---

#### 7. Delete Student
- **URL**: `/students/{id}`
- **Method**: `DELETE`
- **Path Parameter**: `id` (String)
- **Response** (`204 No Content`): On successful deletion.
- **Error Response** (`404 Not Found`): If the student ID does not exist.

---

## Running Automated Tests

Integration tests verify Spring context loading, Mongo connection, and full repository lifecycle (Insert, Query, Search, Delete):

```bash
# Windows
.\mvnw.cmd test

# Linux / macOS
./mvnw test
```

---

## Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## License

This project is licensed under the MIT License - feel free to use and adapt it for your own learning and production projects!
