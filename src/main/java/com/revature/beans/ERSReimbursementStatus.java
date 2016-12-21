package com.revature.beans;
/**
 * Title: Status
 * 
 * Description: Status class holds the position of the user
 * @author Shehar
 *
 */
public class ERSReimbursementStatus {

	int id;
	String status;
	
	/**
	 * Parameterized Constructor
	 * @param id
	 * @param status
	 */
	public ERSReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	/**
	 * Mutator & Accessor Methods ----------------------------------------------------
	 */
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
	/**
	 * -----------------------------------------------------------------------------------
	 */
	
	/**
	 * Method: toString
	 * Description: override toString and for testing purposes
	 */
	@Override
	public String toString() {
		return "ERSReimbursementStatus [id=" + id + ", status=" + status + "]";
	}
	
	
}
