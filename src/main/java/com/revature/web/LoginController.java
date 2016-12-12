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

public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//Service auth = new Service();
		BuisnessDelegate delegate = new BuisnessDelegate();
		ErsUser user;
		HttpSession session = null; 
		try {
			user = delegate.login(username, password);
			 
			if(user!=null && user.getRoleid().getId()>1){
				List<ERSReimbursement> reimb = new Facade().getReimForUser(user);
				request.setAttribute("reimb", reimb);
				//request.getSession().setAttribute("users", user);
				request.getRequestDispatcher("user.jsp").forward(request, response);
				session = request.getSession(); 
				
				
				
				
			}else if(user!=null && user.getRoleid().getId()==1){
				List<ERSReimbursement> reimb = new Facade().getReimForResolver();
				request.setAttribute("resolverList", reimb);
				request.getRequestDispatcher("manager.jsp").forward(request, response);
				session = request.getSession();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("username",username);
		session.setAttribute("password",username);  
		
		
	}
	
	
}
