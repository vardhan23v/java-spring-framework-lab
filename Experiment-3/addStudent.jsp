<%@ page import="com.studentcrud.Student, com.studentcrud.StudentDAO" %>
<%
    if (request.getParameter("rollNo") != null) {
        int    rollNo = Integer.parseInt(request.getParameter("rollNo"));
        String name   = request.getParameter("name");
        String email  = request.getParameter("email");
        int    age    = Integer.parseInt(request.getParameter("age"));

        Student student = new Student();
        student.setRollNo(rollNo);
        student.setName(name);
        student.setEmail(email);
        student.setAge(age);

        new StudentDAO().addStudent(student);
        out.println("Student added successfully.");
    }
%>
<html>
<body>
    <h2>Add Student</h2>
    <form action="addStudent.jsp" method="post">
        Roll No: <input type="text" name="rollNo"><br>
        Name:    <input type="text" name="name"><br>
        Email:   <input type="text" name="email"><br>
        Age:     <input type="text" name="age"><br>
        <input type="submit" value="Add Student">
    </form>
    <a href="viewStudents.jsp">View Students</a>
</body>
</html>
