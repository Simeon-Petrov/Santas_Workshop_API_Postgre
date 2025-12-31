# Santa's Workshop API (PostgreSQL Edition)

## Project Overview

This is an advanced **RESTful API application** built using **Spring Boot** and **PostgreSQL**, designed to manage Santa's holiday operations. 
The system handles the full lifecycle of Christmas gifts, coordinates elf assignments based on skill levels, and manages complex delivery logistics with persistent data storage and real-time status updates.

---

## Key Features Implemented

- **Persistent Data Storage:** Full integration with **PostgreSQL**, ensuring all gifts, elves, and deliveries are saved and recoverable.
- **Gift Lifecycle Management:** Supports full CRUD operations and a dedicated wrapping workflow that transitions gifts from `PENDING` to `READY` status.
- **Dynamic Elf Assignment:** Assigns gifts to specific elves while validating gift availability and preventing the reassignment of already delivered items.
- **Delivery Logistics:** Groups multiple gifts into a single delivery plan with automated status propagation (marking a delivery as `DELIVERED` automatically updates all associated gifts in the database).
- **Real-time Statistics:** A dedicated dashboard endpoint providing live insights from the database, including gift counts by status and total successful deliveries.
- **Global Error Handling:** Centralized exception handling to return meaningful API errors (404 for missing resources, 409 for logical conflicts, 400 for validation errors).

---

## Technologies Used

| Category      | Technology                   | Version / Role |
|--------------|--------------------------|----------------|
| **Backend** | Java Development Kit (JDK)| 21 |
| **Framework**| Spring Boot              | 3.4.1 |
| **Database** | PostgreSQL               | Relational Data Storage |
| **ORM** | Spring Data JPA / Hibernate| Database mapping and queries |
| **Data Tools**| Lombok                   | Boilerplate reduction (@Data, @Builder) |
| **Validation**| Jakarta Validation       | Input constraints and DTO validation |

---

## Getting Started

Follow these steps to get the application up and running locally.

### 1. Prerequisites

- **Java Development Kit (JDK):** Version 21
- **PostgreSQL:** Installed and running locally
- **Database Setup:** Create a database named `santa_workshop` in your PostgreSQL instance.

### 2. Database Configuration

Update the `src/main/resources/application.properties` file with your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/santa_workshop
spring.datasource.username=your_postgres_user
spring.datasource.password=your_postgres_password
spring.jpa.hibernate.ddl-auto=update

---
