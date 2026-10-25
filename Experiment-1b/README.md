# Experiment 1b: SessionServlet (Session Tracking, Cookies & Authentication)

## 🎯 Aim & Objective
Implement a Java Servlet (`SessionServlet`) to demonstrate stateful session management using `HttpSession` and cookies:
- Initial access presents an authentication login form.
- Upon valid login credentials (`admin` / `password`), establish a user session on the server and bind the username as a session attribute.
- On subsequent requests, identify the active session and print session metadata: **Session ID**, **Creation Time**, and **Last Accessed Time**.
- Provide a logout mechanism that calls `session.invalidate()` to destroy the session.

---

## 📦 Required Packages & Dependencies

| Package | Class / Interface | Purpose |
|---|---|---|
| `javax.servlet.http` | `HttpSession` | Manages conversation state across multiple HTTP requests |
| `javax.servlet.http` | `HttpServlet`, `HttpServletRequest`, `HttpServletResponse` | Request retrieval, cookie parsing, redirection |
| `javax.servlet.annotation` | `@WebServlet` | Declares URL pattern mapping |
| `java.util` | `Date` | Formats millisecond timestamps into human-readable date/time strings |

---

## 🛠 Step-by-Step Eclipse Setup & File Creation

### 1. In Existing or New Dynamic Web Project
- You can reuse the project created in Exp 1a (`ServletDemo`), or create a new Dynamic Web Project named `ServletDemo` (**File -> New -> Dynamic Web Project** with Target Runtime **Apache Tomcat v9.0**).

### 2. Create the Servlet Class File
1. In Project Explorer, expand `ServletDemo -> Java Resources -> src`.
2. Right-click package `com.example` -> **New -> Class**.
3. **Name:** `SessionServlet`
4. **Superclass:** `javax.servlet.http.HttpServlet`
5. Click **Finish**.
6. Replace the generated stub with the source code from [`SessionServlet.java`](./SessionServlet.java).
7. Save the file (`Ctrl + S` or `Cmd + S`).

---

## 🚀 How to Run & Execute in Eclipse

1. **Start the Server:**
   - Right-click `SessionServlet.java` -> **Run As -> Run on Server**.
   - Choose your existing Tomcat 9 server -> Click **Finish**.
2. **Access the Application:**
   - In browser, go to:
     ```text
     http://localhost:8080/ServletDemo/SessionServlet
     ```
3. **Execution Steps:**
   - **Step 1 (Login View):** The servlet sees no active session (`session == null`) and displays the Login form.
   - **Step 2 (Authenticate):** Enter:
     - **Username:** `admin`
     - **Password:** `password`
     - Click **Login**.
   - **Step 3 (Welcome Screen):** The server creates a new session, sets cookie `JSESSIONID`, and displays:
     - Welcome message with username.
     - Unique Session ID string.
     - Creation timestamp and last accessed timestamp.
   - **Step 4 (Logout):** Click the **Logout** link.
     - The servlet invalidates the session via `session.invalidate()` and redirects back to the login screen.

---

## 🔍 Code Walkthrough

1. **Checking Session State (`request.getSession(false)`):**
   ```java
   HttpSession session = request.getSession(false);
   ```
   Passing `false` returns the active session if one exists, or `null` if none exists. It prevents creating unwanted empty sessions.

2. **Creating Session on Successful Login:**
   ```java
   HttpSession session = request.getSession(true);
   session.setAttribute("username", username);
   ```
   Passing `true` creates a new session. `setAttribute()` binds the user credential to server memory.

3. **Invalidating Session on Logout:**
   ```java
   session.invalidate();
   response.sendRedirect("SessionServlet");
   ```
   Destroys the session object, releases memory, and redirects to the login form.

---

## 🧪 Expected Output

### 1. Initial Access / Logged Out:
```text
Login
Username: [ admin    ]
Password: [ ******** ]
[ Login ]
```

### 2. After Successful Login:
```text
Welcome, admin!
Logout

Session Information:
Session ID: 48B1CF73E475A96489311DA457BD3F92
Creation Time: Sun Oct 25 23:25:10 IST 2026
Last Accessed Time: Sun Oct 25 23:25:12 IST 2026
```
