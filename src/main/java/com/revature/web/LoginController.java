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
/**
 * Login Controller: Gets the username and password, goes to Business Delegate
 * and authenticates user , once user is returned , session is opened and user is
 * logged in.
 * @author Shehar
 *
 */
public class LoginController{

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		BuisnessDelegate delegate = new BuisnessDelegate();
		ErsUser user;
		HttpSession session = request.getSession();
		
		try {
			if(request.getSession().getAttribute("userSession")==null){
				user = delegate.login(username, password);
				if(user==null){
					request.setAttribute("error","<div class=\"alert alert-danger\" "
							+ "role=\"alert\"><strong>Wrong Username and Password</strong> Enter a valid "
							+ "Username and Password</div>");
				}
			}else{
				user = (ErsUser) session.getAttribute("userSession");
			}
			 
			if(user!=null && user.getRoleid().getId()>1){
				List<ERSReimbursement> reimb = new Facade().getReimForUser(user);
				request.setAttribute("reimb", reimb);
				request.getSession().setAttribute("userSession", user);
				request.getRequestDispatcher("user.jsp").forward(request, response); 
				
			}else if(user!=null && user.getRoleid().getId()==1){
				List<ERSReimbursement> reimb = new Facade().getReimForResolver();
				request.setAttribute("resolverList", reimb);
				request.getSession().setAttribute("userSession", user);
				request.getRequestDispatcher("manager.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	
}
