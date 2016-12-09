package com.revature.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.ers.jndi.ServiceLocator;

public class ErsReimbursementTypeImpl{

	private Connection conn;
	
	
	public void close() throws SQLException {
		conn.close();
	}
	
	public ErsReimbursementTypeImpl() throws SQLException, NamingException {
		conn = ServiceLocator.getersDatabase().getConnection();	
	}
	
	public String getType(int id) throws SQLException{
		String sql = "select REIMB_TYPE from ERS_REIMBURSEMENT_TYPE where REIMB_TYPE_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		String type = null;
		if (rs.next()) {
			  type = rs.getString("REIMB_TYPE"); 
		}
		return type;
	}

	
	
}
