# Java & Spring Framework Lab (CS3603-1): Comprehensive Code, Eclipse Guide & Viva Reference

This repository contains the complete laboratory curriculum for the **Java & Spring Framework Lab (Course Code: CS3603-1)**. Every experiment has been simplified, modernized, and thoroughly documented with:
- **Aim & Concept:** Core objective and design pattern.
- **Required Packages & Dependencies:** Complete listing of Java packages, JAR files, and Maven dependencies.
- **Complete Formatted Source Code:** Clean, standard-compliant Java, JSP, and HTML code.
- **Eclipse IDE Setup & Run Instructions:** Step-by-step walkthroughs for project creation, Tomcat deployment, and Spring Boot execution.
- **Detailed Step-by-Step Explanation:** Component-by-component breakdown of logic, annotations, and request lifecycle.
- **Sample Input & Expected Output:** Browser screenshots, form walkthroughs, terminal commands, and JSON payloads.
- **Viva Questions & Key Takeaways:** High-yield questions with concise answers for lab exams.

---

## Table of Contents
1. [Environment & Software Prerequisites](#environment--software-prerequisites)
2. [Quick Reference Index](#quick-reference-index)
3. [Experiment 1a: Simple Servlet (GET & POST Handling)](#experiment-1a-simple-servlet-get--post-handling)
4. [Experiment 1b: SessionServlet (Session Tracking, Cookies & Authentication)](#experiment-1b-sessionservlet-session-tracking-cookies--authentication)
5. [Experiment 2a: Employee Management using JSP](#experiment-2a-employee-management-using-jsp)
6. [Experiment 2b: Server-Side Form Validation in JSP](#experiment-2b-server-side-form-validation-in-jsp)
7. [Experiment 3: JSP + JDBC Student CRUD Application](#experiment-3-jsp--jdbc-student-crud-application)
8. [Experiment 4: Spring Boot + Thymeleaf CRUD Web Application](#experiment-4-spring-boot--thymeleaf-crud-web-application)
9. [Experiment 5: Spring Boot RESTful API with MySQL & Error Handling](#experiment-5-spring-boot-restful-api-with-mysql--error-handling)
10. [MySQL Database Setup Scripts](#mysql-database-setup-scripts)
11. [Quick Viva Cheat Sheet](#quick-viva-cheat-sheet)
12. [Troubleshooting & Common Errors](#troubleshooting--common-errors)
13. [Servlet & JSP Walkthrough (Eclipse, Tomcat & HTTP 500 Debugging)](./WALKTHROUGH.md)

---

## Environment & Software Prerequisites

Before running the programs, ensure your system has the following components installed and configured:

| Component | Minimum Version | Recommended Version | Download / Installation Source |
|---|---|---|---|
| **JDK (Java Development Kit)** | JDK 11 | **JDK 17 LTS** | [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [Eclipse Temurin](https://adoptium.net/) |
| **Eclipse IDE** | 2022-09+ | **Eclipse IDE for Enterprise Java and Web Developers** | [Eclipse Downloads](https://www.eclipse.org/downloads/packages/) |
| **Apache Tomcat** | Tomcat 9.0 | **Tomcat 9.0.x** (for `javax.servlet`) or **Tomcat 10.1.x** (for `jakarta.servlet`) | [Apache Tomcat](https://tomcat.apache.org/) |
| **MySQL Server** | MySQL 5.7 | **MySQL 8.0+ Community Server** | [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) |
| **MySQL Connector/J** | 8.0.x / 26.x | **mysql-connector-j-26.10.0.jar** | [MySQL Connector](https://dev.mysql.com/downloads/connector/j/) or Maven Central |
| **Spring Tools 4** | Optional plugin | **Spring Tools 4 (STS)** for Eclipse | Eclipse Marketplace |
| **Postman / curl** | Any | Latest | [Postman](https://www.postman.com/downloads/) or Terminal `curl` |

---

## Quick Reference Index

| Exp # | Experiment Title | Tech Stack | Core Concept | Source Code Link |
|:---:|---|---|---|:---:|
| **1a** | Simple Servlet | Java Servlet, HTTP | `doGet()` & `doPost()` Request Dispatching | [Experiment-1a/](./Experiment-1a/) |
| **1b** | Session Tracking Servlet | Java Servlet, Sessions | `HttpSession`, Cookies, State Persistence | [Experiment-1b/](./Experiment-1b/) |
| **2a** | Employee Data Processing | JSP, Scriptlets | Form handling, `Calendar` calculations | [Experiment-2a/](./Experiment-2a/) |
| **2b** | Server-Side Validation | JSP, Regex | Data integrity, Regex sanitization | [Experiment-2b/](./Experiment-2b/) |
| **3** | Student CRUD System | JSP, JDBC, MySQL | 3-Tier Architecture, DAO Pattern, SQL | [Experiment-3/](./Experiment-3/) |
| **4** | Spring Boot MVC CRUD | Spring Boot, Thymeleaf, JPA | MVC Architecture, ORM, Two-way Binding | [Experiment-4/](./Experiment-4/) |
| **5** | Spring Boot RESTful API | Spring Boot, REST, JPA | RESTful Endpoints, JSON, `ResponseEntity` | [Experiment-5/](./Experiment-5/) |

---

## Experiment 1a: Simple Servlet (GET & POST Handling)

### Aim & Concept
Implement an HTTP Servlet that handles both **HTTP GET** and **HTTP POST** requests:
- When invoked via `GET`, respond with an HTML page featuring an interactive form with `name` and `password` fields.
- When the form is submitted via `POST`, retrieve the form input fields using `HttpServletRequest.getParameter()` and output a dynamically constructed HTML page displaying the entered values.

### Required Packages & Dependencies
| Package | Class / Interface | Purpose |
|---|---|---|
| `javax.servlet` | `ServletException` | General servlet lifecycle exception |
| `javax.servlet.annotation` | `@WebServlet` | Declares URL mapping without `web.xml` |
| `javax.servlet.http` | `HttpServlet`, `HttpServletRequest`, `HttpServletResponse` | HTTP protocol support, request reading, and response writing |
| `java.io` | `IOException`, `PrintWriter` | Streaming HTML text to the client browser |

> **Tomcat Note:** If running on **Apache Tomcat 10+**, replace all `javax.servlet.*` imports with `jakarta.servlet.*`.

---

### Source Code

#### `SimpleServlet.java`
```java
package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Experiment 1a - Simple Servlet handling GET and POST requests.
 *
 * GET  → Shows an HTML form (name + password fields).
 * POST → Reads the submitted parameters and displays them.
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ---------- GET: render the form ---------- */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Submit Your Details</h2>");
        out.println("<form method='POST' action='SimpleServlet'>");
        out.println("Name: <input type='text' name='name'><br><br>");
        out.println("Password: <input type='password' name='password'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    /* ---------- POST: display submitted data ---------- */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name     = request.getParameter("name");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Submitted Data</h2>");
        out.println("Name: "     + name     + "<br>");
        out.println("Password: " + password + "<br>");
        out.println("</body></html>");
    }
}
```

---

### How to Create & Run in Eclipse

1. **Create Dynamic Web Project**:
   - Open Eclipse IDE for Enterprise Java.
   - Go to **File -> New -> Dynamic Web Project**.
   - **Project Name:** `ServletDemo`
   - **Target Runtime:** Select **Apache Tomcat v9.0** (Click *New Runtime...* if Tomcat is not configured).
   - **Dynamic web module version:** `4.0` (or `3.1`).
   - Click **Next -> Next -> Check "Generate web.xml deployment descriptor" -> Finish**.

2. **Add Servlet File**:
   - In Project Explorer, expand `ServletDemo -> Java Resources -> src` (or `src/main/java`).
   - Right-click `src` -> **New -> Class**.
   - **Package:** `com.example`
   - **Name:** `SimpleServlet`
   - **Superclass:** `javax.servlet.http.HttpServlet`
   - Replace the default code with `SimpleServlet.java` above.

3. **Deploy & Run on Tomcat Server**:
   - Right-click `SimpleServlet.java` -> **Run As -> Run on Server**.
   - Select **Choose an existing server** -> **Tomcat v9.0 Server** -> Click **Finish**.
   - Eclipse will automatically compile the class, copy it to Tomcat's deployment folder, and launch its internal web browser.

4. **Access in External Browser**:
   ```text
   http://localhost:8080/ServletDemo/SimpleServlet
   ```

---

### Detailed Step-by-Step Explanation

1. **`@WebServlet("/SimpleServlet")` Annotation:**
   - Registers this class as a servlet with the container under the URL pattern `/SimpleServlet`. This eliminates the need for manual XML configuration in `web.xml`.
2. **`doGet()` Method:**
   - Invoked when the client sends an HTTP GET request (e.g. typing the URL into the browser address bar).
   - Sets MIME type to `text/html` using `response.setContentType("text/html")` so the browser parses the response as HTML rather than plain text.
   - Outputs an HTML form whose `action` points back to `SimpleServlet` with `method='POST'`.
3. **`doPost()` Method:**
   - Invoked when the user submits the form.
   - Extracts submitted values using `request.getParameter("name")` and `request.getParameter("password")`.
   - Generates an HTML response echoing back the submitted values.

---

### Sample Input & Expected Output

#### Initial GET Request (`http://localhost:8080/ServletDemo/SimpleServlet`):
```text
+------------------------------------+
| Submit Your Details                |
|                                    |
| Name:     [ Vardhan              ] |
| Password: [ **********           ] |
|                                    |
| [ Submit ]                         |
+------------------------------------+
```

#### After Clicking Submit (POST Response):
```text
+------------------------------------+
| Submitted Data                     |
|                                    |
| Name: Vardhan                      |
| Password: mySecretPassword         |
+------------------------------------+
```

---

### Viva Questions & Key Takeaways

- **Q: What is the difference between `doGet()` and `doPost()`?**
  *A:* `doGet()` handles GET requests where parameters are appended to the URL query string (limited size, visible in browser history). `doPost()` carries parameters inside the HTTP request body (secure for passwords, no URL length limit).
- **Q: What is the role of `PrintWriter`?**
  *A:* `response.getWriter()` returns a character stream (`PrintWriter`) used to send formatted text/HTML directly to the HTTP response body.
- **Q: Why is `serialVersionUID` declared?**
  *A:* `HttpServlet` implements `Serializable`. `serialVersionUID` guarantees that the serialized and deserialized class structures match across different JVM executions.

---

## Experiment 1b: SessionServlet (Session Tracking, Cookies & Authentication)

### Aim & Concept
Demonstrate stateful session tracking in HTTP using `HttpSession` and cookies:
- HTTP is a stateless protocol; session tracking allows the server to remember a user across multiple page requests.
- When accessed without a session, present a login form.
- Upon valid login credentials (`admin` / `password`), initialize a server session via `request.getSession(true)` and store the username as a session attribute.
- In subsequent requests, verify the session; if active, display the username, **Session ID**, **Creation Time**, and **Last Accessed Time**.
- Provide a logout mechanism that calls `session.invalidate()` to purge session data from the server.

### Required Packages & Dependencies
| Package | Class / Interface | Purpose |
|---|---|---|
| `javax.servlet.http` | `HttpSession` | Manages conversation state across multiple HTTP requests |
| `javax.servlet.http` | `HttpServletRequest`, `HttpServletResponse` | Request reading, redirection, session retrieval |
| `java.util` | `Date` | Formats epoch millisecond timestamps into readable dates |

---

### Source Code

#### `SessionServlet.java`
```java
package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Experiment 1b - Session Tracking Servlet.
 *
 * Demonstrates:
 *   • Login form (GET when no session exists)
 *   • Session creation upon valid credentials (POST)
 *   • Welcome page displaying session ID, creation time, last accessed time (GET)
 *   • Logout functionality with session invalidation
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ---------- GET: Show Login Form or Welcome Screen ---------- */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 1. Handle Logout Action
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // Destroy session on server
            }
            response.sendRedirect("SessionServlet"); // Redirect back to login
            return;
        }

        // 2. Inspect Existing Session
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            // ---- Active Session View ----
            String username = (String) session.getAttribute("username");
            out.println("<html><head><title>Welcome</title></head><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("<p><a href='SessionServlet?action=logout'>Logout</a></p>");
            out.println("<h3>Session Information:</h3>");
            out.println("<p><b>Session ID:</b> " + session.getId() + "</p>");
            out.println("<p><b>Creation Time:</b> " + new Date(session.getCreationTime()) + "</p>");
            out.println("<p><b>Last Accessed Time:</b> " + new Date(session.getLastAccessedTime()) + "</p>");
            out.println("</body></html>");
        } else {
            // ---- Unauthenticated View: Login Form ----
            out.println("<html><head><title>Login</title></head><body>");
            out.println("<h2>Login</h2>");
            out.println("<form method='post' action='SessionServlet'>");
            out.println("Username: <input type='text' name='username'><br><br>");
            out.println("Password: <input type='password' name='password'><br><br>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }

    /* ---------- POST: Authenticate Credentials ---------- */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate credentials (hardcoded demo check)
        if ("admin".equals(username) && "password".equals(password)) {
            // Create a new session and bind username
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
        }

        // Redirect back to GET handler
        response.sendRedirect("SessionServlet");
    }
}
```

---

### How to Create & Run in Eclipse

1. In the same project (`ServletDemo`):
2. Right-click package `com.example` -> **New -> Class** -> Name: `SessionServlet`.
3. Paste the code from `SessionServlet.java`.
4. Right-click `SessionServlet.java` -> **Run As -> Run on Server** -> Select Tomcat -> **Finish**.
5. Browser URL:
   ```text
   http://localhost:8080/ServletDemo/SessionServlet
   ```

---

### Detailed Step-by-Step Explanation

1. **`request.getSession(false)` vs `request.getSession(true)`:**
   - `getSession(false)` returns the current session if one exists; otherwise, returns `null` without creating a new session. This is ideal for checking authentication.
   - `getSession(true)` returns the current session, or creates and returns a brand-new session if none exists. Used during successful login.
2. **Session Identification via Cookies:**
   - Under the hood, Tomcat generates a unique session token called `JSESSIONID` and transmits it to the browser as an HTTP response cookie. The browser sends this cookie with every subsequent request.
3. **`session.setAttribute("key", value)` & `getAttribute("key")`:**
   - Binds state objects to the user session on server-side memory.
4. **`session.invalidate()`:**
   - Unbinds all session attributes and destroys the session object on the server. Subsequent requests will be treated as unauthenticated.

---

### Sample Input & Expected Output

#### 1. Login Screen:
```text
+------------------------------------+
| Login                              |
|                                    |
| Username: [ admin            ]     |
| Password: [ ********         ]     |
|                                    |
| [ Login ]                          |
+------------------------------------+
```

#### 2. Welcome Screen (Session Active):
```text
+-------------------------------------------------------------+
| Welcome, admin!                                             |
| Logout                                                      |
|                                                             |
| Session Information:                                        |
| Session ID: 48B1CF73E475A96489311DA457BD3F92                |
| Creation Time: Sun Oct 25 23:25:10 IST 2026                 |
| Last Accessed Time: Sun Oct 25 23:25:12 IST 2026            |
+-------------------------------------------------------------+
```

---

### Viva Questions & Key Takeaways

- **Q: What are the 4 main techniques for session tracking in Java Web applications?**
  *A:* Cookies, `HttpSession` API, URL Rewriting (appending `;jsessionid=...`), and Hidden Form Fields.
- **Q: How does `session.invalidate()` work?**
  *A:* It immediately clears all stored session attributes and marks the session invalid on the server container.
- **Q: What is the default session timeout in Tomcat?**
  *A:* 30 minutes (configurable in `web.xml` using `<session-timeout>30</session-timeout>`).

---

## Experiment 2a: Employee Management using JSP

### Aim & Concept
Develop a JavaServer Pages (JSP) web application to collect and process employee details:
- `employeeForm.jsp`: An HTML form collecting **Employee ID**, **Name**, **Age**, **Department**, and **Email**.
- `employeeResult.jsp`: Processes submitted data using JSP scriptlets (`<% ... %>`) and expressions (`<%= ... %>`):
  - Calculates birth year: `Birth Year = Current Year - Age` using `java.util.Calendar`.
  - Concatenates name and department: `Name (Department)`.
  - Converts email to lowercase.
  - Displays the processed data in a formatted HTML table.

### Required Packages & Dependencies
- `java.util.Calendar`
- Servlet & JSP API (provided by Apache Tomcat)

---

### Source Code

#### `employeeForm.jsp`
```jsp
<!DOCTYPE html>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
    <h2>Employee Information Form</h2>
    <form action="employeeResult.jsp" method="POST">
        <table>
            <tr>
                <td>Employee ID:</td>
                <td><input type="text" name="employeeId" required></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><input type="number" name="age" required></td>
            </tr>
            <tr>
                <td>Department:</td>
                <td><input type="text" name="department" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <input type="submit" value="Submit">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
```

#### `employeeResult.jsp`
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Information</title>
</head>
<body>
    <h2>Employee Information Result</h2>
    <%
        // 1. Retrieve form data
        String employeeId = request.getParameter("employeeId");
        String name       = request.getParameter("name");
        String ageStr     = request.getParameter("age");
        String department = request.getParameter("department");
        String email      = request.getParameter("email");

        // 2. Process data
        int age = Integer.parseInt(ageStr);
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int birthYear = currentYear - age;
        String nameAndDept = name + " (" + department + ")";
        String formattedEmail = email.toLowerCase();
    %>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr><th>Employee ID</th>          <td><%= employeeId %></td></tr>
        <tr><th>Name and Department</th>  <td><%= nameAndDept %></td></tr>
        <tr><th>Birth Year</th>           <td><%= birthYear %></td></tr>
        <tr><th>Formatted Email</th>      <td><%= formattedEmail %></td></tr>
    </table>
</body>
</html>
```

---

### How to Create & Run in Eclipse

1. **Create Project**:
   - Go to **File -> New -> Dynamic Web Project**.
   - Name: `JSPDemo`.
   - Target Runtime: **Apache Tomcat v9.0** -> Click **Finish**.
2. **Add JSP Files**:
   - In Project Explorer, expand `JSPDemo -> WebContent` (or `src/main/webapp`).
   - Right-click `WebContent` -> **New -> JSP File** -> Name: `employeeForm.jsp`.
   - Right-click `WebContent` -> **New -> JSP File** -> Name: `employeeResult.jsp`.
   - Paste the respective code snippets into each file.
3. **Execute**:
   - Right-click `employeeForm.jsp` -> **Run As -> Run on Server** -> Click **Finish**.
   - URL: `http://localhost:8080/JSPDemo/employeeForm.jsp`.

---

### Detailed Step-by-Step Explanation

1. **JSP Directives (`<%@ page ... %>`):**
   - `<%@ page import="java.util.Calendar" %>` imports Java standard library classes into the generated servlet class.
2. **Scriptlet Tag (`<% ... %>`):**
   - Contains pure Java statements that are executed every time the page is requested. Here, we parse the age string to an integer and compute the birth year dynamically.
3. **Expression Tag (`<%= ... %>`):**
   - Evaluates a Java expression, converts the result to a string, and prints it directly into the HTML response stream (equivalent to `out.print(...)`).

---

### Sample Input & Expected Output

#### Form Input:
- **Employee ID:** `EMP1024`
- **Name:** `Sarah Connor`
- **Age:** `30`
- **Department:** `Cyberdyne Systems`
- **Email:** `Sarah.Connor@TECH.ORG`

#### Result Table Output:
| Field | Value |
|---|---|
| **Employee ID** | `EMP1024` |
| **Name and Department** | `Sarah Connor (Cyberdyne Systems)` |
| **Birth Year** | `1996` |
| **Formatted Email** | `sarah.connor@tech.org` |

---

### Viva Questions & Key Takeaways

- **Q: How does a JSP page execute on the server?**
  *A:* The web container translates the `.jsp` file into a Java Servlet source file (`.java`), compiles it into bytecode (`.class`), loads the servlet class, initializes it (`jspInit()`), and invokes `_jspService()` on each request.
- **Q: What is the difference between `<%! ... %>`, `<% ... %>`, and `<%= ... %>`?**
  *A:*
  - `<%! ... %>` (Declaration): Declares instance variables and methods.
  - `<% ... %>` (Scriptlet): Executes statements inside the `_jspService()` method.
  - `<%= ... %>` (Expression): Outputs a variable/expression directly to the response writer.

---

## Experiment 2b: Server-Side Form Validation in JSP

### Aim & Concept
Implement comprehensive server-side input validation in JSP using regular expressions and logic tests:
- **Employee ID:** Must not be null or blank.
- **Name:** Must contain only letters and whitespace (`^[a-zA-Z\s]+$`).
- **Age:** Must parse as an integer and fall within `18 <= age <= 65`.
- **Department:** Must match an allowed option (`HR`, `Finance`, `IT`, `Sales`).
- **Email:** Must match standard email pattern (`^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}$`).
- If validation passes, display the processed employee table; if any field fails, render a list of specific error messages alongside a back link.

### Required Packages & Dependencies
- `java.util.regex.*` (`Pattern`, `Matcher`)
- `java.util.Calendar`

---

### Source Code

#### `employeeForm.jsp`
```jsp
<!DOCTYPE html>
<html>
<head>
    <title>Employee Form with Validation</title>
</head>
<body>
    <h2>Employee Information Form</h2>
    <form action="employeeResult.jsp" method="POST">
        <table>
            <tr>
                <td>Employee ID:</td>
                <td><input type="text" name="employeeId" required></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><input type="number" name="age" required></td>
            </tr>
            <tr>
                <td>Department:</td>
                <td>
                    <select name="department" required>
                        <option value="">-- Select Department --</option>
                        <option value="HR">HR</option>
                        <option value="Finance">Finance</option>
                        <option value="IT">IT</option>
                        <option value="Sales">Sales</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Submit">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
```

#### `employeeResult.jsp`
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.regex.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Validation Results</title>
</head>
<body>
    <h2>Employee Information Result</h2>
    <%
        // 1. Retrieve form data
        String employeeId = request.getParameter("employeeId");
        String name       = request.getParameter("name");
        String ageStr     = request.getParameter("age");
        String department = request.getParameter("department");
        String email      = request.getParameter("email");

        // 2. Initialize error variables
        String employeeIdError = "";
        String nameError       = "";
        String ageError        = "";
        String departmentError = "";
        String emailError      = "";
        boolean isValid        = true;

        // 3. Validate Employee ID
        if (employeeId == null || employeeId.trim().isEmpty()) {
            employeeIdError = "Employee ID is required.";
            isValid = false;
        }

        // 4. Validate Name (only alphabets & spaces)
        if (name == null || !name.matches("^[a-zA-Z\\s]+$")) {
            nameError = "Name must contain only letters and spaces.";
            isValid = false;
        }

        // 5. Validate Age (Numeric between 18 and 65)
        int age = 0;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 18 || age > 65) {
                ageError = "Age must be between 18 and 65.";
                isValid = false;
            }
        } catch (NumberFormatException e) {
            ageError = "Age must be a valid numeric integer.";
            isValid = false;
        }

        // 6. Validate Department
        if (department == null || department.trim().isEmpty()) {
            departmentError = "Department selection is required.";
            isValid = false;
        } else if (!department.matches("HR|Finance|IT|Sales")) {
            departmentError = "Invalid department selected.";
            isValid = false;
        }

        // 7. Validate Email Pattern
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            emailError = "Email must be a valid email address (e.g., name@example.com).";
            isValid = false;
        }

        // 8. Render Success Table or Validation Errors
        if (isValid) {
            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            int birthYear   = currentYear - age;
            String nameAndDept = name + " (" + department + ")";
            String formattedEmail = email.toLowerCase();
    %>
            <table border="1" cellpadding="8" cellspacing="0">
                <tr><th>Employee ID</th>         <td><%= employeeId %></td></tr>
                <tr><th>Name and Department</th> <td><%= nameAndDept %></td></tr>
                <tr><th>Birth Year</th>          <td><%= birthYear %></td></tr>
                <tr><th>Formatted Email</th>     <td><%= formattedEmail %></td></tr>
            </table>
    <%
        } else {
    %>
            <h3 style="color: red;">Validation Errors Found:</h3>
            <ul>
                <% if (!employeeIdError.isEmpty()) { %>
                    <li><b>Employee ID:</b> <%= employeeIdError %></li>
                <% } %>
                <% if (!nameError.isEmpty()) { %>
                    <li><b>Name:</b> <%= nameError %></li>
                <% } %>
                <% if (!ageError.isEmpty()) { %>
                    <li><b>Age:</b> <%= ageError %></li>
                <% } %>
                <% if (!departmentError.isEmpty()) { %>
                    <li><b>Department:</b> <%= departmentError %></li>
                <% } %>
                <% if (!emailError.isEmpty()) { %>
                    <li><b>Email:</b> <%= emailError %></li>
                <% } %>
            </ul>
            <p><a href="employeeForm.jsp">← Go back to the form</a></p>
    <%
        }
    %>
</body>
</html>
```

---

### Detailed Step-by-Step Explanation

1. **Server-Side vs Client-Side Validation:**
   - While HTML5 `required` and `type="email"` provide client-side checks, they can easily be bypassed by malicious users using tools like Postman or curl. Server-side validation guarantees data integrity before business processing.
2. **Regex Validation with `String.matches()`:**
   - `^[a-zA-Z\\s]+$`: Anchored match enforcing only uppercase, lowercase, and space characters.
   - `^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$`: Enforces word characters/dots before `@`, domain name, and at least a 2-character Top-Level Domain (TLD).
3. **Exception-Safe Numeric Parsing:**
   - Wrapping `Integer.parseInt(ageStr)` inside a `try-catch (NumberFormatException e)` ensures the server never throws an unhandled 500 internal server error on unexpected input.

---

### Sample Input & Expected Output

#### Case 1: Invalid Input
- **Name:** `Agent 007` *(contains numbers)*
- **Age:** `15` *(less than 18)*
- **Email:** `bad_email@` *(missing domain)*

#### Output:
```text
Validation Errors Found:
• Name: Name must contain only letters and spaces.
• Age: Age must be between 18 and 65.
• Email: Email must be a valid email address (e.g., name@example.com).

← Go back to the form
```

---

### Viva Questions & Key Takeaways

- **Q: Why is server-side validation mandatory even if HTML5 validation is present?**
  *A:* Client-side validation can be disabled in browser settings or bypassed by submitting raw HTTP requests; server-side validation ensures system security and database consistency.
- **Q: What does regex `^[\\w.-]+` mean?**
  *A:* `^` matches string start, `\w` matches alphanumeric characters and underscores, `.` and `-` match dot and hyphen, and `+` ensures 1 or more characters exist.

---

## Experiment 3: JSP + JDBC Student CRUD Application

### Aim & Concept
Develop a web application integrating **JavaServer Pages (JSP)** with **Java Database Connectivity (JDBC)** to perform complete **CRUD** operations on a MySQL `students` table:
- **Create:** Insert new records (roll number, name, email, age).
- **Read:** Query and display all records in a responsive table.
- **Update:** Pre-populate existing student information into a form and save updates.
- **Delete:** Remove student records by roll number.
- Follow the **DAO (Data Access Object)** design pattern to cleanly decouple SQL statements from JSP view rendering.

### Required Packages & Dependencies
| Package | Class / Interface | Purpose |
|---|---|---|
| `java.sql` | `Connection`, `DriverManager` | Establishes and manages the database connection |
| `java.sql` | `PreparedStatement`, `Statement` | Precompiled parameterized SQL statements preventing SQL Injection |
| `java.sql` | `ResultSet` | Traverses tabular database query results |
| `java.util` | `List`, `ArrayList` | Collections for transporting student objects across layers |
| **External JAR** | `mysql-connector-j-26.10.0.jar` (in `mysql-connector-j-26.10.0/`) | JDBC Driver for MySQL communication |

---

### Source Code

#### 1. `DBConnection.java`
```java
package com.studentcrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to obtain a JDBC Connection to the MySQL database.
 */
public class DBConnection {

    // Configure your MySQL URL, username, and password
    private static final String JDBC_URL      = "jdbc:mysql://localhost:3306/studentDB?useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL Connector Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
```

#### 2. `Student.java` (Model POJO)
```java
package com.studentcrud;

/**
 * POJO representing a single row in the 'students' table.
 */
public class Student {

    private int    rollNo;
    private String name;
    private String email;
    private int    age;

    public Student() { }

    public Student(int rollNo, String name, String email, int age) {
        this.rollNo = rollNo;
        this.name   = name;
        this.email  = email;
        this.age    = age;
    }

    // -------- Getters & Setters --------
    public int    getRollNo() { return rollNo; }
    public void   setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getName()   { return name; }
    public void   setName(String name) { this.name = name; }

    public String getEmail()  { return email; }
    public void   setEmail(String email) { this.email = email; }

    public int    getAge()    { return age; }
    public void   setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student[rollNo=" + rollNo + ", name=" + name + ", email=" + email + ", age=" + age + "]";
    }
}
```

#### 3. `StudentDAO.java` (Data Access Object)
```java
package com.studentcrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO Layer executing SQL CRUD statements.
 */
public class StudentDAO {

    /* ---- 1. CREATE: Insert Student ---- */
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (roll_no, name, email, age) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setInt(4,    student.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ---- 2. READ: Fetch All Students ---- */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student();
                s.setRollNo(rs.getInt("roll_no"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setAge(rs.getInt("age"));
                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    /* ---- 3. UPDATE: Update Student by Roll Number ---- */
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, age = ? WHERE roll_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3,    student.getAge());
            ps.setInt(4,    student.getRollNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ---- 4. DELETE: Delete Student by Roll Number ---- */
    public void deleteStudent(int rollNo) {
        String sql = "DELETE FROM students WHERE roll_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rollNo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

#### 4. `viewStudents.jsp`
```jsp
<%@ page import="java.util.List, com.studentcrud.Student, com.studentcrud.StudentDAO" %>
<%
    StudentDAO dao = new StudentDAO();
    List<Student> students = dao.getAllStudents();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
</head>
<body>
    <h2>Student Records</h2>
    <p><a href="addStudent.jsp">+ Add New Student</a></p>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Roll No</th>
            <th>Name</th>
            <th>Email</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
        <%
            for (Student student : students) {
        %>
        <tr>
            <td><%= student.getRollNo() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getEmail() %></td>
            <td><%= student.getAge() %></td>
            <td>
                <a href="updateStudent.jsp?rollNo=<%= student.getRollNo() %>">Edit</a> |
                <a href="deleteStudent.jsp?rollNo=<%= student.getRollNo() %>"
                   onclick="return confirm('Are you sure you want to delete?');">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
```

#### 5. `addStudent.jsp`
```jsp
<%@ page import="com.studentcrud.Student, com.studentcrud.StudentDAO" %>
<%
    if (request.getParameter("rollNo") != null) {
        int rollNo  = Integer.parseInt(request.getParameter("rollNo"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age     = Integer.parseInt(request.getParameter("age"));

        Student student = new Student(rollNo, name, email, age);
        StudentDAO dao  = new StudentDAO();
        dao.addStudent(student);

        response.sendRedirect("viewStudents.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
    <h2>Add New Student</h2>
    <form action="addStudent.jsp" method="post">
        <table>
            <tr><td>Roll No:</td> <td><input type="number" name="rollNo" required></td></tr>
            <tr><td>Name:</td>    <td><input type="text" name="name" required></td></tr>
            <tr><td>Email:</td>   <td><input type="email" name="email" required></td></tr>
            <tr><td>Age:</td>     <td><input type="number" name="age" required></td></tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save Student"></td>
            </tr>
        </table>
    </form>
    <p><a href="viewStudents.jsp">← Back to Student List</a></p>
</body>
</html>
```

#### 6. `updateStudent.jsp`
```jsp
<%@ page import="com.studentcrud.Student, com.studentcrud.StudentDAO" %>
<%
    StudentDAO dao = new StudentDAO();
    int rollNo = Integer.parseInt(request.getParameter("rollNo"));

    // Find student matching rollNo
    Student student = null;
    for (Student s : dao.getAllStudents()) {
        if (s.getRollNo() == rollNo) {
            student = s;
            break;
        }
    }

    // Process update submission
    if (request.getParameter("name") != null) {
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setAge(Integer.parseInt(request.getParameter("age")));
        dao.updateStudent(student);
        response.sendRedirect("viewStudents.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
    <h2>Update Student Record</h2>
    <% if (student != null) { %>
    <form action="updateStudent.jsp?rollNo=<%= student.getRollNo() %>" method="post">
        <table>
            <tr><td>Roll No:</td> <td><b><%= student.getRollNo() %></b></td></tr>
            <tr><td>Name:</td>    <td><input type="text" name="name" value="<%= student.getName() %>" required></td></tr>
            <tr><td>Email:</td>   <td><input type="email" name="email" value="<%= student.getEmail() %>" required></td></tr>
            <tr><td>Age:</td>     <td><input type="number" name="age" value="<%= student.getAge() %>" required></td></tr>
            <tr>
                <td colspan="2"><input type="submit" value="Update Record"></td>
            </tr>
        </table>
    </form>
    <% } %>
    <p><a href="viewStudents.jsp">← Back to Student List</a></p>
</body>
</html>
```

#### 7. `deleteStudent.jsp`
```jsp
<%@ page import="com.studentcrud.StudentDAO" %>
<%
    int rollNo = Integer.parseInt(request.getParameter("rollNo"));
    StudentDAO dao = new StudentDAO();
    dao.deleteStudent(rollNo);
    response.sendRedirect("viewStudents.jsp");
%>
```

---

### How to Create & Run in Eclipse

1. **Create MySQL Database**:
   - Run the script in MySQL CLI or Workbench:
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
2. **Create Eclipse Project**:
   - **File -> New -> Dynamic Web Project** -> Name: `StudentCRUD`.
   - Add MySQL Driver JAR (`mysql-connector-j-26.10.0.jar` from `mysql-connector-j-26.10.0/`) into `WebContent/WEB-INF/lib/`.
3. **Add Java Package & Classes**:
   - Under `src`, create package `com.studentcrud`.
   - Add `DBConnection.java`, `Student.java`, and `StudentDAO.java`.
4. **Add JSP Pages**:
   - In `WebContent`, add `viewStudents.jsp`, `addStudent.jsp`, `updateStudent.jsp`, and `deleteStudent.jsp`.
5. **Run**:
   - Right-click `viewStudents.jsp` -> **Run As -> Run on Server** -> Select Tomcat -> **Finish**.
   - URL: `http://localhost:8080/StudentCRUD/viewStudents.jsp`.

---

### Detailed Step-by-Step Explanation

1. **Why `PreparedStatement`?**
   - It precompiles the SQL query template on the database server. Values passed via `setInt()` and `setString()` are treated as pure data literals rather than executable SQL code, preventing SQL injection vulnerabilities.
2. **Try-with-Resources:**
   - Using `try (Connection conn = ...; PreparedStatement ps = ...)` guarantees that database connections, statements, and result sets are cleanly closed even if an exception occurs, avoiding database connection leaks.
3. **DAO Pattern:**
   - Isolates all database access logic inside `StudentDAO.java`, keeping the view layer (`.jsp`) clean and maintainable.

---

### Sample Input & Expected Output

#### View Page Output (`viewStudents.jsp`):
```text
Student Records
+ Add New Student

+---------+-----------------+-----------------------+-----+---------------+
| Roll No | Name            | Email                 | Age | Actions       |
+---------+-----------------+-----------------------+-----+---------------+
| 101     | Alice Johnson   | alice@example.com     | 20  | Edit | Delete |
| 102     | Bob Smith       | bob@example.com       | 21  | Edit | Delete |
+---------+-----------------+-----------------------+-----+---------------+
```

---

### Viva Questions & Key Takeaways

- **Q: What is the purpose of `Class.forName("com.mysql.cj.jdbc.Driver")`?**
  *A:* It dynamically loads the MySQL JDBC driver bytecode into the JVM memory, registering it with the `DriverManager`.
- **Q: What is the difference between `Statement` and `PreparedStatement`?**
  *A:* `Statement` compiles SQL queries each time they are executed and does not handle parameter substitution (susceptible to SQL Injection). `PreparedStatement` is precompiled, accepts positional parameters (`?`), performs faster on repeated execution, and is immune to SQL Injection.
- **Q: What is the difference between `executeUpdate()` and `executeQuery()`?**
  *A:* `executeQuery()` is used for `SELECT` statements and returns a `ResultSet`. `executeUpdate()` is used for `INSERT`, `UPDATE`, `DELETE` (DML) and returns an integer representing the number of rows affected.

---

## Experiment 4: Spring Boot + Thymeleaf CRUD Web Application

### Aim & Concept
Develop a full-stack web application using **Spring Boot**, **Spring MVC**, **Spring Data JPA**, **Hibernate**, and **Thymeleaf**:
- Manage staff/book records with attributes: `id`, `name`, `age`, and `email`.
- Use **Spring Data JPA** (`JpaRepository`) to eliminate boilerplate SQL/JDBC code.
- Use **Thymeleaf** templates with two-way object data binding (`th:object`, `th:field`, `th:each`).
- Use **Spring MVC** `@Controller` to handle routing (`/`, `/new`, `/save`, `/edit/{id}`, `/delete/{id}`).

### Required Packages & Dependencies (`pom.xml`)
```xml
<dependencies>
    <!-- Web: Spring MVC & Embedded Tomcat -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Template Engine: Thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- ORM: Spring Data JPA & Hibernate -->
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

### Source Code

#### 1. `Staff.java` (JPA Entity)
```java
package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Experiment 4 - JPA Entity mapped to MySQL table.
 */
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;

    public Staff() { }

    // -------- Getters and Setters --------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Staff[id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
    }
}
```

#### 2. `StaffRepository.java` (Spring Data JPA)
```java
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository provides ready-to-use CRUD methods:
 * findAll(), findById(), save(), deleteById(), existsById().
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
```

#### 3. `StaffManager.java` (Service Layer)
```java
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffManager {

    @Autowired
    private StaffRepository repository;

    public List<Staff> getAllStaff()     { return repository.findAll(); }
    public Staff getStaffById(Long id)  { return repository.findById(id).get(); }
    public void saveStaff(Staff staff)  { repository.save(staff); }
    public void deleteStaff(Long id)   { repository.deleteById(id); }
}
```

#### 4. `AppController.java` (Spring MVC Controller)
```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @Autowired
    private StaffManager manager;

    // 1. List all staff
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("stafflist", manager.getAllStaff());
        return "index";
    }

    // 2. Show create form
    @GetMapping("/new")
    public String showCreatePage() {
        return "create_book_form";
    }

    // 3. Save staff (Create / Update)
    @PostMapping("/save")
    public String saveStaff(@ModelAttribute Staff staff) {
        manager.saveStaff(staff);
        return "redirect:/";
    }

    // 4. Show edit form
    @GetMapping("/edit/{id}")
    public String editStaff(@PathVariable Long id, Model model) {
        model.addAttribute("staff", manager.getStaffById(id));
        return "edit_form";
    }

    // 5. Delete staff
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Long id) {
        manager.deleteStaff(id);
        return "redirect:/";
    }
}
```

#### 5. `application.properties`
```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/staffDB?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate DDL Auto-Generation
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### 6. `src/main/resources/templates/index.html`
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <title>Staff List</title>
</head>
<body>
<div align="center">
    <h1>Staff Management</h1>
    <p><a href="/new">+ Create New Staff</a></p>
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        <tr th:each="staff : ${stafflist}">
            <td th:text="${staff.id}">1</td>
            <td th:text="${staff.name}">John</td>
            <td th:text="${staff.age}">30</td>
            <td th:text="${staff.email}">john@example.com</td>
            <td>
                <a th:href="@{'/edit/'   + ${staff.id}}">Edit</a> |
                <a th:href="@{'/delete/' + ${staff.id}}">Delete</a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
```

#### 7. `src/main/resources/templates/create_book_form.html`
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Create Staff</title>
</head>
<body>
<div align="center">
    <h1>Create New Staff</h1>
    <form action="/save" method="post">
        <table border="0" cellpadding="8" cellspacing="0">
            <tr><td>Name:</td>  <td><input type="text" name="name" required/></td></tr>
            <tr><td>Age:</td>   <td><input type="number" name="age" required/></td></tr>
            <tr><td>Email:</td> <td><input type="email" name="email" required/></td></tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Save</button>
                </td>
            </tr>
        </table>
    </form>
    <p><a href="/">← Back to List</a></p>
</div>
</body>
</html>
```

#### 8. `src/main/resources/templates/edit_form.html`
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Staff</title>
</head>
<body>
<div align="center">
    <h1>Edit Staff Record</h1>
    <form action="#" th:action="@{/save}" th:object="${staff}" method="post">
        <table border="0" cellpadding="8" cellspacing="0">
            <tr><td>ID:</td>    <td><input type="text" th:field="*{id}" readonly="readonly"/></td></tr>
            <tr><td>Name:</td>  <td><input type="text" th:field="*{name}" required/></td></tr>
            <tr><td>Age:</td>   <td><input type="number" th:field="*{age}" required/></td></tr>
            <tr><td>Email:</td> <td><input type="email" th:field="*{email}" required/></td></tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit">Update</button>
                </td>
            </tr>
        </table>
    </form>
    <p><a href="/">← Back to List</a></p>
</div>
</body>
</html>
```

---

### How to Create & Run in Eclipse

1. **Create Spring Starter Project**:
   - Go to **File -> New -> Spring Starter Project** (or generate from [start.spring.io](https://start.spring.io) and import as Existing Maven Project).
   - Dependencies: **Spring Web**, **Thymeleaf**, **Spring Data JPA**, **MySQL Driver**.
2. **Add Files**:
   - In `src/main/java/com/example/demo/`: `Staff.java`, `StaffRepository.java`, `StaffManager.java`, `AppController.java`.
   - In `src/main/resources/application.properties`: Paste the database configuration.
   - In `src/main/resources/templates/`: Add `index.html`, `create_book_form.html`, and `edit_form.html`.
3. **Execute**:
   - Right-click the Application class (e.g., `DemoApplication.java`) -> **Run As -> Spring Boot App**.
   - Open browser: `http://localhost:8080/`.

---

### Detailed Step-by-Step Explanation

1. **Spring Data JPA Magic:**
   - By creating `interface StaffRepository extends JpaRepository<Staff, Long>`, Spring Data dynamically generates proxy implementations for all database interactions at runtime without writing any SQL queries.
2. **`@ModelAttribute`:**
   - Automatically binds form fields from the incoming HTTP POST request into the properties of a `Staff` object.
3. **Hibernate DDL Auto (`update`):**
   - Automatically detects the `@Entity` annotation on `Staff` and creates or alters the `staff` table in MySQL if it doesn't already exist.

---

### Sample Input & Expected Output

#### Home Page (`http://localhost:8080/`):
```text
Staff Management
+ Create New Staff

+----+-------------------+-----+-----------------------+---------------+
| ID | Name              | Age | Email                 | Action        |
+----+-------------------+-----+-----------------------+---------------+
| 1  | Hermione Granger  | 24  | hermione@ministry.gov | Edit | Delete |
+----+-------------------+-----+-----------------------+---------------+
```

---

### Viva Questions & Key Takeaways

- **Q: What is Spring Boot?**
  *A:* An opinionated extension of the Spring framework that provides automatic configuration, embedded web servers (Tomcat), and starter dependencies to eliminate boilerplate configuration.
- **Q: What does `@Autowired` do?**
  *A:* Instructs the Spring IoC container to inject the required bean dependency by type into the class field or constructor.
- **Q: How does `redirect:/` differ from returning a view name like `"index"`?**
  *A:* Returning `"index"` renders the view template directly on the current request. `redirect:/` sends an HTTP 302 redirect response instructing the browser to issue a fresh GET request to `/`, preventing accidental duplicate form submissions upon page refresh (Post/Redirect/Get pattern).

---

## Experiment 5: Spring Boot RESTful API with MySQL & Error Handling

### Aim & Concept
Develop a production-grade **RESTful API** web service using Spring Boot:
- Provide CRUD endpoints for mobile/staff resources producing and consuming **JSON**.
- Implement standard HTTP methods:
  - `GET /api/staff`: List all records.
  - `GET /api/staff/{id}`: Fetch single record. Returns `200 OK` or `404 NOT FOUND`.
  - `POST /api/staff`: Create new record. Returns `201 CREATED`.
  - `PUT /api/staff`: Update existing record. Returns `200 OK` or `404 NOT FOUND`.
  - `DELETE /api/staff/{id}`: Delete record. Returns `200 OK` or `404 NOT FOUND`.
- Encapsulate robust error handling using `ResponseEntity` and `NoSuchElementException`.
- Test the APIs using `curl` and Postman.

### Required Packages & Dependencies (`pom.xml`)
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `com.mysql:mysql-connector-j`

---

### Source Code

#### 1. `Staff.java` (Entity)
```java
package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;

    public Staff() { }

    public Staff(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

#### 2. `StaffRepository.java`
```java
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
```

#### 3. `StaffManager.java` (Service Layer)
```java
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffManager {

    @Autowired
    private StaffRepository repository;

    public List<Staff> getAllStaff()     { return repository.findAll(); }
    public Staff getStaffById(Long id)  { return repository.findById(id).get(); }
    public void saveStaff(Staff staff)  { repository.save(staff); }
    public void deleteStaff(Long id)   { repository.deleteById(id); }
    public boolean exists(Long id)      { return repository.existsById(id); }
}
```

#### 4. `AppController.java` (`@RestController`)
```java
package com.example.demo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Experiment 5 - RESTful API Controller with JSON output and HTTP status codes.
 */
@RestController
@RequestMapping("/api/staff")
public class AppController {

    @Autowired
    private StaffManager manager;

    /* ---- 1. GET ALL: /api/staff ---- */
    @GetMapping
    public List<Staff> listAllStaff() {
        return manager.getAllStaff();
    }

    /* ---- 2. GET BY ID: /api/staff/{id} ---- */
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable Long id) {
        try {
            Staff staff = manager.getStaffById(id);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* ---- 3. POST: /api/staff (Create) ---- */
    @PostMapping
    public ResponseEntity<String> saveStaff(@RequestBody Staff staff) {
        manager.saveStaff(staff);
        return new ResponseEntity<>("Record created successfully", HttpStatus.CREATED);
    }

    /* ---- 4. PUT: /api/staff (Update) ---- */
    @PutMapping
    public ResponseEntity<String> updateStaff(@RequestBody Staff staff) {
        if (staff.getId() != null && manager.exists(staff.getId())) {
            manager.saveStaff(staff);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Record not found to update", HttpStatus.NOT_FOUND);
        }
    }

    /* ---- 5. DELETE: /api/staff/{id} ---- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        if (manager.exists(id)) {
            manager.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
```

#### 5. `application.properties`
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

### How to Create & Run in Eclipse

1. In your Spring Boot project, place `Staff.java`, `StaffRepository.java`, `StaffManager.java`, and `AppController.java` under package `com.example.demo`.
2. Ensure MySQL server is running locally on port `3306`.
3. Right-click the Spring Boot Main class -> **Run As -> Spring Boot App**.
4. The Tomcat server starts listening on port `8080`.

---

### Step-by-Step API Testing with `curl` & Postman

#### 1. Create a Record (POST)
- **Method:** `POST`
- **URL:** `http://localhost:8080/api/staff`
- **Headers:** `Content-Type: application/json`
- **Payload:**
  ```json
  {
    "name": "Sarah Connor",
    "age": 29,
    "email": "sarah@resistance.org"
  }
  ```
- **cURL Command:**
  ```bash
  curl -i -X POST http://localhost:8080/api/staff \
    -H "Content-Type: application/json" \
    -d '{"name": "Sarah Connor", "age": 29, "email": "sarah@resistance.org"}'
  ```
- **Expected Response:** `HTTP/1.1 201 Created` — `"Record created successfully"`

---

#### 2. Get All Records (GET)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/staff`
- **cURL Command:**
  ```bash
  curl -i -X GET http://localhost:8080/api/staff
  ```
- **Expected Response (`200 OK`):**
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

#### 3. Get Record by ID (GET)
- **cURL Command:**
  ```bash
  curl -i -X GET http://localhost:8080/api/staff/1
  ```
- **Expected Response (`200 OK`):**
  ```json
  {
    "id": 1,
    "name": "Sarah Connor",
    "age": 29,
    "email": "sarah@resistance.org"
  }
  ```

- **Querying Non-Existent ID (e.g. `999`):**
  ```bash
  curl -i -X GET http://localhost:8080/api/staff/999
  ```
- **Expected Response:** `HTTP/1.1 404 Not Found`

---

#### 4. Update a Record (PUT)
- **Method:** `PUT`
- **URL:** `http://localhost:8080/api/staff`
- **cURL Command:**
  ```bash
  curl -i -X PUT http://localhost:8080/api/staff \
    -H "Content-Type: application/json" \
    -d '{"id": 1, "name": "Sarah Connor", "age": 30, "email": "sarah.c@resistance.org"}'
  ```
- **Expected Response:** `HTTP/1.1 200 OK` — `"Record updated successfully"`

---

#### 5. Delete a Record (DELETE)
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/api/staff/1`
- **cURL Command:**
  ```bash
  curl -i -X DELETE http://localhost:8080/api/staff/1
  ```
- **Expected Response:** `HTTP/1.1 200 OK`

---

### Detailed Step-by-Step Explanation

1. **`@RestController` vs `@Controller`:**
   - `@Controller` is used for traditional MVC applications where handler methods return view template names (e.g., HTML).
   - `@RestController` combines `@Controller` and `@ResponseBody`. It serializes the return value of every method directly into JSON or XML format in the HTTP response body.
2. **`@RequestBody` & `@PathVariable`:**
   - `@RequestBody` triggers Spring's Jackson library to deserialize the raw incoming JSON payload into a Java POJO.
   - `@PathVariable` extracts dynamic path segments from the URI template (e.g., `/api/staff/{id}`).
3. **`ResponseEntity<T>`:**
   - Represents the complete HTTP response, giving fine-grained control over the response body, headers, and HTTP status codes (`HttpStatus.OK`, `HttpStatus.CREATED`, `HttpStatus.NOT_FOUND`).

---

### Viva Questions & Key Takeaways

- **Q: What is REST and what are its key principles?**
  *A:* Representational State Transfer (REST) is an architectural style based on stateless communication, standard HTTP methods (GET, POST, PUT, DELETE), unique resource identification via URIs, and standard media types like JSON.
- **Q: What is the semantic difference between `POST` and `PUT`?**
  *A:* `POST` is used to create a new resource on the server (non-idempotent). `PUT` is used to update an existing resource or replace it completely (idempotent).
- **Q: What HTTP status code should be returned when a resource is successfully created?**
  *A:* `201 Created` (accompanied by a location header or the created entity in the body).

---

## MySQL Database Setup Scripts

To run the database-backed experiments, open MySQL terminal or Workbench and execute:

```sql
-- ========================================================
-- 1. Database for Experiment 3 (JSP + JDBC Student CRUD)
-- ========================================================
CREATE DATABASE IF NOT EXISTS studentDB;
USE studentDB;

CREATE TABLE IF NOT EXISTS students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

-- Optional sample rows:
INSERT INTO students (roll_no, name, email, age) VALUES
(101, 'Alice Johnson', 'alice@example.com', 20),
(102, 'Bob Smith', 'bob@example.com', 21);

-- ========================================================
-- 2. Database for Experiments 4 & 5 (Spring Boot + JPA)
-- ========================================================
CREATE DATABASE IF NOT EXISTS staffDB;
USE staffDB;
-- Note: Spring Boot automatically generates the 'staff' table
-- via 'spring.jpa.hibernate.ddl-auto=update'
```

---

## Quick Viva Cheat Sheet

| Experiment | Core Concept | Key Annotations / Syntax | Common Viva Question | Answer Summary |
|---|---|---|---|---|
| **1a. Simple Servlet** | HTTP Request Dispatching | `@WebServlet`, `doGet()`, `doPost()`, `getParameter()` | What is the servlet lifecycle? | `init()` -> `service()` (dispatches to `doGet`/`doPost`) -> `destroy()`. |
| **1b. Session Servlet** | Session Tracking | `request.getSession(false)`, `session.invalidate()` | How are sessions tracked across requests? | Via `JSESSIONID` stored as a browser cookie or rewritten in the URL. |
| **2a. Employee JSP** | Scriptlets & Expressions | `<% ... %>`, `<%= ... %>`, `<%@ page import="..." %>` | What is a JSP expression? | Evaluates code and directly writes output into the response stream (`out.print`). |
| **2b. Validation JSP** | Server-Side Validation | `String.matches("^[a-zA-Z\\s]+$")`, `Integer.parseInt()` | Why validate on the server? | Client-side checks can be bypassed; server validation protects data integrity. |
| **3. JDBC CRUD** | 3-Tier DAO Pattern | `PreparedStatement`, `DriverManager.getConnection()` | Why prefer `PreparedStatement`? | Precompiled for speed; parameter binding prevents SQL Injection attacks. |
| **4. Spring MVC CRUD** | MVC & Thymeleaf ORM | `@Controller`, `@GetMapping`, `th:object`, `JpaRepository` | What does `redirect:/` do? | Emits an HTTP 302 redirect to prevent duplicate form submission on refresh. |
| **5. RESTful API** | REST Web Services | `@RestController`, `@RequestBody`, `ResponseEntity` | What is `@RestController`? | `@Controller` + `@ResponseBody`; serializes return objects into JSON. |

---

## Troubleshooting & Common Errors

### 1. `java.lang.ClassNotFoundException: com.mysql.jdbc.Driver`
- **Cause:** Using legacy MySQL 5.x driver class or missing MySQL Connector JAR file.
- **Solution:**
  - For MySQL 8+, update the class name to `com.mysql.cj.jdbc.Driver`.
  - For JSP/Servlet (Exp 3): Make sure `mysql-connector-j-26.10.0.jar` is copied into `WebContent/WEB-INF/lib/`.
  - For Spring Boot (Exp 4 & 5): Ensure `com.mysql:mysql-connector-j` is present in `pom.xml`.

### 2. Port `8080` Already in Use
- **Cause:** Another service (or an earlier Tomcat / Spring Boot instance) is occupying port 8080.
- **Solution:**
  - **In Spring Boot:** Change port in `application.properties`:
    ```properties
    server.port=8085
    ```
  - **In Eclipse Tomcat:** Double-click **Tomcat v9.0 Server** in the *Servers* tab -> Under *Ports*, change *HTTP/1.1* from `8080` to `8085` -> Save (`Ctrl+S`) and restart.

### 3. `javax.servlet.*` cannot be resolved (Eclipse Error)
- **Cause:** Target runtime server library is not attached to the project build path.
- **Solution:**
  - Right-click project -> **Build Path -> Configure Build Path...** -> Select **Libraries** tab -> **Add Library...** -> **Server Runtime** -> Select **Apache Tomcat v9.0** -> Click **Finish**.

### 4. `jakarta.servlet` vs `javax.servlet`
- **Rule of Thumb:**
  - **Tomcat 9 and older:** Uses Java EE namespace -> `javax.servlet.*`.
  - **Tomcat 10 and newer:** Uses Jakarta EE namespace -> `jakarta.servlet.*`.
  - Align your code imports with the Tomcat version installed in Eclipse!

### 5. Access Denied for User `'root'@'localhost'` (MySQL Error 1045)
- **Cause:** Incorrect MySQL password in `DBConnection.java` or `application.properties`.
- **Solution:** Update the password string to match the password configured during MySQL installation.

---

*Repository maintained for CSE Java & Spring Framework Laboratory (CS3603-1).*
