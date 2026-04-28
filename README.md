# 📋 Tasks Application - Hexagonal Architecture
A **task management** application built with **Spring Boot 3** and **Hexagonal Architecture**, implementing clean architecture best practices and a completely decoupled design.
## 📖 Description
This is a REST API application that allows you to create, read, update, and delete tasks. It implements **Hexagonal Architecture** (also known as "Ports and Adapters") to achieve clean, testeable code that is independent of external frameworks.
## 🏗️ Hexagonal Architecture
Hexagonal architecture divides the application into three main layers:
### 1. **Domain** (Core - Business Domain)
```
domain/
├── models/          # Pure business entities (Task, AdditionalTaskInfo)
└── ports/           # Interfaces that define contracts
    ├── in/          # Use cases (input ports)
    └── out/         # Persistence adapters (output ports)
```
- **Independent** of any framework
- Contains **pure business logic**
- Defines contracts through interfaces
### 2. **Application** (Use Cases)
```
application/
├── services/        # Use case orchestrators
└── usecases/        # Use case implementations
```
- Orchestrates **business logic**
- Does not depend on external frameworks
- Implements ports defined in domain
### 3. **Infrastructure** (Adapters)
```
infrastructure/
├── adapters/        # Adapters for external services
├── config/          # Spring configuration
├── controllers/     # REST controllers
├── entities/        # JPA entities
└── repositories/    # Persistence adapters
```
- Integration with **Spring Boot**
- Database access
- REST endpoint exposure
## 📁 Project Structure
```
tasks/
├── src/
│   ├── main/
│   │   ├── java/com/hexagonal/tasks/
│   │   │   ├── TasksApplication.java          # Entry point
│   │   │   ├── domain/
│   │   │   │   ├── models/
│   │   │   │   │   ├── Task.java
│   │   │   │   │   └── AdditionalTaskInfo.java
│   │   │   │   └── ports/
│   │   │   │       ├── in/                    # Use cases (input ports)
│   │   │   │       │   ├── CreateTaskUseCase.java
│   │   │   │       │   ├── DeleteTaskUseCase.java
│   │   │   │       │   ├── GetAdditionalTaskInfoUseCase.java
│   │   │   │       │   ├── RetrieveTaskUseCase.java
│   │   │   │       │   └── UpdateTaskUseCase.java
│   │   │   │       └── out/                   # Output ports
│   │   │   │           └── TaskRepositoryPort.java
│   │   │   ├── application/
│   │   │   │   ├── services/
│   │   │   │   │   └── TaskService.java
│   │   │   │   └── usecases/
│   │   │   │       ├── CreateTaskUseCaseImpl.java
│   │   │   │       ├── DeleteTaskUseCaseImpl.java
│   │   │   │       ├── GetAdditionalTaskInfoUseCaseImpl.java
│   │   │   │       ├── RetrieveTaskUseCaseImpl.java
│   │   │   │       └── UpdateTaskUseCaseImpl.java
│   │   │   └── infrastructure/
│   │   │       ├── adapters/
│   │   │       │   └── ExternalServiceAdapter.java
│   │   │       ├── config/
│   │   │       │   └── ApplicationConfig.java
│   │   │       ├── controllers/
│   │   │       │   └── TaskController.java
│   │   │       ├── entities/
│   │   │       │   └── TaskEntity.java
│   │   │       └── repositories/
│   │   │           ├── JpaTaskRepository.java
│   │   │           └── JpaTaskRepositoryAdapter.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/hexagonal/tasks/
│           └── TasksApplicationTests.java
├── pom.xml                                    # Dependencies
├── mvnw                                       # Maven Wrapper
├── mvnw.cmd                                   # Maven Wrapper (Windows)
└── README.md                                  # This file
```
## 🚀 Prerequisites
- **Java 21** or higher
- **Maven 3.6+** (or use included mvnw)
- **MySQL 8.0+**
- **Git** (optional)
## 📦 Installation
### 1. Clone the repository
```bash
git clone https://github.com/your-username/tasks.git
cd tasks
```
### 2. Create the MySQL database
```sql
CREATE DATABASE tasks_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
### 3. Configure the database connection
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tasks_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
```
### 4. Build and run
```bash
# Build the project
./mvnw clean package -DskipTests
# Run the application
./mvnw spring-boot:run
```
Or run the JAR directly:
```bash
java -jar target/tasks-0.0.1-SNAPSHOT.jar
```
The application will be available at `http://localhost:8080`
## 📡 REST API Endpoints
### Base URL: `/api/tasks`
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/tasks` | Create a new task |
| `GET` | `/api/tasks` | Get all tasks |
| `GET` | `/api/tasks/{id}` | Get a task by ID |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `GET` | `/api/tasks/{id}/additionalInfo` | Get additional task information |
### Usage Examples
#### 1. Create a task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Task List",
    "description": "Create an application using hexagonal architecture",
    "completed": false
  }'
```
**Response:**
```json
{
  "id": 1,
  "title": "Task List",
  "description": "Create an application using hexagonal architecture",
  "creationDate": "2026-04-27T14:30:45.123456",
  "deletedAt": null,
  "completed": false
}
```
#### 2. Get all tasks
```bash
curl -X GET http://localhost:8080/api/tasks
```
#### 3. Get a task by ID
```bash
curl -X GET http://localhost:8080/api/tasks/1
```
#### 4. Update a task
```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Task List",
    "description": "Modified description",
    "completed": true
  }'
```
#### 5. Delete a task
```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```
#### 6. Get additional information
```bash
curl -X GET http://localhost:8080/api/tasks/1/additionalInfo
```
## 🗄️ Database Structure
### Table: `task_entity`
| Field | Type | Description |
|-------|------|-------------|
| `id` | BIGINT | Unique identifier (PK, AUTO_INCREMENT) |
| `title` | VARCHAR(255) | Task title |
| `description` | LONGTEXT | Detailed description |
| `creation_date` | DATETIME | Creation date (assigned automatically) |
| `deleted_at` | DATETIME | Deletion date (soft delete) |
| `completed` | BOOLEAN | Completion status |
## 🛠️ Technologies Used
- **Java 21** - Programming language
- **Spring Boot 3.5.14** - Web framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM
- **MySQL 8** - Relational database
- **Maven** - Dependency manager and build tool
- **Jakarta Persistence** - Persistence specification
## 📋 Main Dependencies
```xml
<!-- Spring Boot Web Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<!-- MySQL Connector -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
<!-- Spring Boot Test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
## 💡 Implemented Features
✅ **Complete CRUD** - Create, read, update, and delete tasks
✅ **Hexagonal Architecture** - Clear separation of concerns
✅ **Independent Use Cases** - Each use case is a separate class
✅ **Ports and Adapters** - Infrastructure decoupling
✅ **JPA/Hibernate Persistence** - Database access
✅ **REST API** - RESTful endpoints
✅ **Dependency Injection** - Automatic configuration via Spring
✅ **Null Handling Validation** - Automatic creationDate control
## 🔧 Additional Configuration
### Enable SQL logs (already included)
```properties
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
```
### DDL Auto (automatic schema updates)
```properties
spring.jpa.hibernate.ddl-auto=update
```
Available options: `create-drop`, `create`, `update`, `validate`, `none`
## 📝 Important Notes
- **Automatic CreationDate**: When creating a task without specifying `creationDate`, it is automatically assigned the current server date and time.
- **Soft Delete**: The `deletedAt` field allows implementing logical deletion (the task is not physically deleted).
- **Decoupling**: The domain has no dependencies on Spring or JPA, allowing you to test business logic without infrastructure.
## 🧪 Testing
```bash
# Run all tests
./mvnw test
# Run with coverage
./mvnw test -Drepository=true
```

**Last updated:** April 2026
