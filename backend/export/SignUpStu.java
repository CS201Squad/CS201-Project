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

/**
 * Servlet implementation class SignUpStu
 */
@WebServlet("/SignUpStu")
public class SignUpStu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static byte[] salt;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpStu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ADD ALL THE PARAMETERS AND MAKE SURE YOU MAKE THE SALT UNIVERSAL
		ServletContext ctx=request.getServletContext();
		salt=(byte[])ctx.getAttribute("salt");
		String SID = request.getParameter("id");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		double id = new Double(SID);
		System.out.println("here");
		System.out.flush();
		if(!check(email)) {
			signupStudent(id, email, password);
			Student s=new Student((int)id);
			s.init_reviews();
			HttpSession session=request.getSession();
			session.setAttribute("student", s);
			session.setAttribute("isStudent", true);
			session.setAttribute("logged", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Julie_Pages/search.jsp");
            dispatcher.forward(request, response);
			//send it to next page
		}
		else{
			PrintWriter out=response.getWriter();
			out.write("Email or USCID is already registered to a user.");
		}
		//send them back to login and tell them that their email is already used
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext ctx=request.getServletContext();
		salt=(byte[])ctx.getAttribute("salt");
		doGet(request, response);
	}
	
	public static void signupStudent(Double id, String email, String pass){
		Connection conn = null;
		Statement ss = null;
		ResultSet rs = null;
		
		String hashed = hash(pass);	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ss = conn.createStatement();
			int x = ss.executeUpdate("INSERT INTO Student (studentID, email, password_hash)"
					+ " VALUES (" + id + ", '"+ email + "', '" + hashed +"');");
			
			
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
				if (ss != null) {
					ss.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		
	}
	
	public static Boolean check(String email) {
		Boolean access = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//compare in database the hash with the email and see if they correspond
		//use preparedstatement so they cant do that fake shit
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT studentID FROM Student "
					+ "WHERE email='" + email + "';");
			rs = ps.executeQuery();
			while(rs.next()) {
				Integer ID = rs.getInt("studentID");
				if(ID != null) {
					access = true;
				}
			}
			
			
			
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
		
		return access;
		
	}
	
	public static String hash(String original) {
		String done = null;
		
		try {
			KeySpec spec = new PBEKeySpec(original.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			byte[] hashed = new byte[salt.length + hash.length];
			System.arraycopy(salt, 0, hashed, 0, salt.length);
		    System.arraycopy(hash, 0, hashed, salt.length, hash.length);
		    Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
			done = enc.encodeToString(hashed);
			return done;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return done;
	}

}
