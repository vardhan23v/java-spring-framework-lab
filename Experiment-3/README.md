# Experiment 3: JSP + JDBC Student CRUD Application

## 🎯 Aim & Objective
Develop a 3-tier web application integrating **JavaServer Pages (JSP)** with **Java Database Connectivity (JDBC)** to perform full **CRUD** (Create, Read, Update, Delete) operations on a MySQL `students` table:
- **Create:** Insert new student records (roll number, name, email, age).
- **Read:** Query and list all records in a table with Edit and Delete options.
- **Update:** Pre-fill existing record data into an editable form and persist modifications.
- **Delete:** Remove student records by roll number.
- Follow the **DAO (Data Access Object)** pattern to keep SQL queries separated from JSP views.

---

## 📦 Required Packages & Dependencies

| Package | Class / Interface | Purpose |
|---|---|---|
| `java.sql` | `Connection`, `DriverManager` | Establishes and manages the connection to MySQL |
| `java.sql` | `PreparedStatement`, `Statement` | Precompiled parameterized SQL statements preventing SQL injection |
| `java.sql` | `ResultSet`, `SQLException` | Tabular query results and SQL exception handling |
| `java.util` | `List`, `ArrayList` | Collections for passing records between DAO and JSP |
| **External JAR** | `mysql-connector-j-8.3.0.jar` (in repo root) | JDBC Driver JAR for MySQL communication |

---

## 🗄 Database Setup

Run the following SQL commands in MySQL Workbench or terminal before running the application:

```sql
CREATE DATABASE IF NOT EXISTS studentDB;
USE studentDB;

CREATE TABLE IF NOT EXISTS students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

-- Optional sample records:
INSERT INTO students (roll_no, name, email, age) VALUES
(101, 'Alice Johnson', 'alice@example.com', 20),
(102, 'Bob Smith', 'bob@example.com', 21);
```

---

## 🛠 Step-by-Step Eclipse Setup & File Creation

### 1. Create Dynamic Web Project
1. Go to **File -> New -> Dynamic Web Project**.
2. **Project Name:** `StudentCRUD`
3. **Target Runtime:** Select **Apache Tomcat v9.0** -> Click **Finish**.

### 2. Add MySQL Connector JAR
1. Use the bundled `mysql-connector-j-8.3.0.jar` located directly in the root folder of this repository.
2. In Eclipse Project Explorer, copy and paste `mysql-connector-j-8.3.0.jar` into:
   ```text
   StudentCRUD -> WebContent -> WEB-INF -> lib
   ```
   *(Note: Putting the JAR inside `WebContent/WEB-INF/lib` is essential for Tomcat runtime; it will automatically be included in Eclipse's Web App Libraries).*
3. Verify: Right-click project -> **Build Path -> Configure Build Path...** -> Ensure the JAR is present under **Libraries**.

### 3. Create Java Classes in `com.studentcrud`
1. Expand `StudentCRUD -> Java Resources -> src`.
2. Right-click `src` -> **New -> Package** -> Name: `com.studentcrud`.
3. In this package, create:
   - `DBConnection.java`: Copy from [`DBConnection.java`](./DBConnection.java). *Update MySQL username/password if needed!*
   - `Student.java`: Copy from [`Student.java`](./Student.java).
   - `StudentDAO.java`: Copy from [`StudentDAO.java`](./StudentDAO.java).

### 4. Create JSP Views in `WebContent`
1. Under `StudentCRUD -> WebContent`, create the following JSP files:
   - `viewStudents.jsp`: Copy from [`viewStudents.jsp`](./viewStudents.jsp).
   - `addStudent.jsp`: Copy from [`addStudent.jsp`](./addStudent.jsp).
   - `updateStudent.jsp`: Copy from [`updateStudent.jsp`](./updateStudent.jsp).
   - `deleteStudent.jsp`: Copy from [`deleteStudent.jsp`](./deleteStudent.jsp).
2. Save all files.

---

## 🚀 How to Run & Execute in Eclipse

1. Make sure your local MySQL service is running.
2. In Project Explorer, right-click `viewStudents.jsp`.
3. Select **Run As -> Run on Server** -> Select Tomcat -> Click **Finish**.
4. Browser URL:
   ```text
   http://localhost:8080/StudentCRUD/viewStudents.jsp
   ```
5. **Testing CRUD Operations:**
   - **Read:** Existing records are displayed in the table.
   - **Create:** Click `+ Add New Student`, enter details, and submit. The table refreshes with the new row.
   - **Update:** Click `Edit` next to any record, edit fields, and submit. The updated row is visible.
   - **Delete:** Click `Delete` next to a record, confirm, and verify removal from the table and database.

---

## 🔍 Code Walkthrough

1. **`PreparedStatement` with Parameter Binding:**
   ```java
   String sql = "INSERT INTO students (roll_no, name, email, age) VALUES (?, ?, ?, ?)";
   try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
       ps.setInt(1, student.getRollNo());
       ps.setString(2, student.getName());
       ps.setString(3, student.getEmail());
       ps.setInt(4, student.getAge());
       ps.executeUpdate();
   }
   ```
   Values passed via `?` placeholders are escaped and typed, preventing SQL injection attacks.

2. **Try-with-Resources:**
   Automatically closes the database connection, statement, and result set, preventing connection pool exhaustion.

---

## 🧪 Expected Output

```text
Student Records
+ Add New Student

+---------+---------------+-------------------+-----+---------------+
| Roll No | Name          | Email             | Age | Actions       |
+---------+---------------+-------------------+-----+---------------+
| 101     | Alice Johnson | alice@example.com | 20  | Edit | Delete |
| 102     | Bob Smith     | bob@example.com   | 21  | Edit | Delete |
+---------+---------------+-------------------+-----+---------------+
```
