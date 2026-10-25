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
