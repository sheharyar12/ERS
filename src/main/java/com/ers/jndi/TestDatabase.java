package com.ers.jndi;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;
import com.revature.data.Facade;

public class TestDatabase {

	public static void main(String[] args) throws Exception {


		//Testing user and its reimbursements
		Facade data = new Facade();
		ErsUser user = data.createUser("creed2", "8oqqKp7zW");
		System.out.println(user.toString());
		
		
		
		List<ERSReimbursement> list = data.getReimForUser(user);
		System.out.println("Reimbursements");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).userToString());
		}
		
		System.out.println("\nReolver Reimb List");
		List<ERSReimbursement> resolverList = data.getReimForResolver();
		for(int i=0;i<resolverList.size();i++){
			System.out.println(resolverList.get(i).managerToString());
		}
		
		user = data.createUser("admin", "admin");
		
		System.out.println(user.getRoleid());
		
		
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
