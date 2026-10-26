# Java Servlet and JSP Walkthrough

This guide provides an end-to-end walkthrough of the Java web application workflow represented in the lab screen recordings. It covers Eclipse IDE setup, creating and running a Java Servlet named `MyServlet.java`, configuring Apache Tomcat, building a JSP employee information form with server-side processing, and diagnosing and fixing the common HTTP 500 internal server error.

> **Scope:** This is a Java web application walkthrough for Servlets and JSP. All values, package names, file paths, and root cause analyses have been verified and cross-referenced with the source code in this repository.

---

## 1. Prerequisites

### Software & Environment

| Component | Verified Specification | Notes |
|---|---|---|
| **IDE** | **Eclipse IDE for Enterprise Java and Web Developers** (2022-09+) | Includes Dynamic Web Project wizard, JSP editor, and Tomcat Server adapter |
| **JDK** | **Java SE 11** or **Java SE 17 LTS** | Must be a full JDK (not JRE alone) configured under Eclipse *Installed JREs* |
| **Servlet Container** | **Apache Tomcat 9.0.x** (or **Tomcat 10.1.x+**) | Handles servlet lifecycle and compiles JSP pages into servlets |
| **API Family** | `javax.servlet.*` (Tomcat 9) or `jakarta.servlet.*` (Tomcat 10+) | Keep API imports matching your Tomcat version; do not mix both families |

---

## 2. Create or Open a Web Project in Eclipse

Follow these verified steps to create the Dynamic Web Project in Eclipse:

1. Launch **Eclipse IDE for Enterprise Java and Web Developers**.
2. Select or accept your Eclipse workspace folder.
3. Choose **File → New → Dynamic Web Project** (or **File → New → Other… → Web → Dynamic Web Project**).
4. Configure the project wizard settings:
   - **Project Name:** `ServletDemo` (or `JSPDemo` for the JSP experiment).
   - **Target runtime:** Select **Apache Tomcat v9.0** (if not yet added, click **New Runtime...**, select *Apache Tomcat v9.0*, point to your local Tomcat installation directory, and click *Finish*).
   - **Dynamic web module version:** `4.0` (for Tomcat 9) or `5.0`/`6.0` (for Tomcat 10+).
   - **Configuration:** *Default Configuration for Apache Tomcat v9.0*.
5. Click **Next** → **Next**.
6. Check **Generate web.xml deployment descriptor** and click **Finish**.

---

## 3. Register Tomcat with Eclipse

1. Open the **Servers** view in Eclipse via **Window → Show View → Servers**.
2. If no server is present, click the link: **No servers are available. Click this link to create a new server...**
3. Expand **Apache** and select **Tomcat v9.0 Server** (matching your installed version).
4. Confirm **Server's host name:** `localhost` and click **Next**.
5. Ensure your web project (`ServletDemo` / `JSPDemo`) is added to the **Configured** column on the right.
6. Click **Finish**.

---

## 4. Create the Servlet (`MyServlet.java`)

### Wizard Workflow in Eclipse

1. In the **Project Explorer**, expand:
   ```text
   ServletDemo -> Java Resources -> src (or src/main/java)
   ```
2. Right-click the `src` folder and select **New → Servlet** (or **New → Class**):
   - **Java Package:** `com.example`
   - **Class name:** `MyServlet`
   - **Superclass:** `javax.servlet.http.HttpServlet` (or `jakarta.servlet.http.HttpServlet`)
3. Click **Next** to inspect the URL Mapping:
   - **URL mapping:** `/MyServlet`
4. Click **Next**, ensure **doGet** and **doPost** method stubs are selected, and click **Finish**.
5. Replace the generated stub with the verified implementation below.

---

## 5. Servlet Source Code & Explanation

The verified source code is available in this repository at [`Experiment-1a/MyServlet.java`](./Experiment-1a/MyServlet.java) (and [`Experiment-1a/SimpleServlet.java`](./Experiment-1a/SimpleServlet.java)):

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
 * MyServlet - Servlet handling HTTP GET and POST requests.
 *
 * GET  → Renders an interactive HTML form with name and password fields.
 * POST → Reads submitted form parameters and displays the result.
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /* ---------- GET: render the input form ---------- */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Submit Details</title></head><body>");
        out.println("<h2>Submit Your Details</h2>");
        out.println("<form method='POST' action='MyServlet'>");
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

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Submission Result</title></head><body>");
        out.println("<h2>Submitted Data</h2>");
        out.println("<p><strong>Name:</strong> "     + name     + "</p>");
        out.println("<p><strong>Password:</strong> " + password + "</p>");
        out.println("</body></html>");
    }
}
```

### Architectural Breakdown
- **`@WebServlet("/MyServlet")`**: Direct annotation mapping that associates incoming HTTP requests targeting `/MyServlet` with this class, eliminating verbose XML configuration.
- **`doGet(...)`**: Triggered when a browser performs an initial navigation (HTTP GET). It streams an HTML `<form>` to the client with `method='POST' action='MyServlet'`.
- **`doPost(...)`**: Triggered when the user clicks **Submit**. It reads parameters using `request.getParameter("key")` and streams back the dynamic response.

---

## 6. Create the JSP Employee Form & Result Processor

The JSP workflow captures and processes employee data across two pages: **`employeeForm.jsp`** and **`employeeResult.jsp`** (located in [`Experiment-2a/`](./Experiment-2a/)).

### Step 1: Input Form (`employeeForm.jsp`)
Create `employeeForm.jsp` under `WebContent/` (or `src/main/webapp/`):

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

### Step 2: Result Processor (`employeeResult.jsp`)
Create `employeeResult.jsp` in the same directory:

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
        int age       = Integer.parseInt(ageStr);
        int birthYear = Calendar.getInstance().get(Calendar.YEAR) - age;
        String nameAndDept   = name + " (" + department + ")";
        String formattedEmail = (email != null) ? email.toLowerCase() : "";
    %>

    <table border="1">
        <tr><th>Employee ID</th>          <td><%= employeeId %></td></tr>
        <tr><th>Name and Department</th>  <td><%= nameAndDept %></td></tr>
        <tr><th>Birth Year</th>           <td><%= birthYear %></td></tr>
        <tr><th>Formatted Email</th>      <td><%= formattedEmail %></td></tr>
    </table>
</body>
</html>
```

---

## 7. Run and Test with Tomcat

1. Right-click `employeeForm.jsp` (or `MyServlet.java`).
2. Select **Run As → Run on Server**.
3. Choose your configured Tomcat v9.0 instance and click **Finish**.
4. Test URLs in your browser:
   - **Servlet:** `http://localhost:8080/ServletDemo/MyServlet`
   - **JSP Employee Form:** `http://localhost:8080/JSPDemo/employeeForm.jsp`
5. Fill in sample details:
   - **Employee ID:** `EMP101`
   - **Name:** `Sarah Connor`
   - **Age:** `30`
   - **Department:** `Cyberdyne`
   - **Email:** `Sarah.Connor@TECH.ORG`
6. Click **Submit** to view the computed results table.

---

## 8. Root Cause Analysis & Fix for the HTTP 500 Error

### Root Cause of the HTTP 500 Error Shown in Video 3

In [`employeeResult.jsp:L19`](./Experiment-2a/employeeResult.jsp#L19), the scriptlet contains:

```java
String ageStr = request.getParameter("age");
int age = Integer.parseInt(ageStr);
```

When an uncaught runtime exception occurs in a JSP scriptlet, Tomcat's generated servlet `_jspService()` terminates and Tomcat returns an **HTTP 500 Internal Server Error** page. This happens under two circumstances:

1. **Direct Page Access (GET Request):** Navigating directly to `employeeResult.jsp` in the browser URL bar bypasses the form submission. `request.getParameter("age")` returns `null`. Executing `Integer.parseInt(null)` immediately throws:
   ```text
   java.lang.NumberFormatException: null
   ```
2. **Empty or Non-Numeric Value:** If the age field is submitted blank or contains non-numeric text, `Integer.parseInt("")` throws:
   ```text
   java.lang.NumberFormatException: For input string: ""
   ```

### The Fix (Robust Validation Pattern from Experiment 2b)

As demonstrated in [`Experiment-2b/employeeResult.jsp`](./Experiment-2b/employeeResult.jsp), wrap `Integer.parseInt()` in a `try-catch` block and validate the request:

```jsp
<%
    String ageStr = request.getParameter("age");
    int age = 0;
    boolean hasError = false;
    String errorMessage = "";

    if (ageStr == null || ageStr.trim().isEmpty()) {
        hasError = true;
        errorMessage = "Age parameter is missing. Please submit the form.";
    } else {
        try {
            age = Integer.parseInt(ageStr.trim());
            if (age < 18 || age > 65) {
                hasError = true;
                errorMessage = "Age must be between 18 and 65.";
            }
        } catch (NumberFormatException e) {
            hasError = true;
            errorMessage = "Invalid age entered. Please provide a valid integer.";
        }
    }

    if (hasError) {
%>
    <div style="color: red; border: 1px solid red; padding: 10px;">
        <h3>Input Error</h3>
        <p><%= errorMessage %></p>
        <a href="employeeForm.jsp">&larr; Return to Form</a>
    </div>
<%
    } else {
        int birthYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) - age;
%>
    <!-- Render output table safely -->
<%
    }
%>
```

---

## 9. Source-Supported vs. Confirmed Repository Details

| Detail | Status in Original Video | Confirmed Repository Value | Source Location |
|---|---|---|---|
| **Servlet Class** | Identified as `MyServlet.java` | Confirmed `MyServlet.java` & `SimpleServlet.java` | [`Experiment-1a/MyServlet.java`](./Experiment-1a/MyServlet.java) |
| **Servlet Package** | Unconfirmed | Confirmed `com.example` | [`Experiment-1a/MyServlet.java`](./Experiment-1a/MyServlet.java) |
| **Servlet URL Mapping** | Unconfirmed | Confirmed `@WebServlet("/MyServlet")` | [`Experiment-1a/MyServlet.java`](./Experiment-1a/MyServlet.java) |
| **Servlet Methods** | Unconfirmed | Confirmed `doGet()` and `doPost()` | [`Experiment-1a/MyServlet.java`](./Experiment-1a/MyServlet.java) |
| **JSP Employee Form** | Visible in Video 3 | Confirmed `employeeForm.jsp` | [`Experiment-2a/employeeForm.jsp`](./Experiment-2a/employeeForm.jsp) |
| **Form Fields** | ID, Name, Age, Dept, Email | Confirmed `employeeId`, `name`, `age`, `department`, `email` | [`Experiment-2a/employeeForm.jsp`](./Experiment-2a/employeeForm.jsp) |
| **Form Action Target** | Unconfirmed | Confirmed `employeeResult.jsp` via `POST` | [`Experiment-2a/employeeForm.jsp`](./Experiment-2a/employeeForm.jsp) |
| **HTTP 500 Error Cause** | Unconfirmed | Confirmed uncaught `NumberFormatException` in `Integer.parseInt(ageStr)` | [`Experiment-2a/employeeResult.jsp`](./Experiment-2a/employeeResult.jsp#L19) |
| **HTTP 500 Error Fix** | Unconfirmed | Confirmed `try-catch (NumberFormatException)` with validation checks | [`Experiment-2b/employeeResult.jsp`](./Experiment-2b/employeeResult.jsp#L74-L86) |
