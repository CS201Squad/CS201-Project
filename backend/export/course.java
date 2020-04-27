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
import java.util.concurrent.*;

public class course implements Runnable{
	private Professor professor;
	private int courseID;
	private int courseNum;
	private String term;
	private String prefix;
	private int professorID;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private int studentId;
	private ResultSet rs = null;
	private ArrayList<Review> reviews=new ArrayList<Review>();
	private double overall;
	private double clarity;
	private double workload;
	private double difficulty;
	private int gradeID;
	private Grades grades;
	private String name;
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
	
	public String get_name() {
		return this.name;
	}
	public Grades get_grades() {
		return this.grades;
	}
	public int courseNum() {
		return this.courseNum;
	}
	
	public double get_overall() {
		return this.overall;
	}
	
	public double get_clarity() {
		return this.clarity;
	}
	
	public double get_difficulty() {
		return this.difficulty;
	}
	
	public double get_workload() {
		return this.workload;
	}
	
	public ArrayList<Review> get_reviews(){
		return this.reviews;
	}
	public String get_prefix() {
		return this.prefix;
	}
	public int get_studentID() {
		return this.studentId;
	}
	public int get_professorID() {
		return this.professorID;
	}
	public String term() {
		return this.term;
	}
	public int get_courseID() {
		return this.courseID;
	}
	public Professor get_professor() {
		return this.professor;
	}
	public course(int ID) {
		this.courseID=ID;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Course "
					+ "WHERE courseID="+Double.toString(courseID)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.professorID=rs.getInt("professorID");
				this.courseID=rs.getInt("courseID");
				this.prefix=rs.getString("prefix");
				this.courseNum=rs.getInt("courseNum");
				this.term=rs.getString("term");
				this.clarity=rs.getDouble("clarity");
				this.overall=rs.getDouble("overall");
				this.difficulty=rs.getDouble("difficulty");
				this.workload=rs.getDouble("workload");
				this.gradeID=rs.getInt("gradeID");
				this.name=rs.getString("title");
			}
			this.professor=new Professor(professorID);
			this.grades=new Grades(this.gradeID);
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
	
	public course(int ID, Professor p, boolean async) {
		this.courseID=ID;
		this.professor=p;
	}
	
	public course(int ID, Professor p) {
		this.courseID=ID;
		this.professor=p;
		try {
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Course "
					+ "WHERE courseID="+Double.toString(courseID)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.professorID=rs.getInt("professorID");
				this.courseID=rs.getInt("courseID");
				this.prefix=rs.getString("prefix");
				this.courseNum=rs.getInt("courseNum");
				this.term=rs.getString("term");
				this.clarity=rs.getDouble("clarity");
				this.overall=rs.getDouble("overall");
				this.difficulty=rs.getDouble("difficulty");
				this.workload=rs.getDouble("workload");
				this.gradeID=rs.getInt("gradeId");
				this.name=rs.getString("title");
			}
			this.grades=new Grades(this.gradeID);
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}finally {
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
	
	public void init_reviews() {
		conn=null;
		ps=null;
		rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM review "
					+ "WHERE courseID="+Integer.toString(courseID)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				Review r=new Review(rs.getInt("reviewID"),true);
				reviews.add(r);
				executor.execute(r);
			}
			executor.shutdown();
			while(!executor.isTerminated())continue;
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
	
	public void run() {
		try {
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Course "
					+ "WHERE courseID="+Double.toString(courseID)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.professorID=rs.getInt("professorID");
				this.courseID=rs.getInt("courseID");
				this.prefix=rs.getString("prefix");
				this.courseNum=rs.getInt("courseNum");
				this.term=rs.getString("term");
				this.clarity=rs.getDouble("clarity");
				this.overall=rs.getDouble("overall");
				this.difficulty=rs.getDouble("difficulty");
				this.workload=rs.getDouble("workload");
				this.gradeID=rs.getInt("gradeId");
				this.name=rs.getString("title");
			}
			this.grades=new Grades(this.gradeID);
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}finally {
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
