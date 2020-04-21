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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Grades {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	private int gradeID;
	private int[] distribution=new int[15];
	
	public String toArray() {
		String returnval="[";
		for(int i=0;i<11;i++) {
			returnval=returnval+Integer.toString(distribution[i])+",";
		}
		returnval=returnval+Integer.toString(distribution[11])+']';
		return returnval;
	}
	public Grades(int ID) {
		this.gradeID=ID;
		conn=null;
		ps=null;
		rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String passwords = "root"; //enter the password for your MySQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/RateSC?serverTimezone=UTC&user=root&password="+passwords);
			ps = conn.prepareStatement("SELECT * FROM grades "
					+ "WHERE gradeID="+Integer.toString(gradeID)+";");
			rs = ps.executeQuery();
			while(rs.next()) {
				for(int i=1;i<=15;i++) {
					distribution[i-1]=rs.getInt(i);
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
