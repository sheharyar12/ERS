package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		switch(requestURI){
			case"/ERS/loginServlet":{
				new LoginController().login(request, response);
				break;
			}
			case"/ERS/logoutServlet":{
				new LoginController().logout(request, response);
				break;
			}
			case"/ERS/filterServlet":{
				new FilterController().Filter(request, response);
				break;
			}
			case"/ERS/updateStatusServlet":{
				new StatusController().updateStatus(request, response);
				break;
			}
			case"/ERS/insertReim":{
				new insertController().addReim(request, response);
				break;
			}
			default:{
				response.setStatus(404);
				response.sendRedirect("oops.html");
			}
		}
	}


}
