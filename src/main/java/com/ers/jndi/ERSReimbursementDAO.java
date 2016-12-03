package com.ers.jndi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public class ERSReimbursementDAO{

	private Connection conn;
	
	public ERSReimbursementDAO() throws Exception{
		conn = ServiceLocator.getersDatabase().getConnection();	
	}

	public void close() throws SQLException {
		conn.close();
	}

	public void getData() throws SQLException, NamingException {
		String sql = "select * from ERS_REIMBURSEMENT";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			System.out.println(rs.getString("REIMB_ID") + " " +
							   rs.getString("REIMB_AMOUNT") + " " +
							   rs.getString("REIMB_SUBMITTED") + " " +
							   rs.getString("REIMB_RESOLVED") + " " +
							   rs.getString("REIMB_DESCRIPTIONS") + " " +
							   rs.getString("REIMB_AUTHOR") + " " +
							   rs.getString("REIMB_RESOLVER") + " " +
							   rs.getString("REIMB_STATUS_ID") + " " +
							   rs.getString("REIMB_TYPE_ID"));
		}
		close();	
	}
}
