<%@ page import="com.studentcrud.StudentDAO" %>
<%
    int rollNo = Integer.parseInt(request.getParameter("rollNo"));
    new StudentDAO().deleteStudent(rollNo);
    out.println("Student deleted successfully.");
%>
<html>
<body>
    <a href="viewStudents.jsp">View Students</a>
</body>
</html>
