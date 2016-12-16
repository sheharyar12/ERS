package com.revature.web;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;
import com.revature.data.Facade;
import com.revature.middle.BuisnessDelegate;
import com.revature.middle.Service;

public class LoginController{

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String lOut = request.getParameter("logout");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//Service auth = new Service();
		BuisnessDelegate delegate = new BuisnessDelegate();
		ErsUser user;
		
		HttpSession session = request.getSession();
		
		

		try {
			if(request.getSession().getAttribute("userSession")==null){
				user = delegate.login(username, password);
			}else{
				user = (ErsUser) session.getAttribute("userSession");
			}
			 
			if(user!=null && user.getRoleid().getId()>1){
				List<ERSReimbursement> reimb = new Facade().getReimForUser(user);
				request.setAttribute("reimb", reimb);
				request.getSession().setAttribute("userSession", user);
				request.getRequestDispatcher("user.jsp").forward(request, response); 
				//testsession
				
				//request.getSession().invalidate();//erase httpSession obj
				//request.getSession().setMaxInactiveInterval(10);
				
			}else if(user!=null && user.getRoleid().getId()==1){
				List<ERSReimbursement> reimb = new Facade().getReimForResolver();
				request.setAttribute("resolverList", reimb);
				request.getSession().setAttribute("userSession", user);
				request.getRequestDispatcher("manager.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}*/
	
}
