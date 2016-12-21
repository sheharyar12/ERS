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
/**
 * Title : DAOImp
 * Description: Access database and run query to return result for each bean case.
 * @author Shehar Yar
 *
 */
public class ERSReimbursementDAOImpl{
	private Connection conn;

	
	
	
	/**
	 * Connection
	 */
	public ERSReimbursementDAOImpl() throws Exception{
		conn = ServiceLocator.getersDatabase().getConnection();	
	}
	public void close() throws SQLException {
		conn.close();
	}	
	
	

	/**
	 * Filter Reimbursement by status and returns a list of all reimbursements according to the filter parameter
	 */
	public List<ERSReimbursement> filterByStatus(List<ERSReimbursement> reimb, int filterStatus) throws SQLException{
		List<ERSReimbursement> status = new ArrayList<ERSReimbursement>();
		for(int i=0;i<reimb.size();i++){
			if(reimb.get(i).getStatusID().getId()==filterStatus){
				status.add(reimb.get(i));
			}
		}
		close();
		return status;
	}
	
	
	
	/**
	 * Returns All the reimbursements for a manager (resolver)
	 */
	public List<ERSReimbursement> getReimForResolver() throws SQLException, NamingException {
		List<ERSReimbursement> results = new ArrayList<ERSReimbursement>();
		String sql = new SQL().reimbSQL();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapRowsForResolver(rs,results);
		close();
		return results;	
	}
	
	
	
	/**
	 * gets the rows from the result set and maps it into the result reimbursement list (FOR RESOLVER:MANAGER)
	 */
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
													rs.getTimestamp("REIMB_SUBMITTED"), 
													rs.getTimestamp("REIMB_RESOLVED"), 
													rs.getString("REIMB_DESCRIPTIONS"), 
													author, resolver, authorStatus, type);
				results.add(obj);
			
			}
	}

	
	
	/**
	 * gets the rows from the result set and maps it into the result reimbursement list (FOR REGULAR USER)
	 */
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
														rs.getTimestamp("REIMB_SUBMITTED"), 
														rs.getTimestamp("REIMB_RESOLVED"), 
														rs.getString("REIMB_DESCRIPTIONS"), 
														author, resolver, authorStatus, type);
					results.add(obj);
				}
			}
	}
	
	
	
	/**
	 * Gets reimbursement for a user (AUTHOR)
	 */
	public List<ERSReimbursement> getReimForAuthor(ErsUser user) throws SQLException, NamingException {
		List<ERSReimbursement> results = new ArrayList<ERSReimbursement>();
		String sql = new SQL().reimbSQL();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapRowsForUser(rs,results,user);
		close();
		return results;	
	}


	
	/**
	 * Adds a reimbursement to the database according to parameters.
	 */
	public void insertRequest(ErsUser user, double amount,String desc,int type) throws NamingException, Exception {
		String sql = "insert into ERS_REIMBURSEMENT(REIMB_ID,REIMB_AMOUNT,REIMB_SUBMITTED,REIMB_DESCRIPTIONS,REIMB_AUTHOR,REIMB_STATUS_ID,REIMB_TYPE_ID) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, new ERSReimbursementDAOImpl().getReimForResolver().size()+1);//99 for test case , have to create auto increment 
		stmt.setDouble(2, amount);
		stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
		stmt.setString(4, desc);
		stmt.setInt(5, user.getId());
		stmt.setInt(6, 1);//status id is 1 , for pending
		stmt.setInt(7,type);
		stmt.executeUpdate();
		close();
	}
	
	
	
	/**
	 * Changes the reimbursement status in the database of an author
	 */
	public void changeReimStatus(int statusNumber,int rid,int eid,int resolverID) throws SQLException{
		String sql = "update ERS_REIMBURSEMENT set REIMB_STATUS_ID=?,REIMB_RESOLVED=?,REIMB_RESOLVER=? WHERE REIMB_ID=? and REIMB_AUTHOR=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, statusNumber);
		stmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
		stmt.setInt(3, resolverID);
		stmt.setInt(4, rid);
		stmt.setInt(5, eid);
		stmt.executeUpdate();
		close();
	}




	
	


}
