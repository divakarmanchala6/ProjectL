<%@ page import="java.util.ArrayList" %>
<%@ page import="projectl.ProjectL.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>
    <!-- Link to external CSS -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Employee List</h1>
	<p><a href="./addemployee">Add Employee</a></p>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Salary</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Retrieve the list of employees from the request and cast it properly
                Object employeesObj = request.getAttribute("employees");
                if (employeesObj instanceof ArrayList<?>) {
                    ArrayList<Employee> employees = (ArrayList<Employee>) employeesObj;

                    if (employees != null && !employees.isEmpty()) {
                        // Iterate through each employee and display their details in the table
                        for (Employee employee : employees) {
            %> 
            <tr>
                <td><%= employee.getId() %></td>
                <td><%= employee.getFirstName() %></td>
                <td><%= employee.getLastName() %></td>
                <td><%= employee.getAge() %></td>
                <td><%= employee.getSalary() %></td>
                <td>
                   <td>
					   <form action="updateEmployee" method="GET">
					      <input type="hidden" name="employeeId" value="<%= employee.getId() %>" />
					      <button type="submit">Edit</button>
					   </form>
					   <form action="deleteEmployee" method="POST">
					      <input type="hidden" name="employeeId" value="<%= employee.getId() %>" />
					      <button type="submit">Delete</button>
					   </form>
					</td>

                </td>
            </tr>
            <% 
                        }
                    } else {
            %>
            <tr>
                <td colspan="6">No employees found.</td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="6">Error: Employees list is not available.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
