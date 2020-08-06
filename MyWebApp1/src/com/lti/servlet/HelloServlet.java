package com.lti.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/firstServlet.lti") //URL or URI : unique identification of this servlet - Servlet Mapping
public class HelloServlet extends HttpServlet {
	//doGet() is an overridden method
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TWO objects are passed in this method as parameters :
		// 1. Request object, 2. Response object
		
		response.setContentType("text/html");
		// setContentType - Multipurpose Internet Mail Extensions (MIME)
		
		PrintWriter out = response.getWriter();
		
		out.write("<html><body>");
		out.write("<h1>Welcome to the World of Servlets</h1>");
		out.write("<h2> Aaj ki taarikh : " + LocalDate.now() + "</h2>");
		out.write("<h3>&#128591;</h3>");
		out.write("<h3>&#x0928;&#x092E;&#x0938;&#x094D;&#x0924;&#x0947</h3>");
		out.write("<h3>नमसते</h3>");
		out.write("</body></html>");
	}
}
