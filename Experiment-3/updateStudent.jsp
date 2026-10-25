<%@ page import="com.studentcrud.Student, com.studentcrud.StudentDAO" %>
<%
    StudentDAO dao = new StudentDAO();
    int rollNo = Integer.parseInt(request.getParameter("rollNo"));

    // Find the student to pre-fill the form
    Student student = new Student();
    for (Student s : dao.getAllStudents()) {
        if (s.getRollNo() == rollNo) {
            student = s;
            break;
        }
    }

    // If form was submitted, update the record
    if (request.getParameter("name") != null) {
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setAge(Integer.parseInt(request.getParameter("age")));
        dao.updateStudent(student);
        out.println("Student updated successfully.");
    }
%>
<html>
<body>
    <h2>Update Student</h2>
    <form action="updateStudent.jsp?rollNo=<%= student.getRollNo() %>" method="post">
        Name:  <input type="text" name="name"  value="<%= student.getName() %>"><br>
        Email: <input type="text" name="email" value="<%= student.getEmail() %>"><br>
        Age:   <input type="text" name="age"   value="<%= student.getAge() %>"><br>
        <input type="submit" value="Update Student">
    </form>
    <a href="viewStudents.jsp">View Students</a>
</body>
</html>
