package com.revature.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;
import com.revature.data.Facade;
import com.revature.middle.BuisnessDelegate;

public class insertController{


	public void addReim(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String amount = request.getParameter("amount");
		String desc = request.getParameter("desc");
		int typeNum=0;
		if(type.equals("LODGING")){typeNum=1;}
		if(type.equals("TRAVEL")){typeNum=2;}
		if(type.equals("FOOD")){typeNum=3;}
		if(type.equals("OTHER")){typeNum=4;}
		HttpSession session = request.getSession();
		ErsUser user = (ErsUser) session.getAttribute("userSession");

		try {//TO add try catch to new Buisness delegate 
			new BuisnessDelegate().addReimb(user, Double.parseDouble(amount), desc, typeNum);
			List<ERSReimbursement> reimb = new Facade().getReimForUser(user);
			request.setAttribute("reimb", reimb);
		} catch (Exception e) {
			request.setAttribute("numberError","<div class=\"alert alert-danger\" "
							+ "role=\"alert\"><strong>Invalid Input</strong> Enter a valid "
							+ "number</div>");
		}
		request.getRequestDispatcher("user.jsp").forward(request, response);
	}
}
