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
import java.util.Objects;

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
import java.util.ArrayList;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Search")

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SecureRandom random = new SecureRandom();
	private static byte[] salt;
	
	
    public Search() {
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
		boolean course;
		
		if(Objects.equals(request.getParameter("searchby"),"Courses"))course=true;
		else course=false;
		
		request.setAttribute("search", request.getParameter("search"));
		
		if(course) {
			System.out.println("here");
			RequestDispatcher dispatcher =request.getRequestDispatcher("ClassProfile");
			dispatcher.forward(request, response);	
		}
		else {
			RequestDispatcher dispatcher =request.getRequestDispatcher("ProfessorResult");
			dispatcher.forward(request, response);
		}   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
