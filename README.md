# 🎫 Ticket Management System 

---

## 1️⃣ Introduction

This project is a **Ticket Management System backend** built using **Spring Boot**.  
It implements **JWT-based authentication**, **role-based authorization**, and **ticket lifecycle management** using **MySQL**.

The system is designed with **clean architecture** and follows industry best practices.

---

## 2️⃣ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Postman (API testing)

---

## 3️⃣ Roles in the System

### 👤 USER
- Register & login
- Create tickets
- View own tickets
- View comments

### 🧑‍💼 AGENT
- Login using JWT
- View assigned tickets
- Update ticket status
- Add comments

### 👑 ADMIN
- View all tickets
- Assign tickets to agents
- View audit logs
- Add comments

---

## 4️⃣ Ticket Lifecycle

Tickets move through the following stages:

- OPEN
- IN_PROGRESS
- ON_HOLD
- RESOLVED
- CLOSED

---

## 5️⃣ Project Architecture

The project follows **layered architecture**:

src/main/java/com/example/ticketing
│
├── controller # REST controllers
├── service # Business logic
├── repository # JPA repositories
├── entity # JPA entities
├── dto # Data Transfer Objects
├── security # JWT & Security config
├── exception # Global exception handling
│
└── TicketManagementSystemApplication.java


---

## 📡 API Endpoints

### 🔑 Authentication
- POST `/auth/register` – Register new user
- POST `/auth/login` – Login and receive JWT

### 🎫 Tickets
- POST `/api/tickets` – Create ticket (USER)
- PUT `/api/tickets/{id}/status` – Update status (AGENT)
- PUT `/api/tickets/{id}/assign/{agentId}` – Assign ticket (ADMIN)

### 💬 Comments
- POST `/api/tickets/{ticketId}/comments` – Add comment (AGENT, ADMIN)

### 🧾 Audit
- GET `/api/audit/tickets/{ticketId}` – View audit logs (ADMIN)

---

## ⚙️ Database Configuration
Configure MySQL in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticket_db
spring.datasource.username=root
spring.datasource.password=****
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```
▶️ How to Run the Project

Clone or download the project

Configure MySQL database

Update application.properties

Run:

mvn spring-boot:run


Server starts at:

http://localhost:8080

🧪 Testing

APIs tested using Postman

JWT token required in header:

Authorization: Bearer <JWT_TOKEN>

