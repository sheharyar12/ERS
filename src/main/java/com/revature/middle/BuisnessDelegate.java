package com.revature.middle;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;

public class BuisnessDelegate {

	public ErsUser login(String username,String password) throws SQLException, NamingException{
		return new Service().authUser(username, password);
	}
	
	public List<ERSReimbursement> filter(List<ERSReimbursement> reimb, int statusType) throws Exception{
		return new FilterService().filter(reimb,statusType);
	}
	
	public void addReimb(ErsUser user, double amount,String desc,int type) throws Exception{
		new insertService().addReimb(user, amount, desc, type);
	}
	
}
