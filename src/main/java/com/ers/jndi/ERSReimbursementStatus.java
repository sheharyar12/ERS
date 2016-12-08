package com.ers.jndi;

public class ERSReimbursementStatus {

	int id;
	String status;
	
	public ERSReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ERSReimbursementStatus [id=" + id + ", status=" + status + "]";
	}
	
	
}
