package com.revature.data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ers.jndi.ServiceLocator;
/**
 * Title: StatusDAOImp
 * @author Shehar
 *
 */
public class ERSReimbursementStatusDAOImp{
	private Connection conn;
	
	
	
	/**
	 * connection for the database
	 */
	public ERSReimbursementStatusDAOImp() throws Exception{
		conn = ServiceLocator.getersDatabase().getConnection();	
	}
	public void close() throws SQLException {
		conn.close();
	}
	
	
	
	/**
	 * get status of a reimbursement 
	 */
	public String getStatus(int id) throws SQLException{
		String sql = "select REIMB_STATUS from ERS_REIMBURSEMENT_STATUS where REIMB_STATUS_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		String status = null;
		if (rs.next()) {
			  status = rs.getString("REIMB_STATUS"); 
		}
		close();
		return status;
	}
	
	
	
}
