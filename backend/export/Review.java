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


public class Review implements Runnable{
	private Student student;
	private course Course;
	private int reviewId;
	private double overall;
	private double difficulty;
	private double clarity;
	private double workload;
	private String createdAt;
	private String Content;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private int courseId;
	private int studentId;
	private ResultSet rs = null;
	private boolean text;
	private boolean att;
	
	public boolean get_text() {
		return this.text;
	}
	
	public boolean get_att() {
		return this.att;
	}
	public String get_content() {
		return this.Content;
	}
	public double studentID() {
		return this.studentId;
	}
	
	public double get_courseID() {
		return this.courseId;
	}
	
	public double get_workload() {
		return this.workload;
	}
	
	public double get_clarity() {
		return this.clarity;
	}
	public double get_difficulty() {
		return this.difficulty;
	}
	public double get_overall() {
		return this.overall;
	}
	public double get_reviewID() {
		return this.reviewId;
	}
	public Student get_student() {
		return this.student;
	}
	
	public course get_course() {
		return this.Course;
	}
	public Review(int ID) {
		this.reviewId=ID;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Review "
					+ "WHERE reviewID="+Double.toString(reviewId)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.overall=rs.getDouble("overall");
				this.difficulty=rs.getDouble("overall");
				this.clarity=rs.getDouble("overall");
				this.workload=rs.getDouble("workload");	
				this.Content=rs.getString("content");
				this.studentId=rs.getInt("studentID");
				this.courseId=rs.getInt("courseID");
			}
			this.student=new Student(this.studentId);
			this.Course=new course(this.courseId);
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
	
	public Review(int ID, boolean async) {
		this.reviewId=ID;
	}
	
	public Review(int ID, Student s) {
		this.student=s;
		this.reviewId=ID;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Review "
					+ "WHERE reviewID="+Double.toString(reviewId)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.overall=rs.getDouble("overall");
				this.difficulty=rs.getDouble("overall");
				this.clarity=rs.getDouble("overall");
				this.workload=rs.getDouble("workload");	
				this.Content=rs.getString("content");
				this.studentId=rs.getInt("studentID");
				this.courseId=rs.getInt("courseID");
			}
			this.student=new Student(this.studentId);
			this.Course=new course(this.courseId);
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
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM Review "
					+ "WHERE reviewID="+Double.toString(reviewId)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				this.overall=rs.getDouble("overall");
				this.difficulty=rs.getDouble("overall");
				this.clarity=rs.getDouble("overall");
				this.workload=rs.getDouble("workload");	
				this.Content=rs.getString("content");
				this.studentId=rs.getInt("studentID");
				this.courseId=rs.getInt("courseID");
			}
			this.student=new Student(this.studentId);
			this.Course=new course(this.courseId);
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
	
	
	public static void main(String[] args) {
		Review r=new Review(1);
		r.get_course().init_reviews();
		System.out.println(r.get_course().get_reviews().get(0).get_course().get_professor().get_lname());
	}
}
