package com.revature.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.ers.jndi.ServiceLocator;
import com.revature.beans.ErsRoles;
import com.revature.beans.ErsUser;

public class ErsUsersDAOImpl{
	private Connection conn;
	
	public ErsUsersDAOImpl() throws SQLException, NamingException {
		conn = ServiceLocator.getersDatabase().getConnection();	
	}
	

	public void close() throws SQLException {
		conn.close();
	}

	
	public ErsUser lookForUser(String username, String password) throws SQLException {
		
		String sql = "select * from ERS_USERS where ERS_USERNAME=? AND ERS_PASSWORD=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
	    ResultSet rs = stmt.executeQuery();
	    
	    if (rs.next()) {
	    		ErsRoles role;
	    		if(rs.getInt("USER_ROLE_ID")==2){
	    			role = new ErsRoles(2, "EMPLOYEE");
	    		}
	    		else{
	    			role = new ErsRoles(1, "Manager");
	    		}
			  ErsUser user = new ErsUser(
					  rs.getInt("ERS_USERS_ID"),
					  rs.getString("ERS_USERNAME"),
					  rs.getString("ERS_PASSWORD"),
					  rs.getString("USER_FIRST_NAME"),
					  rs.getString("USER_LAST_NAME"),
					  rs.getString("USER_EMAIL"),
					  role);
			  System.out.println("user Created " + user.getId() + " " + user.getFn());
			  close();
			  return user;
			  
	      }
		System.out.println("user not found: remove this at end");
		  close();
		
		return null;
	
	}



}
