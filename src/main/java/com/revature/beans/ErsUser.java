package com.revature.beans;
/**
 * Title User
 * Description: User class holds all users information
 * @author Shehar
 *
 */
public class ErsUser {

	private int id;
	private ErsRoles roleid;
	private String un,pw,fn,ln,email;
	
	/**
	 * Parameterized Constructor
	 */
	public ErsUser(int id, String un,String pw,String fn,String ln,String email,ErsRoles roleid){
		this.id = id;
		this.un = un;
		this.pw = pw;
		this.fn = fn;
		this.ln = ln;
		this.email = email;
		this.roleid = roleid;
	}
	
	/**
	 * Mutator and Accesser Methods ---------------------------------------------------------------
	 */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ErsRoles getRoleid() {
		return roleid;
	}

	public void setRoleid(ErsRoles roleid) {
		this.roleid = roleid;
	}

	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	public String getLn() {
		return ln;
	}
	public void setLn(String ln) {
		this.ln = ln;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

/**
 *  over ride to String method for testing purposes
 */
	@Override
	public String toString() {
		return "ErsUser [id=" + id + ", roleid=" + roleid + ", un=" + un + ", pw=" + pw + ", fn=" + fn + ", ln=" + ln
				+ ", email=" + email + "]";
	}

	
	
}
