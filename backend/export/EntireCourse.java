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
import java.util.*;

public class EntireCourse {
	private String prefix;
	private int courseNum;
	private HashMap<Integer, CourseProfessor> courses=new HashMap<Integer, CourseProfessor>();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	
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
	
	public double get_clarity() {
		double total=0;
		double count=0;
		Iterator hmiterator=courses.entrySet().iterator();
		
		while(hmiterator.hasNext()){
			Map.Entry mapElement = (Map.Entry)hmiterator.next();
			CourseProfessor cp=(CourseProfessor)mapElement.getValue();
			total+=cp.get_clarity();
			count++;
		}
		
		return total/count;
	}
	
	public double get_difficulty() {
		double total=0;
		double count=0;
		Iterator hmiterator=courses.entrySet().iterator();
		
		while(hmiterator.hasNext()){
			Map.Entry mapElement = (Map.Entry)hmiterator.next();
			CourseProfessor cp=(CourseProfessor)mapElement.getValue();
			total+=cp.get_difficulty();
			count++;
		}
		
		return total/count;
	}
	
	public double get_workload() {
		double total=0;
		double count=0;
		Iterator hmiterator=courses.entrySet().iterator();
		
		while(hmiterator.hasNext()){
			Map.Entry mapElement = (Map.Entry)hmiterator.next();
			CourseProfessor cp=(CourseProfessor)mapElement.getValue();
			total+=cp.get_workload();
			count++;
		}
		
		return total/count;
	}
	
	public String get_prefix() {
		return this.prefix;
	}
	
	public int get_courseNum() {
		return this.courseNum;
	}
	public HashMap<Integer, CourseProfessor> get_CP(){
		return this.courses;
	}
	
	private void parse(String search) {
		prefix="";
		int i;
		for (i=0;i<search.length();i++) {
			if(Character.isLetter(search.charAt(i))) {
				prefix+=search.charAt(i);
			}
			else {
				break;
			}
		}
		prefix=prefix.toLowerCase();
		
		if(!(i==search.length())) {
			try {
				this.courseNum=Integer.parseInt(search.substring(i));
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(courseNum);
	}
	
	EntireCourse(String search){
		parse(search);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM course "
					+ "WHERE prefix='"+prefix+
					"' and courseNum="+Double.toString(courseNum)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				Integer key=(Integer)rs.getInt("professorID");
				if(!courses.containsKey(key)) {
					courses.put(key, new CourseProfessor(prefix, courseNum, key));		
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
