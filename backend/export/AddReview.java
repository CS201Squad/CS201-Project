package export;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


/**
 * Servlet implementation class Login
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SecureRandom random = new SecureRandom();
	private static byte[] salt;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	
    public AddReview() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		if(session.getAttribute("logged")==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);	
		}
		if(!(boolean)session.getAttribute("logged")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
		}
		Student s=(Student)session.getAttribute("student");
		int studentId=(int)s.get_studentID();
		int coursenum=Integer.parseInt(request.getParameter("num"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		String prefix=request.getParameter("prefix");
		CourseProfessor cp=new CourseProfessor(prefix, coursenum, pid);
		int courseId=cp.get_courses().get(0).get_courseID();
		String content=request.getParameter("addtlComments");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String overall=request.getParameter("overall");
		String difficulty=request.getParameter("difficulty");
		String clarity=request.getParameter("clarity");
		String workload=request.getParameter("workload");
		String attendance="false";
		String textbook="false";
		if(request.getParameter("attendance").contentEquals( "Yes")){
			attendance="true";
		}
		if(request.getParameter("textbook").contentEquals( "Yes")){
			textbook="true";
		}
		
		add_review(Integer.toString(courseId), content, Integer.toString(studentId),timestamp.toString(), 
				overall,difficulty,workload,clarity,textbook,attendance);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Reviews?pre="+prefix+"&num="+request.getParameter("num")
		+"&pid="+request.getParameter("pid"));
        dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void add_review(String courseID, String content, String studentID, String time, 
			String overall, String difficulty, String clarity, String workload, String textbook, String attendance) {
		conn=null;
		ps=null;
		rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("Insert into review(courseID,content,studentID,create_at,"
					+ "overall,difficulty,workload,clarity,textbook,attendance) values("+courseID+",'"+
					content+"',"+studentID+","+"'2020-04-26 01:02:03'"+","+overall+","+difficulty+","+clarity+","+workload+
					","+textbook+","+attendance+")");
			System.out.println("Insert into review(courseID,content,studentID,create_at,"
					+ "overall,difficulty,workload,clarity,textbook,attendance) values("+courseID+","+
					content+","+studentID+","+"2020-04-26 01:02:03"+","+overall+","+difficulty+","+clarity+","+workload+
					","+textbook+","+attendance+")");
			ps.executeUpdate();
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
	}
	
	

}
