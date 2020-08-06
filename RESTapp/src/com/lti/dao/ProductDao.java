package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lti.entity.Product;

//classes which contain DB code are commonly
// referred to as Data Access Objects

/* Derby  
 * https://docs.oracle.com/cd/E19501-01/819-3659/gcmaz/
 * CREATE TABLE tb1_product(
	id INT,
	name VARCHAR(20),
	price FLOAT);
INSERT INTO tb1_product VALUES(111, 'iPhone 11', 75000);
INSERT INTO tb1_product VALUES(222, 'Samsung S20', 85000);
INSERT INTO tb1_product VALUES(333, 'Moto Razr', 12500);
*/

public class ProductDao {

	public Product select(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			String url = "jdbc:derby://localhost:1527/servletDB";
			String user = "batman";
			String pass = "batman";
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection Successfull...");
			String sql ="SELECT * FROM tb1_product WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			// this 1 is order of question mark in sql string
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			// rs currently points to the header
			// rs.next() moves the cursor to 1st row
			// if used below because only 1 row needs to be returned
			// use while for returning multiple rows
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				return product;
			}
			return null; //very bad, should throw and exception indicating that no product found
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace(); //detailed report of exception
			// we should rather throw user defined exception
			return null;
		}
		finally { try {conn.close(); }catch(Exception e) {	}}
	}
	
	public List<Product> selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			String url = "jdbc:derby://localhost:1527/servletDB";
			String user = "batman";
			String pass = "batman";
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection Successfull...");
			String sql ="SELECT * FROM tb1_product";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Product> products = new ArrayList<Product>();
			Product product;
			while(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				products.add(product);
			}
			return products;
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally { try {conn.close(); }catch(Exception e) {	}}
	}
	
	public void insert(Product product) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			String url = "jdbc:derby://localhost:1527/servletDB";
			String user = "batman";
			String pass = "batman";
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Data inserted successfully...");
			String sql ="INSERT INTO tb1_product VALUES(?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			// this 1 is order of question mark in sql string
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setFloat(3, product.getPrice());
			int count = stmt.executeUpdate();
			//checking count is important in case of update/delete statements
			//eg. if(count==0), return 0 rows got updated/deleted
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally { try {conn.close(); }catch(Exception e) {	}}
	}
	
}
