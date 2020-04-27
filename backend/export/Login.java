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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SecureRandom random = new SecureRandom();
	private static byte[] salt;
	
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext ctx=request.getServletContext();
		salt=(byte[])ctx.getAttribute("salt");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession sess=request.getSession();
		sess.setAttribute("logged", false);
		Boolean x = signin(email, password, request);

		if(x) {
			if((boolean)sess.getAttribute("isStudent")==true) {
				request.setAttribute("s", sess.getAttribute("student"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("Julie_Pages/search.jsp");
	            dispatcher.forward(request, response);	
			}
			//send them to the next page
			else {
				Professor p=(Professor)sess.getAttribute("professor");
				RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorProfile?pid="+
						Integer.toString(p.get_professorID()));
				dispatcher.forward(request, response);	
			}
		}
		else {
			//stay on page, or maybe a page that says incorrect password/username
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
            dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static Boolean signin(String email, String pass, HttpServletRequest request) {
		Boolean access = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String hashed = hash(pass);	//get hash of password
		//compare in database the hash with the email and see if they correspond
		//use preparedstatement so they cant do that fake shit
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Student "
					+ "WHERE password_hash='" + hashed +"' AND email='" + email + "';");
			rs = ps.executeQuery();
			while(rs.next()) {
				Integer ID = rs.getInt("studentID");
				if(ID != null) {
					access = true;
					Student s=new Student(rs.getInt("studentID"));
					s.init_reviews();
					HttpSession session=request.getSession();
					session.setAttribute("student", s);
					session.setAttribute("isStudent", true);
					session.setAttribute("logged", true);
					return true;
				}
			}
			
			
			ps = null;
			rs = null;
			ps = conn.prepareStatement("SELECT professorID FROM professor "
					+ "WHERE password_hash='" + hashed +"' AND email='" + email + "';");
			System.out.println("SELECT professorID FROM professor "
					+ "WHERE password_hash='" + hashed +"' AND email='" + email + "';");
			rs = ps.executeQuery();
			while(rs.next()) {
				Integer ID = rs.getInt("professorID");
				if(ID != null) {
					System.out.println(email);
					access = true;
					Professor p=new Professor(rs.getInt("professorID"));
					HttpSession session=request.getSession();
					session.setAttribute("professor", p);
					session.setAttribute("isStudent", false);
					session.setAttribute("logged", true);
					return true;
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
		System.out.println(access);
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
