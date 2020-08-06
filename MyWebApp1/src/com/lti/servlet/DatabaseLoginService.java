package com.lti.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* table structure in derby
 CREATE TABLE tb1_users (
 	user_id INT,
 	user_name VARCHAR(20) UNIQUE,
 	user_pass VARCHAR(8),
 	user_email VARCHAR(30) UNIQUE,
 	last_logged_in TIMESTAMP
 );
 
 INSERT INTO tb1_users VALUES(1, 'anchal', '123', 'anchal@lti', null);
 INSERT INTO tb1_users VALUES(2, 'batman', '111', 'batman@dark', null);
 INSERT INTO tb1_users VALUES(3, 'bond', '007', 'james@bond', null);
 
 COMMIT;

*/

/* Driver jar file
 Before we run the DB version, we need to ensure that we have the Driver jar file ready.
 After locating the same(derbyclient.jar/ojbc6/7/8/.jar)
 Copy the same in the Project's WebContent/WEB-INF/lib folder (can paste directly in eclipse)
 Then only run the application.
*/

/* Common Errors and their Solution in Servlet-Database program
 1. ClassNotFound - driver jar file missing from Project's WebContent/WEB-INF/lib folder
 2. Redirecting to login page again instead of another :-
  	a. forgot 'commit' in db
  	b. wrong sql query string
 3. If getting error http status 404 or 405 (Get or Post Method Not Allowed) :-
 	Check if method mentioned in form tag of html file and the method defined in servlet java file is same or not.
 	method="get" OR no method mentioned : doGet() in servlet java file
 	method="post"						: doPost() in servlet java file
 3.	If errors in all files and server not starting, maybe the tomcat folder is corrupted.
 	Download tomcat zip again.
 5. kuch samajh ni aa rha :-
 	a. clean tomcat server build, restart server and run html file again
 	b. If a. doesn't work, close eclipse and laptop, and sleep for a while.
 		Then try again later or ask sir, the next day.
*/

public class DatabaseLoginService {
	
	public boolean authenticate(String uname, String pwd) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			String url = "jdbc:derby://localhost:1527/servletDB";
			String user = "batman";
			String pass = "batman";
			conn = DriverManager.getConnection(url, user, pass);
			//System.out.println("Connection Successfull...");
			String sql ="SELECT COUNT(user_id) FROM tb1_users WHERE user_name = ? AND user_pass = ?";
			stmt = conn.prepareStatement(sql);
			// this 1 and 2 is order of question marks in sql string
			stmt.setString(1, uname);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			// rs currently points to the header
			// rs.next() moves the cursor to 1st row
			if(rs.next()) {
				// here 1 means 1st select clause/column in sql string i.e. Count
				int count = rs.getInt(1);
				if(count == 1)
					return true;
			}
			return false;
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace(); //detailed report of exception
			// we should rather throw user defined exception
			return false;
		}
		finally {
			try {
				conn.close();
			}
			catch(Exception e) {
				
			}
		}
	}

}
