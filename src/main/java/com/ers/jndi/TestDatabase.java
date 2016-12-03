package com.ers.jndi;

import java.util.ArrayList;
import java.util.List;


public class TestDatabase {

	public static void main(String[] args) throws Exception {

		//test
		ERSReimbursementDAO ers = new ERSReimbursementDAOImpl();
		
		List<ERSReimbursement> list = ers.getReimData();
		

		for(ERSReimbursement e: list){
			System.out.println(e);
			ers.close();
		}
		
	}

}
