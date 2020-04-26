package csilfanu_CSCI201L_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class CourseInfo {
	private String prefix;
	private int courseNum;
	private long professorID;
	
	public CourseInfo(String prefix, int courseNum, long professorID) {
		this.prefix = prefix;
		this.courseNum = courseNum;
		this.professorID = professorID;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public int getCourseNum() {
		return this.courseNum;
	}
	
	public long getProfessorID() {
		return this.professorID;
	}
}

public class QueryDatabase {

	private static Connection conn = null;
	
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static String sqlServer = "jdbc:mysql://localhost";
	private static String sqlDatabase = "RateSC";
	private static String sqlUsername = "root";
	private static String sqlPassword = "clifford";
	private static String connectionURL = sqlServer + "/" + sqlDatabase;
	
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public static ArrayList<CourseInfo> queryDistinctCourses(String query) {
		ArrayList<CourseInfo> courses = new ArrayList<CourseInfo>();
		
		try {
			conn = DriverManager.getConnection(connectionURL, sqlUsername, sqlPassword);
			
			if (query != null && !query.isEmpty()) {
				// Just courseNum
				if (isNumeric(query)) {
					
				} else {
					String[] queries = query.split(" ");
					String coursePrefix = queries[0];
					
					String sqlQuery = "SELECT DISTINCT prefix, courseNum, professorID FROM Course WHERE prefix=\"" + coursePrefix.toUpperCase() + "\"";
					
					// Query is both prefix and courseNum
					if (queries.length != 1) {
						String courseNumber = queries[1];
						sqlQuery += (" AND courseNum=" + courseNumber);
					}
					
					ps = conn.prepareStatement(sqlQuery);
				}
			} else {
				ps = conn.prepareStatement("SELECT DISTINCT prefix, courseNum, professorID FROM Course");
			}
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String prefix = rs.getString("prefix");
				int courseNum = rs.getInt("courseNum");
				long professorID = rs.getLong("professorID");
				
				courses.add(new CourseInfo(prefix, courseNum, professorID));
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
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
		
		return courses;
	}
	
	public static void main(String[] args) {
		ArrayList<CourseInfo> result = queryDistinctCourses("CSCI");
		
		for (CourseInfo c : result) {
			System.out.println(c.getPrefix() + " | " + c.getCourseNum() + " | " + c.getProfessorID());
		}
	}
	
	
}
