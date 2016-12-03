package com.ers.jndi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;


public class ERSReimbursementDAOImpl implements ERSReimbursementDAO{
	private Connection conn;

	public ERSReimbursementDAOImpl() throws Exception{
		conn = ServiceLocator.getersDatabase().getConnection();	
	}

	public void close() throws SQLException {
		conn.close();
	}
	
	private void mapRows(ResultSet rs, List<ERSReimbursement> results) throws SQLException {
		while(rs.next()){
			//get values from row
			int id = rs.getInt("REIMB_ID");
			int amount = rs.getInt("REIMB_AMOUNT");
			Date sub = rs.getDate("REIMB_SUBMITTED");
			Date resolved = rs.getDate("REIMB_RESOLVED");
			String desc = rs.getString("REIMB_DESCRIPTIONS");
			int authorID = rs.getInt("REIMB_AUTHOR");
			int resolverID = rs.getInt("REIMB_RESOLVER");
			int statusID = rs.getInt("REIMB_STATUS_ID");
			int type = rs.getInt("REIMB_TYPE_ID");
			//create genre object
			ERSReimbursement obj = new ERSReimbursement(id, amount, sub, resolved,desc,authorID,resolverID,statusID,type);
			// add object to list 
			results.add(obj);
		}
	}
	
	public List<ERSReimbursement> filterReimStatusData(String filterValue) throws SQLException, NamingException {
		String sql ="";
		if(filterValue.equals("Pending")){sql = "select * from ERS_REIMBURSEMENT where REIMB_STATUS_ID = 1";}
		else if(filterValue.equals("Approved")){sql = "select * from ERS_REIMBURSEMENT where REIMB_STATUS_ID = 2";}
		else if(filterValue.equals("Denied")){sql = "select * from ERS_REIMBURSEMENT where REIMB_STATUS_ID = 3";}	
		List<ERSReimbursement> results = new ArrayList<ERSReimbursement>();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapRows(rs,results);
		close();
		return results;	
	}
	
	@Override
	public List<ERSReimbursement> getReimData() throws SQLException, NamingException {
		List<ERSReimbursement> results = new ArrayList<ERSReimbursement>();
		String sql = "select * from ERS_REIMBURSEMENT";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapRows(rs,results);
		close();
		return results;	
	}

	@Override
	public void insertRequest(ERSReimbursement request) throws SQLException {
		String sql = "insert into ERS_REIMBURSEMENT values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, request.getId());
		stmt.setDouble(2, (int)request.getAmount());
		stmt.setDate(3, request.getSubmitted());
		stmt.setDate(4, request.getResolved());
		stmt.setString(5, request.getDescription());
		stmt.setInt(6, request.getAuthor());
		stmt.setInt(7,request.getResolver());
		stmt.setInt(8,request.getStatusID());
		stmt.setInt(9,request.getTypeID());
		stmt.executeUpdate();
		close();
	}
	
	@Override
	public void changeReimStatus(int statusNumber,int idofUser) throws SQLException{
		String sql = "update ERS_REIMBURSEMENT set REIMB_SATUS_ID=? WHERE REIMB_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, statusNumber);
		stmt.setInt(2, idofUser);
		stmt.executeUpdate();
		close();
	}
	
	


}
