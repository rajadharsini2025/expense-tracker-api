# Expense Tracker REST API

## Overview

Expense Tracker is a secure backend application built using Spring Boot that allows users to manage their personal expenses. The application provides authentication and authorization using JWT, enabling users to securely create, view, update, and delete their own expenses while preventing unauthorized access.

---

## Features

### User Management

* User Registration
* User Login
* Password Encryption using BCrypt
* JWT-based Authentication

### Expense Management

* Add Expense
* View Personal Expenses
* Update Expense
* Delete Expense

### Expense Analytics

* Total Expenses
* Category-wise Expense Total
* Category Summary
* Monthly Expense Summary
* Top Spending Category

### Security

* Spring Security Integration
* JWT Authentication & Authorization
* Protected REST APIs
* Ownership-based Access Control

### Additional Features

* DTO Pattern
* Input Validation
* Global Exception Handling
* Pagination & Sorting
* Swagger API Documentation

---

## Technology Stack

* Java 21
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Hibernate
* MySQL
* Swagger / OpenAPI
* Maven
* Git & GitHub

---

## Project Architecture

Controller Layer
↓
Service Layer
↓
Repository Layer
↓
MySQL Database

---

## API Endpoints

### Authentication

| Method | Endpoint  | Description                 |
| ------ | --------- | --------------------------- |
| POST   | /register | Register a new user         |
| POST   | /login    | Login and receive JWT token |

### Expense Management

| Method | Endpoint      | Description                        |
| ------ | ------------- | ---------------------------------- |
| POST   | /expense      | Create a new expense               |
| GET    | /my-expenses  | Get all expenses of logged-in user |
| PUT    | /expense/{id} | Update an expense                  |
| DELETE | /expense/{id} | Delete an expense                  |

### Expense Analytics

| Method | Endpoint                               | Description                      |
| ------ | -------------------------------------- | -------------------------------- |
| GET    | /my-expenses/total                     | Get total expenses               |
| GET    | /my-expenses/category/{category}/total | Get category-wise total          |
| GET    | /my-expenses/category-summary          | Get spending grouped by category |
| GET    | /my-expenses/monthly-summary           | Get month-wise expense summary   |
| GET    | /my-expenses/top-category              | Get highest spending category    |

---

## Security Flow

1. User registers using `/register`.
2. User logs in using `/login`.
3. Server validates credentials and generates a JWT token.
4. Client sends the JWT token in the Authorization header.
5. Spring Security validates the token for every protected request.
6. Users can access and manage only their own expenses.

---

## Running the Application

### Clone Repository

git clone <repository-url>

### Configure Database

Update `application.properties` with your MySQL credentials.

### Run Application

mvn spring-boot:run

### Access Swagger

http://localhost:8080/swagger-ui/index.html

---

## Key Learning Outcomes

* Spring Boot REST API Development
* Spring Security Fundamentals
* JWT Authentication & Authorization
* Hibernate & JPA
* Entity Relationships
* DTO Pattern
* Validation
* Global Exception Handling
* Pagination & Sorting
* Swagger Documentation
* Git & GitHub

---

## Author

Dharshini
