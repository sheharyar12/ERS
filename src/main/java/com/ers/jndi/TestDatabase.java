package com.ers.jndi;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;
import com.revature.data.ERSReimbursementDAOImpl;
import com.revature.data.Facade;
import com.revature.middle.BuisnessDelegate;
/**
 * testdatabase class
 * @author Shehar
 *
 */
public class TestDatabase {
	public static void main(String[] args) throws Exception {

		//Testing user and its reimbursements
		Facade data = new Facade();
		ErsUser user = data.createUser("creed2");
		System.out.println(user.toString());

		//resolver reimbursement list
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

		//test filter
		List<ERSReimbursement> resolverList2 = data.getReimForResolver();
		System.out.println("\nStatus Pending");
		resolverList2 = new BuisnessDelegate().filter(resolverList2, 1);
		for(ERSReimbursement rim : resolverList2){
			System.out.println(rim);
		}
		
		int length = new ERSReimbursementDAOImpl().getReimForResolver().size();
		System.out.println(length);
		

		// Check that an unencrypted password matches one that has
		// previously been hashed
		if (BCrypt.checkpw("test", "$2a$10$NUYintsu/8ZZPYi3otAEPOGc6j4qse8cWm0FP2mLijltmnv0c8PZW"))
			System.out.println("It matches");
		else
			System.out.println("It does not match");
		
		
	}

}
