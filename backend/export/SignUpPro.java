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

/**
 * Servlet implementation class SignUpPro
 */
@WebServlet("/SignUpPro")
public class SignUpPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static byte[] salt;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpPro() {
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
		HttpSession session=request.getSession();
		salt=(byte[])ctx.getAttribute("salt");
		String id = request.getParameter("id");
		String fname = request.getParameter("first name");
		String lname = request.getParameter("last name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int pID = new Integer(id);
		signupProfessor(pID, fname, lname, password, email);
		session.setAttribute("isStudent", false);
		session.setAttribute("logged", true);
		RequestDispatcher dispatcher =request.getRequestDispatcher("ProfessorProfile?pid="+Integer.toString(pID));
        dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void signupProfessor(int id, String fname, String lname, String pass, String email){
		Connection conn = null;
		Statement ss = null;
		ResultSet rs = null;
		
		String hashed = hash(pass);	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ss = conn.createStatement();
			ss.executeUpdate("update professor set email='"+email+"', password_hash='"+hashed
					+"'where professorID="+Integer.toString(id));	
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
