package com.lti.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculator.lti")
public class CalculatorServlet extends HttpServlet {
	
	// doGet method because default method="get" in HTML file
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// reading user input
		int num1 = Integer.parseInt(request.getParameter("no1"));
		int num2 = Integer.parseInt(request.getParameter("no2"));
		
		// calculating result
		int result = num1 + num2;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><body>");
		out.write("<h2>Result is " + result + "</h2>");
		out.write("</html></body>");
	}

}
