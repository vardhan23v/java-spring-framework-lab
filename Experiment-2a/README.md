# Experiment 2a: Employee Management using JSP

## 🎯 Aim & Objective
Design a JavaServer Pages (JSP) web application to collect and process employee details:
- `employeeForm.jsp`: HTML form capturing **Employee ID**, **Name**, **Age**, **Department**, and **Email** via HTTP POST.
- `employeeResult.jsp`: Processes submitted data using JSP scriptlets (`<% ... %>`) and expressions (`<%= ... %>`):
  - Calculates birth year dynamically: `Birth Year = Current Year - Age` using `java.util.Calendar`.
  - Concatenates name and department: `Name (Department)`.
  - Converts email address to lowercase.
  - Displays the processed data in an HTML table.

---

## 📦 Required Packages & Dependencies

| Package | Class | Purpose |
|---|---|---|
| `java.util` | `Calendar` | Access current system year for calculating birth year |
| Standard JSP API | Implicit objects (`request`, `response`, `out`) | Automatic JSP container variables |

---

## 🛠 Step-by-Step Eclipse Setup & File Creation

### 1. Create a Dynamic Web Project
1. In Eclipse, go to **File -> New -> Dynamic Web Project**.
2. **Project Name:** `JSPDemo`
3. **Target Runtime:** Select **Apache Tomcat v9.0**.
4. Click **Finish**.

### 2. Create the JSP Files
1. In Project Explorer, expand:
   ```text
   JSPDemo -> WebContent (or src/main/webapp if Maven)
   ```
2. **Create `employeeForm.jsp`:**
   - Right-click `WebContent` -> **New -> JSP File**.
   - **File name:** `employeeForm.jsp` -> Click **Finish**.
   - Copy and paste the code from [`employeeForm.jsp`](./employeeForm.jsp).
3. **Create `employeeResult.jsp`:**
   - Right-click `WebContent` -> **New -> JSP File**.
   - **File name:** `employeeResult.jsp` -> Click **Finish**.
   - Copy and paste the code from [`employeeResult.jsp`](./employeeResult.jsp).
4. Save both files (`Ctrl + S` or `Cmd + S`).

---

## 🚀 How to Run & Execute in Eclipse

1. In Project Explorer, right-click `employeeForm.jsp`.
2. Select **Run As -> Run on Server**.
3. Choose your configured Tomcat server -> Click **Finish**.
4. Open your browser at:
   ```text
   http://localhost:8080/JSPDemo/employeeForm.jsp
   ```
5. **Testing the Application:**
   - Fill in the form fields:
     - **Employee ID:** `EMP101`
     - **Name:** `Sarah Connor`
     - **Age:** `30`
     - **Department:** `Cyberdyne`
     - **Email:** `Sarah.Connor@TECH.ORG`
   - Click **Submit**.
   - The form posts data to `employeeResult.jsp`, which renders the processed employee table.

---

## 🔍 Code Walkthrough

1. **Directive Tag (`<%@ page import="..." %>`):**
   ```jsp
   <%@ page import="java.util.Calendar" %>
   ```
   Imports the `Calendar` class into the generated servlet class.

2. **Scriptlet Tag (`<% ... %>`):**
   ```jsp
   <%
       String employeeId = request.getParameter("employeeId");
       int age = Integer.parseInt(request.getParameter("age"));
       int currentYear = Calendar.getInstance().get(Calendar.YEAR);
       int birthYear = currentYear - age;
   %>
   ```
   Contains procedural Java code executed inside the container's `_jspService()` method.

3. **Expression Tag (`<%= ... %>`):**
   ```jsp
   <td><%= birthYear %></td>
   ```
   Directly converts the expression result into a string and sends it to `out.print()`.

---

## 🧪 Expected Output

### Input Form (`employeeForm.jsp`):
```text
Employee Information Form
Employee ID: [ EMP101                 ]
Name:        [ Sarah Connor           ]
Age:         [ 30                     ]
Department:  [ Cyberdyne              ]
Email:       [ Sarah.Connor@TECH.ORG  ]
[ Submit ]
```

### Result View (`employeeResult.jsp`):
```text
Employee Information Result
+-----------------------+----------------------------------+
| Employee ID           | EMP101                           |
+-----------------------+----------------------------------+
| Name and Department   | Sarah Connor (Cyberdyne)         |
+-----------------------+----------------------------------+
| Birth Year            | 1996                             |
+-----------------------+----------------------------------+
| Formatted Email       | sarah.connor@tech.org            |
+-----------------------+----------------------------------+
```
