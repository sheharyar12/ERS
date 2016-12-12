package com.ers.jndi;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;
import com.revature.data.Facade;
import com.revature.middle.BuisnessDelegate;

public class TestDatabase {

	public static void main(String[] args) throws Exception {

		//Testing user and its reimbursements
		Facade data = new Facade();
		ErsUser user = data.createUser("creed2", "8oqqKp7zW");
		System.out.println(user.toString());
		
		
		
		/*
		List<ERSReimbursement> list = data.getReimForUser(user);
		System.out.println("Reimbursements");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).userToString());
		}
		*/
		
		System.out.println("\nResolver Reimb List");
		List<ERSReimbursement> resolverList = data.getReimForResolver();
		for(int i=0;i<resolverList.size();i++){
			System.out.println(resolverList.get(i).managerToString());
		}
		
		//filter by status 
		List<ERSReimbursement> status = data.filterByStatus(resolverList, 2);
		System.out.println("\n Filter Status Reimbursment");
		for(int i=0;i<status.size();i++){
			System.out.println(status.get(i).managerToString());
		}
		
		
		//filter by type
		resolverList = data.filterByType(resolverList, 2);
		System.out.println("\n Filter Type Reimbursment");
		for(int i=0;i<resolverList.size();i++){
			System.out.println(resolverList.get(i).managerToString());
		}
		
		//change status 
		data.changeReimStatus(1, 8);
		
		
		//test filter
		List<ERSReimbursement> resolverList2 = data.getReimForResolver();
		System.out.println("\nStatus Pending");
		resolverList2 = new BuisnessDelegate().filter(resolverList2, 1);
		for(ERSReimbursement rim : resolverList2){
			System.out.println(rim);
		}
		
		
		
		
		
	}

}
