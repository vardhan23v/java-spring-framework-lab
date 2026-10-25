# Experiment 4: Spring Boot + Thymeleaf CRUD Web Application

## 🎯 Aim & Objective
Develop an enterprise-style web application using **Spring Boot**, **Spring MVC**, **Spring Data JPA**, **Hibernate**, and **Thymeleaf**:
- Perform complete CRUD operations on a `staff` database entity (`id`, `name`, `age`, `email`).
- Use **Spring Data JPA** (`JpaRepository`) to eliminate boilerplate database access code.
- Implement **Thymeleaf** templates with two-way form data binding (`th:object`, `th:field`, `th:each`).
- Use **Spring MVC** `@Controller` to handle routing (`/`, `/new`, `/save`, `/edit/{id}`, `/delete/{id}`) and flash redirects (`redirect:/`).

---

## 📦 Required Dependencies (`pom.xml`)

Add the following starter dependencies to your Maven project:

```xml
<dependencies>
    <!-- Web Starter: Spring MVC + Embedded Tomcat -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Template Engine: Thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Persistence: Spring Data JPA + Hibernate ORM -->
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

Open MySQL and create the database (Hibernate will automatically generate and update tables):

```sql
CREATE DATABASE IF NOT EXISTS staffDB;
```

---

## 🛠 Step-by-Step Eclipse Setup & File Creation

### 1. Create Spring Starter Project
1. In Eclipse with Spring Tools 4 installed:
   - Go to **File -> New -> Spring Starter Project** (or generate from [start.spring.io](https://start.spring.io)).
   - **Name:** `SpringBootThymeleafCRUD`
   - **Type:** Maven, **Java Version:** 17
   - **Dependencies to select:**
     - `Spring Web`
     - `Thymeleaf`
     - `Spring Data JPA`
     - `MySQL Driver`
   - Click **Finish**.

### 2. Add Java Classes in `com.example.demo`
In `src/main/java/com/example/demo/`:
- `Staff.java`: JPA Entity. Copy from [`Staff.java`](./Staff.java).
- `StaffRepository.java`: JPA Repository interface. Copy from [`StaffRepository.java`](./StaffRepository.java).
- `StaffManager.java`: Service layer. Copy from [`StaffManager.java`](./StaffManager.java).
- `AppController.java`: MVC Controller. Copy from [`AppController.java`](./AppController.java).

### 3. Add Thymeleaf Templates in `src/main/resources/templates`
Under `src/main/resources/templates/`:
- `index.html`: Table view listing all staff. Copy from [`templates/index.html`](./templates/index.html).
- `create_book_form.html`: Form to add a new record. Copy from [`templates/create_book_form.html`](./templates/create_book_form.html).
- `edit_form.html`: Form to edit an existing record. Copy from [`templates/edit_form.html`](./templates/edit_form.html).

### 4. Configure `application.properties`
In `src/main/resources/application.properties`:
```properties
# MySQL Connection
spring.datasource.url=jdbc:mysql://localhost:3306/staffDB?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🚀 How to Run & Execute in Eclipse

1. Ensure MySQL is running on port `3306`.
2. In Project Explorer, find your Spring Boot main application class (e.g. `DemoApplication.java`).
3. Right-click the class -> **Run As -> Spring Boot App** (or *Java Application*).
4. Watch the Eclipse Console:
   - Notice Tomcat initializing on port `8080`.
   - Hibernate auto-creates the `staff` table schema.
5. Open your browser:
   ```text
   http://localhost:8080/
   ```
6. **Testing Features:**
   - Click `+ Create New Staff`, enter details, and hit **Save**.
   - The app redirects back to the home page showing the newly added staff.
   - Click `Edit` to modify the fields and update.
   - Click `Delete` to remove the row.

---

## 🔍 Code Walkthrough

1. **JPA Entity (`@Entity` & `@Id`):**
   ```java
   @Entity
   public class Staff {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
   ```
   Maps the Java class directly to a relational table in MySQL. Primary key generation is delegated to MySQL `AUTO_INCREMENT`.

2. **Spring Data Repository:**
   ```java
   public interface StaffRepository extends JpaRepository<Staff, Long> { }
   ```
   Extending `JpaRepository` automatically provides CRUD methods without needing any implementation class.

3. **Controller & Redirect Pattern:**
   ```java
   @PostMapping("/save")
   public String saveStaff(@ModelAttribute Staff staff) {
       manager.saveStaff(staff);
       return "redirect:/";
   }
   ```
   Uses the **Post/Redirect/Get (PRG)** pattern to eliminate duplicate form submissions on browser page refresh.

---

## 🧪 Expected Output

```text
Staff Management
+ Create New Staff

+----+-------------------+-----+-----------------------+---------------+
| ID | Name              | Age | Email                 | Action        |
+----+-------------------+-----+-----------------------+---------------+
| 1  | Hermione Granger  | 24  | hermione@ministry.gov | Edit | Delete |
+----+-------------------+-----+-----------------------+---------------+
```
