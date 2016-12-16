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

public class StatusController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		 String[] status = request.getParameterValues("status");
		 String[] rID = request.getParameterValues("rid");
		 String[] eID = request.getParameterValues("userid");
		 
		 
		 System.out.println("Button was clicked");
		 
		 for(int i=0;i<status.length;i++){
			 System.out.println("REIMB_ID: " + rID[i] + "    UserID: " + eID[i] + "   STATUS: " + status[i]);
		 }
		 
		 
		 System.out.println("Length of Status " + status.length);
		 System.out.println("Length of rID " + eID.length);
		 HttpSession session = request.getSession();
		 ErsUser user = (ErsUser) session.getAttribute("userSession");
		 
        for(int i =0;i<status.length;i++){
    			try {
    				if(status[i].equals("APPROVED")){
    					System.out.println("TRUE " + eID[i]);
    					new Facade().changeReimStatus(2, Integer.parseInt(rID[i]), Integer.parseInt(eID[i]),user.getId());
    				}
    				else if(status[i].equals("DENIED")){
    					new Facade().changeReimStatus(3, Integer.parseInt(rID[i]), Integer.parseInt(eID[i]),user.getId());
    				}
    				
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
		List<ERSReimbursement> reimb = null;
		try {
			reimb = new Facade().getReimForResolver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("resolverList", reimb);
        request.getRequestDispatcher("manager.jsp").forward(request, response);
	}
}
