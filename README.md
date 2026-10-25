# Java & Spring Framework Laboratory (CS3603-1)

A complete, beginner-friendly guide to Java Servlets, JavaServer Pages (JSP), JDBC, and Spring Boot (MVC & RESTful APIs). This repository contains cleaned, simplified, and well-structured source code for all lab experiments along with step-by-step instructions on setting up, building, and running them in **Eclipse IDE**.

---

## 📋 Table of Contents
1. [Prerequisites & Environment Setup](#prerequisites--environment-setup)
2. [Repository Structure](#repository-structure)
3. [Experiment 1a: Simple Servlet (GET & POST Handling)](#experiment-1a-simple-servlet-get--post-handling)
4. [Experiment 1b: SessionServlet (Session Tracking & Authentication)](#experiment-1b-sessionservlet-session-tracking--authentication)
5. [Experiment 2a: Employee Info Processing using JSP](#experiment-2a-employee-info-processing-using-jsp)
6. [Experiment 2b: Server-Side Validation in JSP](#experiment-2b-server-side-validation-in-jsp)
7. [Experiment 3: JSP + JDBC Student CRUD Application](#experiment-3-jsp--jdbc-student-crud-application)
8. [Experiment 4: Spring Boot + Thymeleaf CRUD Web Application](#experiment-4-spring-boot--thymeleaf-crud-web-application)
9. [Experiment 5: Spring Boot RESTful API with MySQL & Error Handling](#experiment-5-spring-boot-restful-api-with-mysql--error-handling)
10. [MySQL Database Setup Scripts](#mysql-database-setup-scripts)
11. [Troubleshooting Common Issues](#troubleshooting-common-issues)

---

## 🛠 Prerequisites & Environment Setup

To run all experiments smoothly, install the following software:

| Tool / Software | Recommended Version | Purpose |
|---|---|---|
| **JDK (Java Development Kit)** | JDK 17 (or JDK 11 / 21) | Compiling and executing Java code |
| **Eclipse IDE** | Eclipse IDE for Enterprise Java and Web Developers | Web & Spring project development |
| **Apache Tomcat** | Tomcat 9.0 (for `javax.servlet`) or Tomcat 10.1+ (for `jakarta.servlet`) | Servlet & JSP container |
| **MySQL Server** | MySQL Community Server 8.0+ | Database for experiments 3, 4, and 5 |
| **MySQL Workbench / CLI** | Any | Managing schemas and querying tables |
| **Postman / curl** | Latest | Testing REST endpoints (Experiment 5) |

---

## 📂 Repository Structure

```text
├── Experiment-1a/          # SimpleServlet (GET & POST handling)
│   └── SimpleServlet.java
├── Experiment-1b/          # SessionServlet (Session tracking, Login & Logout)
│   └── SessionServlet.java
├── Experiment-2a/          # Employee Info using JSP & scriptlets
│   ├── employeeForm.jsp
│   └── employeeResult.jsp
├── Experiment-2b/          # Employee Info with Server-side Regex Validation
│   ├── employeeForm.jsp
│   └── employeeResult.jsp
├── Experiment-3/           # JSP + JDBC CRUD Application
│   ├── DBConnection.java
│   ├── Student.java
│   ├── StudentDAO.java
│   ├── addStudent.jsp
│   ├── viewStudents.jsp
│   ├── updateStudent.jsp
│   ├── deleteStudent.jsp
│   └── schema.sql
├── Experiment-4/           # Spring Boot + Thymeleaf CRUD
│   ├── Staff.java
│   ├── StaffRepository.java
│   ├── StaffManager.java
│   ├── AppController.java
│   ├── application.properties
│   └── templates/
│       ├── index.html
│       ├── create_book_form.html
│       └── edit_form.html
├── Experiment-5/           # Spring Boot RESTful API (CRUD + JSON + Error Handling)
│   ├── Staff.java
│   ├── StaffRepository.java
│   ├── StaffManager.java
│   ├── AppController.java
│   └── application.properties
└── README.md
```

---

## Experiment 1a: Simple Servlet (GET & POST Handling)

### Objective
Create an `HttpServlet` that:
- Handles **HTTP GET**: Delivers an HTML form with input fields for `name` and `password`.
- Handles **HTTP POST**: Reads the parameters sent by the form and outputs an HTML response showing the entered values.

### Required Packages / Libraries
- `javax.servlet.*` (`javax.servlet-api` 4.0+) or `jakarta.servlet.*` (Tomcat 10+)
- `javax.servlet.http.*`
- `javax.servlet.annotation.WebServlet`
- `java.io.*`

### How to Create & Run in Eclipse
1. **Create Project**:
   - Open Eclipse IDE for Enterprise Java.
   - Go to `File -> New -> Dynamic Web Project`.
   - Name the project: `ServletDemo`.
   - Target runtime: Select **Apache Tomcat v9.0** (or configure your installed Tomcat server).
   - Click `Next -> Next -> Check "Generate web.xml deployment descriptor" -> Finish`.
2. **Add Servlet Class**:
   - Expand `ServletDemo -> Java Resources -> src` (or `src/main/java`).
   - Right-click `src` -> `New -> Class`.
   - Package: `com.example`
   - Name: `SimpleServlet`
   - Superclass: `javax.servlet.http.HttpServlet`
   - Copy the code from [`Experiment-1a/SimpleServlet.java`](./Experiment-1a/SimpleServlet.java).
3. **Run on Server**:
   - Right-click `SimpleServlet.java` -> `Run As -> Run on Server`.
   - Select your configured Apache Tomcat server -> Click `Finish`.
   - URL: `http://localhost:8080/ServletDemo/SimpleServlet`
4. **Testing**:
   - The browser opens showing a form: enter **Name** and **Password** and click **Submit**.
   - The servlet handles the POST request and renders the submitted data.

---

## Experiment 1b: SessionServlet (Session Tracking & Authentication)

### Objective
Demonstrate session management in Java Servlets using `HttpSession`:
- Unauthenticated users are shown a login form.
- Valid credentials (`admin` / `password`) initiate a session stored on the server.
- The session preserves state across requests and displays session metadata: Session ID, Creation Time, and Last Access Time.
- User can log out, which invalidates the session and redirects to the login screen.

### Required Packages / Libraries
- `javax.servlet.http.HttpSession`
- `javax.servlet.http.HttpServletRequest`
- `javax.servlet.http.HttpServletResponse`
- `javax.servlet.annotation.WebServlet`
- `java.util.Date`

### How to Create & Run in Eclipse
1. In your existing or new Dynamic Web Project (`ServletDemo`):
2. Right-click package `com.example` in `src` -> `New -> Class` -> Name: `SessionServlet`.
3. Paste code from [`Experiment-1b/SessionServlet.java`](./Experiment-1b/SessionServlet.java).
4. Right-click `SessionServlet.java` -> `Run As -> Run on Server`.
5. Open URL: `http://localhost:8080/ServletDemo/SessionServlet`
6. **Testing**:
   - Enter Username: `admin`, Password: `password` -> Click `Login`.
   - Notice the greeting, Session ID, creation timestamp, and last accessed timestamp.
   - Click `Logout` -> The session is invalidated via `session.invalidate()` and redirected back to the login page.

---

## Experiment 2a: Employee Info Processing using JSP

### Objective
Collect employee details using an HTML form in a JSP page (`employeeForm.jsp`), submit them via `POST` to `employeeResult.jsp`, extract values with `request.getParameter()`, compute derived fields (e.g. Birth Year from Age using `Calendar`), and display them in a table.

### Required Packages / Libraries
- `java.util.Calendar`
- JSP Standard APIs (bundled with Apache Tomcat)

### How to Create & Run in Eclipse
1. Go to `File -> New -> Dynamic Web Project` -> Name: `JSPDemo`.
2. Target Runtime: **Apache Tomcat v9.0**.
3. Under `WebContent` (or `src/main/webapp` in Maven):
   - Right-click -> `New -> JSP File` -> Name: `employeeForm.jsp`. Paste code from [`Experiment-2a/employeeForm.jsp`](./Experiment-2a/employeeForm.jsp).
   - Right-click -> `New -> JSP File` -> Name: `employeeResult.jsp`. Paste code from [`Experiment-2a/employeeResult.jsp`](./Experiment-2a/employeeResult.jsp).
4. Right-click `employeeForm.jsp` -> `Run As -> Run on Server`.
5. URL: `http://localhost:8080/JSPDemo/employeeForm.jsp`
6. Fill in the Employee ID, Name, Age, Department, and Email -> Click `Submit`.

---

## Experiment 2b: Server-Side Validation in JSP

### Objective
Enhance employee input validation on the server side in `employeeResult.jsp`:
- **Employee ID**: Must not be empty or whitespace.
- **Name**: Only letters and spaces allowed (`^[a-zA-Z\s]+$`).
- **Age**: Numeric integer between 18 and 65.
- **Department**: Selected from allowed values (`HR`, `Finance`, `IT`, `Sales`).
- **Email**: Conforms to standard email pattern.
- If valid, renders the formatted employee details table; otherwise, prints descriptive field-level error messages with a link back to the form.

### Required Packages / Libraries
- `java.util.regex.*`
- `java.util.Calendar`

### How to Create & Run in Eclipse
1. In project `JSPDemo` under `WebContent` (or `src/main/webapp`):
   - Create `employeeForm.jsp` from [`Experiment-2b/employeeForm.jsp`](./Experiment-2b/employeeForm.jsp).
   - Create `employeeResult.jsp` from [`Experiment-2b/employeeResult.jsp`](./Experiment-2b/employeeResult.jsp).
2. Right-click `employeeForm.jsp` -> `Run As -> Run on Server`.
3. Try invalid inputs (e.g., negative age, letters in age, numbers in name, invalid email) to verify validation messages.

---

## Experiment 3: JSP + JDBC Student CRUD Application

### Objective
Integrate Java Database Connectivity (JDBC) with JSP to perform full **CRUD** (Create, Read, Update, Delete) operations on a MySQL `students` table.

### Database Setup
Execute in MySQL Workbench or terminal:
```sql
CREATE DATABASE IF NOT EXISTS studentDB;
USE studentDB;

CREATE TABLE IF NOT EXISTS students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    age INT
);
```

### Required Packages / Libraries
- `java.sql.*` (`Connection`, `DriverManager`, `PreparedStatement`, `Statement`, `ResultSet`, `SQLException`)
- `java.util.List`, `java.util.ArrayList`
- **MySQL Connector/J JAR** (`mysql-connector-j-8.x.jar` or `mysql-connector-java-5.1.x.jar`)
  - Copy this JAR into `WebContent/WEB-INF/lib/` or add to project Build Path.

### How to Create & Run in Eclipse
1. Create a Dynamic Web Project named `StudentCRUD`.
2. Copy `mysql-connector-j-8.x.jar` into `WebContent/WEB-INF/lib/` (or `src/main/webapp/WEB-INF/lib/`).
3. Under `src` (or `src/main/java`), create package `com.studentcrud`:
   - `DBConnection.java` -> Database connection helper. **Note:** Update your MySQL username and password!
   - `Student.java` -> Model POJO.
   - `StudentDAO.java` -> Data Access Object with SQL queries (`INSERT`, `SELECT`, `UPDATE`, `DELETE`).
4. Under `WebContent` (or `src/main/webapp`), create the JSP views:
   - `addStudent.jsp`: Form to insert records.
   - `viewStudents.jsp`: Displays records in a table with Edit and Delete links.
   - `updateStudent.jsp`: Form pre-populated with existing record data to update.
   - `deleteStudent.jsp`: Deletes record based on query param `rollNo` and redirects.
5. Right-click `viewStudents.jsp` -> `Run As -> Run on Server`.
6. URL: `http://localhost:8080/StudentCRUD/viewStudents.jsp`

---

## Experiment 4: Spring Boot + Thymeleaf CRUD Web Application

### Objective
Build a web application using **Spring Boot**, **Spring MVC**, **Spring Data JPA**, **Hibernate**, and **Thymeleaf** templates to perform CRUD operations on a `staff` / book database entity.

### Required Packages / Dependencies (`pom.xml`)
If using Maven, add these starters:
```xml
<dependencies>
    <!-- Spring Boot Web Starter (Spring MVC + Embedded Tomcat) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Thymeleaf Template Engine -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Spring Data JPA + Hibernate -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Database Setup
```sql
CREATE DATABASE IF NOT EXISTS staffDB;
```
*(Hibernate automatically creates or updates the table schema when `spring.jpa.hibernate.ddl-auto=update`).*

### How to Create & Run in Eclipse
1. **Create Project**:
   - Go to `File -> New -> Spring Starter Project` (requires Spring Tools 4 plugin in Eclipse, or create at [start.spring.io](https://start.spring.io)).
   - Dependencies to choose: **Spring Web**, **Thymeleaf**, **Spring Data JPA**, **MySQL Driver**.
2. **Add Source Files**:
   - In package `com.example.demo`:
     - `Staff.java` (Entity)
     - `StaffRepository.java` (JpaRepository)
     - `StaffManager.java` (Service)
     - `AppController.java` (Controller)
3. **Add HTML Templates**:
   - In `src/main/resources/templates/`:
     - `index.html` (Staff listing)
     - `create_book_form.html` (Creation form)
     - `edit_form.html` (Edit form)
4. **Configure Properties**:
   - In `src/main/resources/application.properties`, configure MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/staffDB?createDatabaseIfNotExist=true
     spring.datasource.username=root
     spring.datasource.password=password
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```
5. **Run the Application**:
   - Right-click the root Spring Boot Application class -> `Run As -> Spring Boot App` (or `Java Application`).
   - Open browser: `http://localhost:8080/`

---

## Experiment 5: Spring Boot RESTful API with MySQL & Error Handling

### Objective
Create a RESTful API service supporting CRUD operations with:
- JSON request and response payloads.
- HTTP methods: `GET`, `POST`, `PUT`, `DELETE`.
- Proper HTTP response codes (`200 OK`, `201 CREATED`, `404 NOT FOUND`).
- Robust error handling for non-existent records.

### Required Packages / Dependencies (`pom.xml`)
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `com.mysql:mysql-connector-j`

### Endpoints Overview
| HTTP Method | Endpoint | Description | Status Code |
|---|---|---|---|
| `GET` | `/api/staff` | Fetch all records | `200 OK` |
| `GET` | `/api/staff/{id}` | Fetch record by ID | `200 OK` / `404 NOT FOUND` |
| `POST` | `/api/staff` | Create a new record | `201 CREATED` |
| `PUT` | `/api/staff` | Update an existing record | `200 OK` / `404 NOT FOUND` |
| `DELETE` | `/api/staff/{id}` | Delete a record by ID | `200 OK` / `404 NOT FOUND` |

### How to Create & Run in Eclipse
1. Create a Spring Starter Project (or reuse the existing one from Experiment 4).
2. Include:
   - `Staff.java`
   - `StaffRepository.java`
   - `StaffManager.java`
   - `AppController.java` (`@RestController`)
   - `application.properties`
3. Run as `Spring Boot App`.

### Testing with `curl` or Postman

#### 1. Create a Record (POST)
```bash
curl -X POST http://localhost:8080/api/staff \
  -H "Content-Type: application/json" \
  -d '{"name": "Alice Johnson", "age": 28, "email": "alice@example.com"}'
```

#### 2. Get All Records (GET)
```bash
curl -X GET http://localhost:8080/api/staff
```

#### 3. Get Record by ID (GET)
```bash
curl -X GET http://localhost:8080/api/staff/1
```

#### 4. Update a Record (PUT)
```bash
curl -X PUT http://localhost:8080/api/staff \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "Alice J.", "age": 29, "email": "alice.j@example.com"}'
```

#### 5. Delete a Record (DELETE)
```bash
curl -X DELETE http://localhost:8080/api/staff/1
```

---

## 🗄 MySQL Database Setup Scripts

To run all database-backed experiments, run these SQL statements in MySQL:

```sql
-- For Experiment 3
CREATE DATABASE IF NOT EXISTS studentDB;
USE studentDB;

CREATE TABLE IF NOT EXISTS students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    age INT
);

-- For Experiments 4 & 5
CREATE DATABASE IF NOT EXISTS staffDB;
```

---

## 🔧 Troubleshooting Common Issues

1. **Port 8080 already in use**:
   - In Spring Boot: Add `server.port=8081` to `application.properties`.
   - In Tomcat: Double-click Tomcat Server in Eclipse -> Change HTTP Port from `8080` to `8085` -> Save and restart.

2. **`java.lang.ClassNotFoundException: com.mysql.jdbc.Driver`**:
   - For MySQL 8+, use driver name `com.mysql.cj.jdbc.Driver`.
   - Ensure the MySQL Connector `.jar` file is placed in `WEB-INF/lib` (for JSP/Servlet) or declared in `pom.xml` (for Spring Boot).

3. **`javax.servlet.*` vs `jakarta.servlet.*` mismatch**:
   - If using **Tomcat 9**, use `javax.servlet.*`.
   - If using **Tomcat 10+**, change imports to `jakarta.servlet.*`.

4. **MySQL Access Denied**:
   - Check the username and password in `DBConnection.java` or `application.properties`.
   - Ensure your local MySQL server service is started and running.

---
*Created for CSE Java & Spring Framework Laboratory (CS3603-1).*
