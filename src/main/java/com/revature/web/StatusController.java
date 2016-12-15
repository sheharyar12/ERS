package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.data.Facade;

public class StatusController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		 String[] status = request.getParameterValues("status");
		 String[] eID = request.getParameterValues("userid");
        for(int i =0;i<status.length;i++){
    			try {
    				if(status[i].equals("Pending")){
    					new Facade().changeReimStatus(1, Integer.parseInt(eID[i]));
    				}else if(status[i].equals("APPROVED")){

    					new Facade().changeReimStatus(2, Integer.parseInt(eID[i]));
    				}
    				else if(status[i].equals("DENIED")){
    					new Facade().changeReimStatus(3, Integer.parseInt(eID[i]));
    				}
    				
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
       //request.getRequestDispatcher("manager.jsp").forward(request, response);*/
	}
}
