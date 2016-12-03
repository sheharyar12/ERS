package com.ers.jndi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Look up and cache services. Reduce performance 
 * overhead of looking up services.
 * 
 * @author Shehar
 *
 */
public class ServiceLocator {

	private static DataSource ers;
	private static Properties env;//environment props
	static{//used to initialize static variables.
		//executed when a class is loaded into JVM
		//load properties from jndi.properties in classpath
		InputStream stream = ServiceLocator.class.getClassLoader().getResourceAsStream("application.properties");
		env = new Properties();
		try{
			env.load(stream);
		}catch(IOException e){}
	}
	public synchronized static DataSource getersDatabase() throws NamingException{
		if(ers==null){
			ers= lookupers();
		}
		
		return ers;
	}
	private static DataSource lookupers() {
		//naming constructor
		try{
			//to look up in properties file e by looking at the key on the left of properties file.
			//use context object to perform a look up for database;
			Context ctxt = new InitialContext(env);
			//DataSource is returned here from the context look up
			DataSource ds = (DataSource) ctxt.lookup(env.getProperty("ersdb"));
			return ds;
		}catch(NamingException e){
			e.printStackTrace();
			return null;
		}

	}
	
}
