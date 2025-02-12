package projectl.ProjectL;


import java.io.IOException;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/addemployee")

public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	response.sendRedirect("./addemployee.html");
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String salary = request.getParameter("salary");
        try {
//        	System.out.println("Try block started");
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Driver loaded");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "password");
//			System.out.println("Connection established");
			String query = "INSERT INTO employees VALUES(?, ?, ?, ?, ?)";
			PreparedStatement prpt = cnn.prepareStatement(query);
			prpt.setInt(1, Integer.parseInt(id));
			prpt.setString(2, firstName);
			prpt.setString(3, lastName);
			prpt.setInt(4, Integer.parseInt(age));
			prpt.setDouble(5, Double.parseDouble(salary));
			prpt.execute();
//			System.out.println("Database connected");
//			System.out.println("ID: " + id);
//		    System.out.println("First Name: " + firstName);
//		    System.out.println("Last Name: " + lastName);
//		    System.out.println("Age: " + age);
//		    System.out.println("Salary: " + salary);
//		    System.out.println("----Details uploaded----");
			response.sendRedirect("employees.jsp");
			cnn.close();
			prpt.close();
		} catch (ClassNotFoundException | SQLException e) {
//			System.out.println("not connected");
			e.printStackTrace();
		}
        
        
    }


}
