package com.lti.servlet;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet implementation class LoginServlet

// connect 'jdbc:derby://localhost:1527/servletDB;create=true;user=batman;password=batman';

/* TO-DO
	write a js function that will be called on onload event of the page.
	Inside the function, write the code for reading the cookies we have created.
	if cookies are present, then redirect to welcome page or else let the user continue with login page.
*/

/* Checkbox - Remember Me - Cookies. (or Local Storage or Sessions)
	If checkbox is unchecked, it will be returned as null and not false.
		i.e. the checkbox data will not be sent to the server.
	If checked, value sent to server is 'checkbox_id=on' by default.
		If value property mentioned in checkbox input tag eg. value="yes";
		Then value sent to server is 'checkbox_id=yes'
	
	Using post method, we can send unlimited data.
	URL string has max length approx 2000 characters. So if we use GET method, we are limited.
*/

@WebServlet("/login.lti")
public class LoginServlet extends HttpServlet {
	
	// doPost method because method="post" in the HTML file
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// reading user input
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		//InMemoryLoginService loginService = new InMemoryLoginService();
		DatabaseLoginService loginService = new DatabaseLoginService();
		
		boolean isValid = loginService.authenticate(uname, pwd);
		if(isValid) {
			String rememberMe = request.getParameter("reme");
			if(rememberMe != null && rememberMe.equals("yes")) {
				Cookie c1 = new Cookie("uname", uname);
				c1.setMaxAge(60 * 60); // 1 hr
				String encodedPwd = Base64.getEncoder().encodeToString(
			            pwd.getBytes());
				Cookie c2 = new Cookie("pass", encodedPwd);
				// right now its giving same encoded string evry time
				c2.setMaxAge(60 * 60);
				response.addCookie(c1);
				response.addCookie(c2);	
				// view this cookie data in broswer->inspect->Application->Cookies
			}
			response.sendRedirect("Welcome.html");
		}
		else
			response.sendRedirect("Login.html");
	}

}

