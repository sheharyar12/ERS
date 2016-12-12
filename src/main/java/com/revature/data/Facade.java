package com.revature.data;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;



public class Facade {

	
	
	public ErsUser createUser(String username, String password) throws SQLException, NamingException{
		return new ErsUsersDAOImpl().lookForUser(username, password);
	}
	public List<ERSReimbursement> getReimForUser(ErsUser user) throws Exception{
		ERSReimbursementDAOImpl dao = new ERSReimbursementDAOImpl();
		List<ERSReimbursement> list = dao.getReimForAuthor(user);
		return list;
	}
	
	public List<ERSReimbursement> getReimForResolver() throws Exception{
		ERSReimbursementDAOImpl dao = new ERSReimbursementDAOImpl();
		List<ERSReimbursement> list = dao.getReimForResolver();
		return list;
	}
	
	public List<ERSReimbursement> filterByStatus(List<ERSReimbursement> reimb, int statusType) throws Exception{
		return new ERSReimbursementDAOImpl().filterByStatus(reimb, statusType);
	}
	
	public List<ERSReimbursement> filterByType(List<ERSReimbursement> reimb, int filterType) throws Exception{
		return new ERSReimbursementDAOImpl().filterByType(reimb, filterType);
	}
	
	public void changeReimStatus(int statusNumber,int idofUser) throws Exception{
		ERSReimbursementDAOImpl dao = new ERSReimbursementDAOImpl();
		dao.changeReimStatus(statusNumber, idofUser);
	}


	
	
	
	
	
}
