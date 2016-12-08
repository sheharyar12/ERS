package com.ers.jndi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;


public class ERSReimbursementDAOImpl{
	private Connection conn;

	public ERSReimbursementDAOImpl() throws Exception{
		conn = ServiceLocator.getersDatabase().getConnection();	
	}

	public void close() throws SQLException {
		conn.close();
	}
	
	
	
	
	/*
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
	}*/
	

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
	
	
	public List<ERSReimbursement> getReimForUser(ErsUser user) throws SQLException, NamingException {
		List<ERSReimbursement> results = new ArrayList<ERSReimbursement>();
		String sql = "select "
					+ "ERS_REIMBURSEMENT.REIMB_AMOUNT,"
					+ "ERS_REIMBURSEMENT.REIMB_DESCRIPTIONS,"
					+ "ERS_REIMBURSEMENT.REIMB_ID,"
					+ "ERS_REIMBURSEMENT.REIMB_RESOLVED,"
					+ "ERS_REIMBURSEMENT.REIMB_SUBMITTED,"
					
					+ "users.ERS_USERS_ID as AuthorID,"
					+ "users.ERS_USERNAME as AuthorUn,"
					+ "users.ERS_PASSWORD as AuthoerPw,"
					+ "users.USER_FIRST_NAME as AuthorFn,"
					+ "users.USER_LAST_NAME as AuthorLn,"
					+ "users.USER_EMAIL as AuthorEm,"
					+ "users.USER_ROLE_ID as AuthorRole,"
					
					+ "resolvers.ERS_USERS_ID as ResolverID,"
					+ "resolvers.ERS_USERNAME as ResolverUn,"
					+ "resolvers.ERS_PASSWORD as ResolverPw,"
					+ "resolvers.USER_FIRST_NAME as ResolverFn,"
					+ "resolvers.USER_LAST_NAME as ResolverLn,"
					+ "resolvers.USER_EMAIL as ResolverEm,"
					+ "resolvers.USER_ROLE_ID as ResolverRole,"
					
					+ "status.REIMB_STATUS_ID as statusID,"
					+ "status.REIMB_STATUS as status,"
					
					+ "type.REIMB_TYPE_ID as typeID,"
					+ "type.REIMB_TYPE as type,"
					
					+ "u.REIMB_USER_ROLES_ID as userroleID,"
					+ "u.REIMB_ROLES as userRole,"
					
					+ "r.REIMB_USER_ROLES_ID as resolverroleID,"
					+ "r.REIMB_ROLES as resolverRole "
					
					+ "from ERS_REIMBURSEMENT "
					+ "inner join ERS_USERS users "
					+ "on ERS_REIMBURSEMENT.REIMB_AUTHOR = users.ERS_USERS_ID "
					
					+ "inner join ERS_USERS resolvers "
					+ "on ERS_REIMBURSEMENT.REIMB_RESOLVER = resolvers.ERS_USERS_ID "
					
					+ "inner join ERS_REIMBURSEMENT_STATUS status "
					+ "on ERS_REIMBURSEMENT.REIMB_STATUS_ID = status.REIMB_STATUS_ID "
					
					+ "inner join ERS_REIMBURSEMENT_TYPE type "
					+ "on ERS_REIMBURSEMENT.REIMB_TYPE_ID = type.REIMB_TYPE_ID "
					
					+ "inner join ERS_USER_ROLES u "
					+ "on users.USER_ROLE_ID = u.REIMB_USER_ROLES_ID "
					
					+ "inner join ERS_USER_ROLES r "
					+ "on resolvers.USER_ROLE_ID = r.REIMB_USER_ROLES_ID";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapRowsForUser(rs,results,user);
		close();
		return results;	
	}


	public void insertRequest(ErsUser user, double amount,String desc,int type) throws SQLException {
		String sql = "insert into ERS_REIMBURSEMENT(REIMB_ID,REIMB_AMOUNT,REIMB_SUBMITTED,REIMB_DESCRIPTIONS,REIMB_AUTHOR,REIMB_STATUS_ID,REIMB_TYPE_ID) values(?,?,?,?,?,?)";
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
