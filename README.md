# 🏥 Hospital Management System (HMS)
## Project Description
**Hospital Management System (HMS)** is a backend-driven web application built using Spring Boot. 
It **manages patients, doctors and appointments** efficiently.
The system follows REST architecture and uses MySQL as the database.

## 🚀 Features
- 👨‍⚕️ Doctor Management (Add, Update, Delete, View)
- 🧑‍🤝‍🧑 Patient Management
- 📅 Appointment Scheduling
- 🔁 CRUD Operations
- 🗄️ Database Relationships
- 🌐 RESTful API Design
- ⚠️ Global Exception Handling
- ✅ Request Validation

## 🛠️ Tech Stack
| Technology          | Purpose              |
| ------------------- | -------------------- |
| ☕ Java 17           | Programming Language |
| 🌱 Spring Boot      | Backend Framework    |
| 🗃️ Spring Data JPA | ORM Layer            |
| 🐬 MySQL            | Database             |
| 🔨 Maven            | Build Tool           |
| 📦 Hibernate        | ORM Implementation   |

## 🏗️ Project Architecture
***The project follows layered architecture***
#### Controller → Service → Repository → Database
- Controller → Handles HTTP requests
- Service → Business logic
- Repository → Database interaction
- Entity → Database mapping

## 🗄️ Database Design

### 📌 Entities
- Patient
- Doctor
- Appointment
- Bill
- BillItem
### 🔗 Relationships
- One Patient → Many Appointments
- One Doctor → Many Appointments
- One Doctor → Many Schedule (Day by day)
## ⚙️ Installation & Setup
1. 1️⃣ Clone the Repository
    - git clone https://github.com/Vivek987099/hms-backend
2. 2️⃣ Configure Database
    - spring.datasource.url=jdbc:mysql://localhost:3306/hms
    - spring.datasource.username=your_username
    - spring.datasource.password=your_password
3. 3️⃣ Run the Application
    - mvn spring-boot:run

## 👨‍💻 Author
**Vivek Arya**






