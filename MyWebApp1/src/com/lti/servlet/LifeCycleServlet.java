package com.lti.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */

/* This program purpose
	servuice methods - init, destroy
 	server is loaded only once in the memory i.e. initialised only once.
 	If we refresh the browser, init will not be called again n again.
 	destroy will be called if we redeploy the application or shut down the server.
*/

@WebServlet("/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("init called...");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet called...");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("service called...");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	@Override
	public void destroy() {
		System.out.println("destroy called...");
	}

}
