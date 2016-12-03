package com.ers.jndi;

import java.sql.SQLException;

public interface ErsUsersDAO {

	public void close() throws SQLException;
	
	public boolean loginAuth(String username,String password);
	
	public String getName(int id);
	
	
}
