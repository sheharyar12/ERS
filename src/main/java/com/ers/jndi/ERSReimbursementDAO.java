package com.ers.jndi;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

public interface ERSReimbursementDAO {
	

	public void close() throws SQLException;

	public List<ERSReimbursement> getReimData() throws SQLException, NamingException;

	public void insertRequest(ERSReimbursement request) throws SQLException;
	
	public void changeReimStatus(int statusNumber,int idofUser) throws SQLException;

	List<ERSReimbursement> filterReimStatusData(String filterValue) throws SQLException, NamingException;
}
