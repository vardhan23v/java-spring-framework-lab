<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.regex.*" %>
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

        // 2. Error messages
        String employeeIdError = "";
        String nameError       = "";
        String ageError        = "";
        String departmentError = "";
        String emailError      = "";
        boolean isValid = true;

        // 3. Validate Employee ID
        if (employeeId == null || employeeId.trim().isEmpty()) {
            employeeIdError = "Employee ID is required.";
            isValid = false;
        }

        // 4. Validate Name (letters and spaces only)
        if (name == null || !name.matches("^[a-zA-Z\\s]+$")) {
            nameError = "Name must contain only letters and spaces.";
            isValid = false;
        }

        // 5. Validate Age (18–65)
        int age = 0;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 18 || age > 65) {
                ageError = "Age must be between 18 and 65.";
                isValid = false;
            }
        } catch (NumberFormatException e) {
            ageError = "Age must be a valid numeric value.";
            isValid = false;
        }

        // 6. Validate Department
        if (department == null || department.trim().isEmpty()) {
            departmentError = "Department is required.";
            isValid = false;
        } else if (!department.matches("HR|Finance|IT|Sales")) {
            departmentError = "Invalid department selected.";
            isValid = false;
        }

        // 7. Validate Email
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            emailError = "Email must be in a valid format (e.g., name@example.com).";
            isValid = false;
        }

        if (isValid) {
            // Process data
            int birthYear          = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) - age;
            String nameAndDept     = name + " (" + department + ")";
            String formattedEmail  = email.toLowerCase();
    %>
            <table border="1">
                <tr><th>Employee ID</th>         <td><%= employeeId %></td></tr>
                <tr><th>Name and Department</th> <td><%= nameAndDept %></td></tr>
                <tr><th>Birth Year</th>          <td><%= birthYear %></td></tr>
                <tr><th>Formatted Email</th>     <td><%= formattedEmail %></td></tr>
            </table>
    <%
        } else {
    %>
            <h3>Validation Errors</h3>
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
            <a href="employeeForm.jsp">Go back to the form</a>
    <%
        }
    %>
</body>
</html>
