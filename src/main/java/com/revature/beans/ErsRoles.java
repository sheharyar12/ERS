package com.revature.beans;
/**
 * Title: Roles
 * Description: User roles 
 * @author Shehar
 *
 */
public class ErsRoles {

	private int id;
	private String role;
	
	/**
	 * Parameterized Constructor
	 * @param id
	 * @param role
	 */
	public ErsRoles(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	/**
	 * Mutator Methods and Accesser methods ------------
	 * @return
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
/**
 * -------------------------------------------------------
 */
	
	/**
	 * to String
	 * over ride toString for testing purposes
	 */
	@Override
	public String toString() {
		return "ErsRoles [id=" + id + ", role=" + role + "]";
	}
	
}
