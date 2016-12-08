package com.ers.jndi;

import java.util.ArrayList;
import java.util.List;






public class TestDatabase {

	public static void main(String[] args) throws Exception {


		//Testing user and its reimbursements
		Facade data = new Facade();
		ErsUser user = data.createUser("creed2", "8oqqKp7zW");
		System.out.println(user.toString());
		
		
		
		List<ERSReimbursement> list = data.getReimForUser(user);
		System.out.println("Reimbursements");
		for(ERSReimbursement rim : list){
			System.out.println("ROLE: " + rim.getAuthor().getRoleid().getRole() + " ::::user Reim:  " + rim);
		}
		
		
		
		
		/*
		//testing filter
		System.out.println("\nStatus Pending");
		list = data.filterData("Pending");
		for(ERSReimbursement rim : list){
			System.out.println(rim);
		}
		*/
		
		
		
		
	}

}
