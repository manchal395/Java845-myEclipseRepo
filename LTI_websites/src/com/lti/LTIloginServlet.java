package com.lti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Servlet implementation class LTIloginServlet
 */
@WebServlet("/LTIloginServlet")
public class LTIloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String psw = request.getParameter("psw");
		
		LTIdbConnect dbConnect = new LTIdbConnect();
		if( dbConnect.authenticate(uname, psw) ) {
//			Alert alert = new Alert(AlertType.INFORMATION, "Login Successful!");
//			alert.show();
			response.sendRedirect("index.html");
		}
		else {
//			Alert alert = new Alert(AlertType.INFORMATION, "Invalid Login Credentials, Try again!");
//			alert.show();
			response.sendRedirect("login.html");
		}
		
	}

}
