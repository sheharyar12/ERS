package com.ers.jndi;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;



public class Facade {

	
	
	public ErsUser createUser(String username, String password) throws SQLException, NamingException{
		return new ErsUsersDAOImpl().lookForUser(username, password);
	}
	public List<ERSReimbursement> getReimForUser(ErsUser user) throws Exception{
		ERSReimbursementDAOImpl dao = new ERSReimbursementDAOImpl();
		List<ERSReimbursement> list = dao.getReimForUser(user);
		return list;
	}
	
	//create reimb for manager
	

	
	
	
	
	
}
