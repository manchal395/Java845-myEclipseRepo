package com.lti;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LTIdbConnect {
	
	private static Connection conn = null;
	private static Statement st = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

//	public static void main(String[] args) {
//		try {
//			Class.forName("org.apache.derby.jdbc.ClientDriver");
//			String url = "jdbc:derby://localhost:1527/servletDB";
//			String user = "batman";
//			String pass = "batman";
//			
//			conn = DriverManager.getConnection(url, user, pass);
//			System.out.println("Connection Successfull...");
//			
//			DatabaseMetaData databaseMetaData = conn.getMetaData();
//
//	        rs = databaseMetaData.getTables(null, null, "LOGINCRED", null);
//	        if (!rs.next()) {
//	            createTable();
//	        }
//			
//		}
//		catch(ClassNotFoundException | SQLException e) {
//			e.printStackTrace(); //detailed report of exception
//		}
//		finally {
//			try { conn.close(); }
//			catch(Exception e) {}
//		}
//	}
	
	public boolean authenticate(String uname, String pwd) {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			String url = "jdbc:derby://localhost:1527/servletDB";
			String user = "batman";
			String pass = "batman";
			
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection Successfull...");
			
			DatabaseMetaData databaseMetaData = conn.getMetaData();

	        rs = databaseMetaData.getTables(null, null, "LOGINCRED", null);
	        if (!rs.next()) {
	            createTable();
	        }
			String sql ="SELECT COUNT(user_id) FROM logincred WHERE user_name = ? AND user_pass = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uname);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				if(count == 1)
					return true;
			}
			return false;
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace(); //detailed report of exception
			return false;
		}
		finally {
			try { conn.close(); }
			catch(Exception e) {}
		}
	}
	
	private static void createTable() {
		try {
			st = conn.createStatement();
			String createTableSQL = "CREATE TABLE logincred ( "
					+ "user_id INT, user_name VARCHAR(20) UNIQUE, user_pass VARCHAR(8), "
					+ "user_email VARCHAR(30) UNIQUE, last_logged_in TIMESTAMP "
					+ ")";
			st.execute(createTableSQL);
			
			pst = conn.prepareStatement("INSERT INTO logincred VALUES(?,?,?,?,?)");
			pst.setInt(1, 1);
			pst.setString(2, "m_anchal");
			pst.setString(3, "imbatman");
			pst.setString(4, "anchal@lti");
			pst.setString(5, null);
			pst.executeUpdate();
			
			st.execute("INSERT INTO logincred VALUES(2,'batman','dark123','batman@lti',null)");
			st.execute("INSERT INTO logincred VALUES(3,'joker','laugh123','joker@lti',null)");
			st.execute("INSERT INTO logincred VALUES(4,'bond','bond007','jamesbond@lti',null)");
			
			//st.execute("COMMIT");
			//conn.setAutoCommit(false);
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace(); //detailed report of exception
		}
		finally {
			try { conn.close(); }
			catch(Exception e) {}
		}
	}
}
