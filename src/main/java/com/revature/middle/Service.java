package com.revature.middle;

import java.sql.SQLException;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;

import com.revature.beans.ErsUser;
import com.revature.data.Facade;

public class Service {

	public ErsUser authUser(String username, String password) throws SQLException, NamingException{
		ErsUser user = new Facade().createUser(username, password);
		if(user.equals(null)){
			throw new AuthenticationException();
		}else if(user.getPw().equals(password)){
			return user;
		}else{
			return null;
		}
	}
	
}
