<%@ page import="projectl.ProjectL.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
</head>
<body>
<h2>Edit Employee</h2>
<% 
    // Retrieve employee from request (set in doGet in servlet)
    Employee employee = (Employee) request.getAttribute("employee");
%>
<form action="updateEmployee" method="POST">
    <input type="hidden" name="employeeId" value="<%= employee.getId() %>" />
    <label>First Name:</label>
    <input type="text" name="firstName" value="<%= employee.getFirstName() %>" required/><br>

    <label>Last Name:</label>
    <input type="text" name="lastName" value="<%= employee.getLastName() %>" required/><br>

    <label>Age:</label>
    <input type="number" name="age" value="<%= employee.getAge() %>" required/><br>

    <label>Salary:</label>
    <input type="number" name="salary" value="<%= employee.getSalary() %>" required/><br>

    <button type="submit">Save Changes</button>
</form>

</body>
</html>
