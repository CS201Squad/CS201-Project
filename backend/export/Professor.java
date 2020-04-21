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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Professor {
	private int professorID;
	private String fname;
	private String lname;
	private String email;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	private HashMap<String,CourseProfessor> courses;
	
	public double get_overall() {
		double total=0;
		double count=0;
		Iterator hmiterator=courses.entrySet().iterator();
		
		while(hmiterator.hasNext()){
			Map.Entry mapElement = (Map.Entry)hmiterator.next();
			CourseProfessor cp=(CourseProfessor)mapElement.getValue();
			total+=cp.get_overall();
			count++;
		}
		return total/count;
	}
	public int get_professorID() {
		return this.professorID;
	}
	
	public String get_fname() {
		return this.fname;
	}
	
	public String get_lname() {
		return this.lname;
	}
	
	public String get_email() {
		return this.email;
	}
	
	public HashMap<String, CourseProfessor> get_courses(){
		return this.courses;
	}
	
	public Professor(int ID) {
		this.professorID=ID;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Professor "
					+ "WHERE professorID="+Double.toString(professorID)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.professorID=rs.getInt("professorID");
				this.fname=rs.getString("fname");
				this.lname=rs.getString("lname");
				this.email=rs.getString("email");
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
	
	public void init_courses() {
		courses=new HashMap<String, CourseProfessor>();
		conn=null;
		ps=null;
		rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM course "
					+ "WHERE professorID="+Double.toString(professorID)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				String pre=rs.getString("prefix");
				int courseNum=rs.getInt("courseNum");
				String key=pre+Double.toString(courseNum);
				if(!courses.containsKey(key)) {
					courses.put(key, new CourseProfessor(pre, courseNum, this.get_professorID()));
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
	}
}
