package projectl.ProjectL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Employees
 */
@WebServlet(name = "employees", urlPatterns = { "/employees" })
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			System.out.println("Try block started");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "password");
			//System.out.println("Establishing the connection");
			String query = "SELECT * FROM employees";
			PreparedStatement prpt = cnn.prepareStatement(query);
			//System.out.println("Establishing the statement");
			ResultSet results = prpt.executeQuery();
			
			while (results.next()) {
				int id = results.getInt("id");
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				int age = results.getInt("age");
				double salary = results.getDouble("salary");
				Employee employee = new Employee(id, firstName, lastName, age, salary);
				employees.add(employee);
				
			}
			
			//System.out.println("Database Connected");
			
			cnn.close();
			prpt.close();
			results.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Database Not Connected");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("employees", employees);
		//response.sendRedirect("./employees");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("./employees.jsp");
//		dispatcher.forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
