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

public class CourseProfessor {
	private String prefix;
	private int number;
	private int professorID;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	private ArrayList<course> courses;
	Professor p;
	
	public ArrayList<course> get_courses(){
		return this.courses;
	}
	public String get_prefix() {
		return this.prefix;
	}
	
	public int get_courseNum() {
		return this.number;
	}
	
	public double get_overall() {
		double total=0;
		double count=0;
		for(int i=0;i<courses.size();i++) {
			total+=courses.get(i).get_overall();
			count++;
		}
		return total/count;
	}
	
	public double get_clarity() {
		double total=0;
		double count=0;
		for(int i=0;i<courses.size();i++) {
			total+=courses.get(i).get_clarity();
			count++;
		}
		return total/count;
	}
	
	public double get_difficulty() {
		double total=0;
		double count=0;
		for(int i=0;i<courses.size();i++) {
			total+=courses.get(i).get_difficulty();
			count++;
		}
		return total/count;
	}
	
	public double get_workload() {
		double total=0;
		double count=0;
		for(int i=0;i<courses.size();i++) {
			total+=courses.get(i).get_workload();
			count++;
		}
		return total/count;
	}
	
	public Professor get_professor() {
		return this.p;
	}
	
	public CourseProfessor(String pre, int num, int professorI) {
		prefix=pre;
		number=num;
		professorID=professorI;
		courses=new ArrayList<course>();
		/*
		int i;
		for (i=0;i<search.length();i++) {
			if(Character.isLetter(search.charAt(i))) {
				prefix+=search.charAt(i);
			}
		}
		if(!(i==search.length())) {
			number=(double)Integer.parseInt(search.substring(i));
		}*/
		
		try {
			p=new Professor(professorID);
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM course "
					+ "WHERE professorID="+Integer.toString(professorID)+" and prefix='"+prefix+
					"' and courseNum="+Integer.toString(number)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				courses.add(new course(rs.getInt("courseID"),p));
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
