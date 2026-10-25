# Experiment 5: Spring Boot RESTful API with MySQL & Error Handling

## 🎯 Aim & Objective
Develop a production-grade **RESTful API** web service using **Spring Boot**:
- Build CRUD endpoints producing and consuming **JSON** payloads.
- Implement standard HTTP methods:
  - `GET /api/staff` -> Retrieve all records (`200 OK`).
  - `GET /api/staff/{id}` -> Retrieve record by ID (`200 OK` or `404 NOT FOUND`).
  - `POST /api/staff` -> Create new record (`201 CREATED`).
  - `PUT /api/staff` -> Update existing record (`200 OK` or `404 NOT FOUND`).
  - `DELETE /api/staff/{id}` -> Delete record by ID (`200 OK` or `404 NOT FOUND`).
- Encapsulate robust error handling using `ResponseEntity` and `NoSuchElementException`.
- Demonstrate API functionality using `curl` and Postman.

---

## 📦 Required Dependencies (`pom.xml`)

```xml
<dependencies>
    <!-- Spring Boot Web Starter (REST controllers, Jackson JSON, Tomcat) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA & Hibernate ORM -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL JDBC Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

---

## 🗄 Database Setup

```sql
CREATE DATABASE IF NOT EXISTS staffDB;
```
*(Hibernate automatically manages table creation when `spring.jpa.hibernate.ddl-auto=update`).*

---

## 🛠 Step-by-Step Eclipse Setup & File Creation

### 1. Project Setup
- In Eclipse, create a new **Spring Starter Project** named `SpringBootRESTApi` (or reuse the existing Spring Boot project).
- Select dependencies: `Spring Web`, `Spring Data JPA`, and `MySQL Driver`.

### 2. Add Java Source Files
Under `src/main/java/com/example/demo/`:
- `Staff.java`: Entity class. Copy from [`Staff.java`](./Staff.java).
- `StaffRepository.java`: JPA repository interface. Copy from [`StaffRepository.java`](./StaffRepository.java).
- `StaffManager.java`: Service class with `exists(Long id)` helper. Copy from [`StaffManager.java`](./StaffManager.java).
- `AppController.java`: `@RestController` implementing RESTful endpoints. Copy from [`AppController.java`](./AppController.java).

### 3. Configure `application.properties`
In `src/main/resources/application.properties`:
```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/staffDB?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🚀 How to Run & Execute in Eclipse

1. Ensure MySQL Server is running locally.
2. In Eclipse, right-click the Application main class -> **Run As -> Spring Boot App**.
3. The server starts on port `8080`.

---

## 📡 API Testing Walkthrough (cURL & Postman)

### 1. Create a Record (`POST`)
- **Endpoint:** `POST http://localhost:8080/api/staff`
- **Headers:** `Content-Type: application/json`
- **cURL Command:**
  ```bash
  curl -i -X POST http://localhost:8080/api/staff \
    -H "Content-Type: application/json" \
    -d '{"name": "Sarah Connor", "age": 29, "email": "sarah@resistance.org"}'
  ```
- **Response:**
  ```text
  HTTP/1.1 201 Created
  Record created successfully
  ```

---

### 2. Retrieve All Records (`GET`)
- **Endpoint:** `GET http://localhost:8080/api/staff`
- **cURL Command:**
  ```bash
  curl -i -X GET http://localhost:8080/api/staff
  ```
- **Response (`200 OK`):**
  ```json
  [
    {
      "id": 1,
      "name": "Sarah Connor",
      "age": 29,
      "email": "sarah@resistance.org"
    }
  ]
  ```

---

### 3. Retrieve Record by ID (`GET`)
- **Endpoint:** `GET http://localhost:8080/api/staff/1`
- **cURL Command:**
  ```bash
  curl -i -X GET http://localhost:8080/api/staff/1
  ```
- **Response (`200 OK`):**
  ```json
  {
    "id": 1,
    "name": "Sarah Connor",
    "age": 29,
    "email": "sarah@resistance.org"
  }
  ```
- **Testing 404 Error (Non-existent ID `999`):**
  ```bash
  curl -i -X GET http://localhost:8080/api/staff/999
  ```
  **Response:** `HTTP/1.1 404 Not Found`

---

### 4. Update an Existing Record (`PUT`)
- **Endpoint:** `PUT http://localhost:8080/api/staff`
- **cURL Command:**
  ```bash
  curl -i -X PUT http://localhost:8080/api/staff \
    -H "Content-Type: application/json" \
    -d '{"id": 1, "name": "Sarah Connor", "age": 30, "email": "sarah.c@resistance.org"}'
  ```
- **Response (`200 OK`):**
  ```text
  Record updated successfully
  ```

---

### 5. Delete a Record (`DELETE`)
- **Endpoint:** `DELETE http://localhost:8080/api/staff/1`
- **cURL Command:**
  ```bash
  curl -i -X DELETE http://localhost:8080/api/staff/1
  ```
- **Response (`200 OK`):**
  ```text
  HTTP/1.1 200 OK
  ```

---

## 🔍 Code Walkthrough

1. **`@RestController` vs `@Controller`:**
   `@RestController` eliminates the need to annotate every method with `@ResponseBody`. Return types are automatically converted to JSON strings using Spring's integrated Jackson serializer.
2. **`ResponseEntity<T>`:**
   Allows programmatic control over the HTTP status codes returned to the client (`HttpStatus.OK`, `HttpStatus.CREATED`, `HttpStatus.NOT_FOUND`).
3. **`@RequestBody` & `@PathVariable`:**
   - `@RequestBody` deserializes incoming JSON payloads into Java POJOs.
   - `@PathVariable` extracts URI path variables (e.g., `{id}`).
