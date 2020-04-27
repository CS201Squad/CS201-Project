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
@WebServlet("/ProfessorProfile")
public class ProfessorProfile extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	
    public ProfessorProfile() {
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
		
		Professor p=new Professor(Integer.parseInt(request.getParameter("pid")));
		request.setAttribute("professor", p);
		p.init_courses();
		RequestDispatcher dispatcher=request.getRequestDispatcher("Sophia's_Pages/ProfessorProfile.jsp");
        dispatcher.forward(request, response);	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

