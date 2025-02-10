package projectl.ProjectL;

import java.io.IOException;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet(name = "updateEmployee", urlPatterns = { "/updateEmployee" })
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		System.out.println(employeeId);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.sendRedirect("./employees");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "password");
			String query = "SELECT * FROM employees WHERE id = ?";
			PreparedStatement prpt = cnn.prepareStatement(query);
			prpt.setInt(1, employeeId);
			ResultSet results = prpt.executeQuery();
			if (results.next()) {
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				int age = results.getInt("age");
				double salary = results.getDouble("salary");
//				System.out.println(firstName + " " + lastName);
//				System.out.println(age);
//				System.out.println(salary);
//				System.out.println("--------");
				Employee employee = new Employee(employeeId, firstName, lastName, age, salary);
				request.setAttribute("employee", employee);
				request.getRequestDispatcher("updateemployee.jsp").forward(request, response);
			}
			
			
			
			cnn.close();
			prpt.close();
			results.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "password");
			String query = "UPDATE employees SET first_name = ?, last_name = ?, age = ?, salary = ? WHERE id = ?";
			PreparedStatement prpt = cnn.prepareStatement(query);
			
			prpt.setString(1, firstName);
			prpt.setString(2, lastName);
			prpt.setInt(3, age);
			prpt.setDouble(4, salary);
			prpt.setInt(5, employeeId);
			
//			System.out.println("----New Details----");
//			System.out.println(firstName + " " + lastName);
//			System.out.println(age);
//			System.out.println(salary);
			prpt.execute();
			cnn.close();
			prpt.close();
			
			response.sendRedirect("./employees"); 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
