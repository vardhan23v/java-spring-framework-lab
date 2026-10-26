# Experiment 1a: Simple Servlet (GET & POST Request Handling)

## 🎯 Aim & Objective
Implement a Java Servlet (`SimpleServlet`) that handles both **HTTP GET** and **HTTP POST** requests:
- **HTTP GET:** Generates an HTML response containing a form with fields for `name` and `password`.
- **HTTP POST:** Reads the form parameters submitted by the client (`HttpServletRequest.getParameter()`) and outputs an HTML response displaying the submitted data.

---

## 📦 Required Packages & Dependencies

| Package | Class / Interface | Purpose |
|---|---|---|
| `javax.servlet` | `ServletException` | Servlet lifecycle exception |
| `javax.servlet.annotation` | `@WebServlet` | Declares URL pattern mapping without `web.xml` |
| `javax.servlet.http` | `HttpServlet`, `HttpServletRequest`, `HttpServletResponse` | HTTP protocol support and request/response abstraction |
| `java.io` | `IOException`, `PrintWriter` | Writing HTML character stream to response |

> **Note on Servlet Specification:**
> - If using **Apache Tomcat 9.0**: Use `javax.servlet.*`
> - If using **Apache Tomcat 10.1+**: Use `jakarta.servlet.*`

---

## 🛠 Step-by-Step Eclipse Setup & File Creation

### 1. Create a Dynamic Web Project
1. Open **Eclipse IDE for Enterprise Java and Web Developers**.
2. Go to **File -> New -> Dynamic Web Project**.
3. **Project Name:** `ServletDemo`
4. **Target Runtime:** Select **Apache Tomcat v9.0** (if not listed, click *New Runtime...*, point to your Tomcat directory, and finish).
5. **Dynamic web module version:** `4.0` (or `3.1`).
6. Click **Next** -> **Next** -> Check **"Generate web.xml deployment descriptor"** -> Click **Finish**.

### 2. Create the Servlet Class File
1. In the Project Explorer, expand:
   ```text
   ServletDemo -> Java Resources -> src (or src/main/java)
   ```
2. Right-click `src` -> **New -> Class**.
3. Fill in the class creation wizard:
   - **Package:** `com.example`
   - **Name:** `SimpleServlet` (or `MyServlet`)
   - **Superclass:** `javax.servlet.http.HttpServlet`
4. Click **Finish**.
5. Copy and paste the code from [`SimpleServlet.java`](./SimpleServlet.java) or [`MyServlet.java`](./MyServlet.java) into this newly created file.
6. Press `Ctrl + S` (or `Cmd + S` on Mac) to save. Eclipse will automatically compile the class.

---

## 🚀 How to Run & Execute in Eclipse

1. **Deploy and Start on Tomcat:**
   - In Project Explorer, right-click `SimpleServlet.java` (or the `ServletDemo` project).
   - Select **Run As -> Run on Server**.
   - Choose **Select an existing server** -> Select **Tomcat v9.0 Server at localhost**.
   - Click **Finish**.
2. **Open in Browser:**
   - Eclipse opens its internal browser automatically.
   - You can also open Chrome, Firefox, or Safari and navigate to:
     ```text
     http://localhost:8080/ServletDemo/SimpleServlet
     ```
3. **Test the Workflow:**
   - **Step 1:** The page loads showing an HTML form (handled by `doGet()`).
   - **Step 2:** Type a Name (e.g., `Vardhan`) and a Password (e.g., `pass123`).
   - **Step 3:** Click **Submit**.
   - **Step 4:** The form sends an HTTP POST request to the servlet. The servlet executes `doPost()` and displays the submitted details on screen.

---

## 🔍 Code Walkthrough

```java
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
```
- `@WebServlet("/SimpleServlet")` maps the URL `/SimpleServlet` to this servlet class directly without requiring configuration in `web.xml`.
- `extends HttpServlet` inherits HTTP handling capabilities from the servlet container.

```java
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    // Outputs the HTML form pointing back to SimpleServlet via POST
}
```
- `response.setContentType("text/html")` instructs the client browser to render the output as an HTML document.
- `response.getWriter()` gets the character output stream.

```java
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    // Displays the retrieved data
}
```
- `request.getParameter("key")` extracts values submitted by HTML form `<input name="key">` elements.

---

## 🧪 Expected Output

### 1. Form Display (GET)
```text
Submit Your Details
Name:     [ Alice ]
Password: [ ***** ]
[ Submit ]
```

### 2. Form Submission (POST)
```text
Submitted Data
Name: Alice
Password: pass123
```
