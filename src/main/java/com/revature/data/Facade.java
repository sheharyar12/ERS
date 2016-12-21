package com.revature.data;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.revature.beans.ERSReimbursement;
import com.revature.beans.ErsUser;
/**
 * 
 * @author Shehar
 *
 */
public class Facade {

	
	
	public ErsUser createUser(String username) throws SQLException, NamingException{
		return new ErsUsersDAOImpl().lookForUser(username);
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
	
	
	public void changeReimStatus(int statusNumber,int rid , int eID, int resolverID) throws Exception{
		ERSReimbursementDAOImpl dao = new ERSReimbursementDAOImpl();
		dao.changeReimStatus(statusNumber, rid , eID, resolverID);
	}

	public void addReim(ErsUser user, double amount,String desc,int type) throws Exception{
		ERSReimbursementDAOImpl dao = new ERSReimbursementDAOImpl();
		dao.insertRequest(user, amount, desc, type);
	}

	
	
	
	
	
}
