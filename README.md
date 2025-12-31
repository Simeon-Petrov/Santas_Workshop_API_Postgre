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
```

---

### 3. Build and Run

cd path/to/exam-postgre<br> 
mvn clean install<br> 
mvn spring-boot:run<br>  

#### Navigate to the project root (where `pom.xml` is located):
cd [[[[https://github.com/Simeon-Petrov/Santas-Workshop-API](https://github.com/Simeon-Petrov/Santas_Workshop_API_Postgre/blob/main/pom.xml)]

---

### 4. Access the Application

Once the application starts (typically runs on port **8080**), open your browser or Postman and go to:

[http://localhost:8080/api](http://localhost:8080/api)


| Feature                          | Location          | Description                                                                                            |
| -------------------------------- | ----------------- | ------------------------------------------------------------------------------------------------------ |
| **Database Persistence**         | Repositories      | Uses `JpaRepository` for out-of-the-box CRUD and SQL support with PostgreSQL.                          |
| **Transactional Status Updates** | `DeliveryService` | Uses `@Transactional` to ensure delivery and related gift status updates are applied atomically.       |
| **Conflict Handling**            | `ElfService`      | Validates gift state before assignment and throws `IllegalStateException`, mapped to **409 Conflict**. |
| **Modern Java Stack**            | Project-wide      | Built with **Java 21** and **Lombok** to reduce boilerplate (getters, setters, builders).              |
| **Dynamic Filtering**            | `GiftService`     | Combines database queries with Java Streams for flexible filtering by status and category.             |


## API Documentation

You can find detailed API request examples and test scenarios in the following file:

- [Test Requests (HTTP Client)](test_requests.http)

---

## Author

**Simeon Petrov**
