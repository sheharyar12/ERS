package com.ers.jndi;

public class ErsRoles {

	private int id;
	private String role;
	
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
	public ErsRoles(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	@Override
	public String toString() {
		return "ErsRoles [id=" + id + ", role=" + role + "]";
	}
	
}
