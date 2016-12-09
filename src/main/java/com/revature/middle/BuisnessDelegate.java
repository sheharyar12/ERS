package com.revature.middle;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.revature.beans.ErsUser;

public class BuisnessDelegate {

	public ErsUser login(String username,String password) throws SQLException, NamingException{
		return new Service().authUser(username, password);
	}
	
}
