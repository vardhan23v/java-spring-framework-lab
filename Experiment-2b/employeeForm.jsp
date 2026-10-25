<!DOCTYPE html>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
    <h2>Employee Information Form</h2>
    <form action="employeeResult.jsp" method="POST">
        Employee ID: <input type="text" name="employeeId" required><br>
        Name: <input type="text" name="name" required><br>
        Age: <input type="number" name="age" required><br>
        Department:
        <select name="department" required>
            <option value="">Select</option>
            <option value="HR">HR</option>
            <option value="Finance">Finance</option>
            <option value="IT">IT</option>
            <option value="Sales">Sales</option>
        </select><br>
        Email: <input type="email" name="email" required><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
