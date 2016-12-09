package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import com.ers.jndi.SQL;
import com.ers.jndi.ServiceLocator;
import com.revature.beans.ERSReimbursement;
import com.revature.beans.ERSReimbursementStatus;
import com.revature.beans.ErsReimbursementType;
import com.revature.beans.ErsRoles;
import com.revature.beans.ErsUser;


public class ERSReimbursementDAOImpl{
	private Connection conn;

	public ERSReimbursementDAOImpl() throws Exception{
		conn = ServiceLocator.getersDatabase().getConnection();	
	}

	public void close() throws SQLException {
		conn.close();
	}
	
	
	public List<ERSReimbursement> getReimForResolver() throws SQLException, NamingException {
		List<ERSReimbursement> results = new ArrayList<ERSReimbursement>();
		String sql = new SQL().reimbSQL();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapRowsForResolver(rs,results);
		close();
		return results;	
	}
	
	private void mapRowsForResolver(ResultSet rs, List<ERSReimbursement> results) throws SQLException {
		while(rs.next()){
				ErsRoles authorRole = new ErsRoles(rs.getInt("userroleID"), rs.getString("userRole"));
				ErsRoles resolverRole = new ErsRoles(rs.getInt("resolverroleID"), rs.getString("resolverRole"));
				ErsUser author = new ErsUser(rs.getInt("AuthorID"), rs.getString("AuthorUn"), rs.getString("AuthoerPw"), rs.getString("AuthorFn"), rs.getString("AuthorLn"), rs.getString("AuthorEm"), authorRole);
				ErsUser resolver = new ErsUser(rs.getInt("ResolverID"), rs.getString("ResolverUn"), rs.getString("ResolverPw"), rs.getString("ResolverFn"), rs.getString("ResolverLn"), rs.getString("ResolverEm"), resolverRole);
				ERSReimbursementStatus authorStatus = new ERSReimbursementStatus(rs.getInt("statusID"), rs.getString("status"));	
				ErsReimbursementType type = new ErsReimbursementType(rs.getInt("typeID"), rs.getString("type"));
		
				ERSReimbursement obj = new ERSReimbursement(rs.getInt("REIMB_ID"), 
													rs.getInt("REIMB_AMOUNT"), 
													rs.getDate("REIMB_SUBMITTED"), 
													rs.getDate("REIMB_RESOLVED"), 
													rs.getString("REIMB_DESCRIPTIONS"), 
													author, resolver, authorStatus, type);
				results.add(obj);
			
			}
	}

	private void mapRowsForUser(ResultSet rs, List<ERSReimbursement> results, ErsUser user) throws SQLException {
		while(rs.next()){
				int userValue = rs.getInt("AuthorID");
				if(user.getId()== userValue){
					ErsRoles authorRole = new ErsRoles(rs.getInt("userroleID"), rs.getString("userRole"));
					ErsRoles resolverRole = new ErsRoles(rs.getInt("resolverroleID"), rs.getString("resolverRole"));
					ErsUser author = new ErsUser(rs.getInt("AuthorID"), rs.getString("AuthorUn"), rs.getString("AuthoerPw"), rs.getString("AuthorFn"), rs.getString("AuthorLn"), rs.getString("AuthorEm"), authorRole);
					ErsUser resolver = new ErsUser(rs.getInt("ResolverID"), rs.getString("ResolverUn"), rs.getString("ResolverPw"), rs.getString("ResolverFn"), rs.getString("ResolverLn"), rs.getString("ResolverEm"), resolverRole);
					ERSReimbursementStatus authorStatus = new ERSReimbursementStatus(rs.getInt("statusID"), rs.getString("status"));	
					ErsReimbursementType type = new ErsReimbursementType(rs.getInt("typeID"), rs.getString("type"));
			
					ERSReimbursement obj = new ERSReimbursement(rs.getInt("REIMB_ID"), 
														rs.getInt("REIMB_AMOUNT"), 
														rs.getDate("REIMB_SUBMITTED"), 
														rs.getDate("REIMB_RESOLVED"), 
														rs.getString("REIMB_DESCRIPTIONS"), 
														author, resolver, authorStatus, type);
					results.add(obj);
				}
			}
	}
	
	
	public List<ERSReimbursement> getReimForAuthor(ErsUser user) throws SQLException, NamingException {
		List<ERSReimbursement> results = new ArrayList<ERSReimbursement>();
		String sql = new SQL().reimbSQL();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapRowsForUser(rs,results,user);
		close();
		return results;	
	}


	public void insertRequest(ErsUser user, double amount,String desc,int type) throws SQLException {
		String sql = "insert into ERS_REIMBURSEMENT(REIMB_ID,REIMB_AMOUNT,REIMB_SUBMITTED,REIMB_DESCRIPTIONS,REIMB_AUTHOR,REIMB_STATUS_ID,REIMB_TYPE_ID) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 99);//99 for test case , have to create auto increment 
		stmt.setDouble(2, amount);
		stmt.setDate(3, (java.sql.Date) new Date());
		stmt.setString(4, desc);
		stmt.setInt(5, user.getId());
		stmt.setInt(6, 1);//status id is 1 , for pending
		stmt.setInt(7,type);
		stmt.executeUpdate();
		close();
	}
	
	/*
	public void changeReimStatus(int statusNumber,int idofUser) throws SQLException{
		String sql = "update ERS_REIMBURSEMENT set REIMB_SATUS_ID=? WHERE REIMB_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, statusNumber);
		stmt.setInt(2, idofUser);
		stmt.executeUpdate();
		close();
	}*/




	
	


}