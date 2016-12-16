package com.revature.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.ERSReimbursement;
import com.revature.data.Facade;
import com.revature.middle.BuisnessDelegate;

public class FilterController{

	
	public void Filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuisnessDelegate delegate = new BuisnessDelegate();
		
		try {
			List<ERSReimbursement> reimb = new Facade().getReimForResolver();
			if(request.getParameter("pending")!=null){
				reimb = delegate.filter(reimb, 1);
				request.setAttribute("resolverList", reimb);
				request.getRequestDispatcher("manager.jsp").forward(request, response);
			}else if(request.getParameter("approved")!=null){
				reimb = delegate.filter(reimb, 2);
				request.setAttribute("resolverList", reimb);
				request.getRequestDispatcher("manager.jsp").forward(request, response);
			}else if(request.getParameter("all")!=null){
				reimb = new Facade().getReimForResolver();
				request.setAttribute("resolverList", reimb);
				request.getRequestDispatcher("manager.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
}
