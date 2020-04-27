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
@WebServlet("/ProfessorResult")
public class ProfessorResult extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	private ArrayList<Integer> ids;
    
	public ProfessorResult() {
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
		//ArrayList<Integer> ids=(ArrayList<Integer>)request.getAttribute("ids");
		String search=(String)request.getParameter("search");
		find_professors(search);
		ArrayList<Professor> professors=new ArrayList<Professor>();
		for(int i=0;i<ids.size();i++) {
			Professor p=new Professor(ids.get(i));
			p.init_courses();
			professors.add(p);
		}
		request.setAttribute("professors", professors);
		RequestDispatcher dispatcher=request.getRequestDispatcher("Ruchi_Pages/profs.jsp");
        dispatcher.forward(request, response);	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void find_professors(String search) {
		ids=new ArrayList<Integer>();
		String[] split=search.split(" ");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			if(split.length==0)return;
			if(split.length==1) {
				ps = conn.prepareStatement("SELECT * FROM Professor "
						+ "WHERE fname='"+split[0]+"';");
			}
			else {
				ps = conn.prepareStatement("SELECT * FROM Professor "
						+ "WHERE fname='"+split[0]+"' and lname='"+split[1]+"';");
			}
		
			rs = ps.executeQuery();
			while(rs.next()) {
				ids.add(rs.getInt("professorID"));
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
	}
}

