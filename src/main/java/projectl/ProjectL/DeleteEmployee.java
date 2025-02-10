package projectl.ProjectL;

import java.io.IOException;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet(name = "deleteEmployee", urlPatterns = { "/deleteEmployee" })
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB", "root", "password");
			String query = "DELETE FROM employees WHERE id = ?";
			PreparedStatement prpt = cnn.prepareStatement(query);
			prpt.setInt(1, employeeId);
			System.out.println("Connected database");
			prpt.execute();
			response.sendRedirect("./employees");
			cnn.close();
			prpt.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Not Connected database");
			response.sendRedirect("./employees");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
