//FIX THE SALTING
package export;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.sql.PreparedStatement;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
/**
 * Servlet implementation class SignUpStu
 */
@WebServlet("/Reviews")
public class Reviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static byte[] salt;
    private int id;
    private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reviews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ADD ALL THE PARAMETERS AND MAKE SURE YOU MAKE THE SALT UNIVERSAL
		HttpSession session=request.getSession();
		if(session.getAttribute("logged")==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);	
		}
		if(!(boolean)session.getAttribute("logged")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
		}
		Student s=(Student)(session.getAttribute("student"));
		
		id=s.get_studentID();
		boolean canReview=taken(id, request.getParameter("pre"), Integer.parseInt(request.getParameter("num")),Integer.parseInt(request.getParameter("pid")));
		request.setAttribute("canReview", !canReview);
		CourseProfessor cp=new CourseProfessor(request.getParameter("pre"), 
				Integer.parseInt(request.getParameter("num")),
				Integer.parseInt(request.getParameter("pid")));	
		request.setAttribute("cp", cp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Julie_Pages/cpr.jsp");
        dispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public boolean taken(int studentID, String prefix, int courseNum, int professorID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("Select * from taken where studentID="+Integer.toString(studentID)+" and professorID="
			+Integer.toString(professorID)+" and prefix='"+prefix+"' and courseNum="+Integer.toString(courseNum)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				return false;
			}
			return true;
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return true;
	}
}
