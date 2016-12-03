package com.ers.jndi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

public class ErsUsersDAOImpl implements ErsUsersDAO{
	private Connection conn;
	
	public ErsUsersDAOImpl() throws SQLException, NamingException {
		conn = ServiceLocator.getersDatabase().getConnection();	
	}
	/*
	private void mapRows(ResultSet rs, List<ErsUsers> results) throws SQLException {
		while(rs.next()){
			//get values from row
			int id = rs.getInt("ERS_USERS_ID");
			String un = rs.getString("ERS_USERNAME");
			String pw = rs.getString("ERS_PASSWORD");
			String fn = rs.getString("USER_FIRST_NAME");
			String ln = rs.getString("USER_LAST_NAME");
			String em = rs.getString("USER_EMAIL");
			int roleid = rs.getInt("USER_ROLE_ID");
			//create genre object
			ErsUsers obj = new ErsUsers(id, un, pw, fn, ln, em, roleid);
			// add object to list 
			results.add(obj);
		}
	}*/
	
	@Override
	public void close() throws SQLException {
		conn.close();
	}

	@Override
	public boolean loginAuth(String username, String password) {
		return false;
	}

	@Override
	public String getName(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
