package csci201;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	
	// TODO: Change info here
	private static String sqlServer = "jdbc:mysql://localhost";
	private static String sqlDatabase = "RateSC";
	private static String sqlUsername = "root";
	private static String sqlPassword = "clifford";
	private static String connectionURL = sqlServer + "/" + sqlDatabase;

    public AddReview() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Do we still need this?
		String term = request.getParameter("term");
		String year = request.getParameter("year");
		
		String overall = request.getParameter("overallrate");
		String difficulty = request.getParameter("difficulty");
		String workload = request.getParameter("workload");
		String clarity = request.getParameter("clarity");
		String textbook = request.getParameter("textbook"); // yes or no
		String attendance = request.getParameter("attendance"); // yes or no
		
		String content = request.getParameter("addtlComments");
		
		// TODO: Redirect to new page
		// String next = "/...";
		
		// if (email != null && email.equalsIgnoreCase("asdf@asdf.com")) {
			// next = "/schoolform.jsp";
		// } else {
			// request.setAttribute("error", "Email address was not correct.");
		// }
		
		// RequestDispatcher dispatch = getServletContext().getRequestDispatcher(next);
		// dispatch.forward(request, response);
		
		// TODO: get courseID, studentID
		try {
			Date d = new Date();
			
			conn = DriverManager.getConnection(connectionURL, sqlUsername, sqlPassword);
			ps = conn.prepareStatement("INSERT INTO Review (create_at, overall, difficulty, workload, clarity, textbook, attendance, content) "
					+ "VALUES (" + d + ", " + overall + ", " + difficulty + ", " + workload + ", " + clarity + ", " + content + ", " + textbook + ", " + attendance + ")");
			ps.execute();
			
			
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
