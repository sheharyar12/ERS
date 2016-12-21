package com.revature.beans;
/**
 * Title: Type
 * Description: Reimbursement type
 * @author Shehar
 *
 */
public class ErsReimbursementType {

	private int id;
	private String type;
	
	/**
	 * Parameterized constructor
	 * @param id
	 * @param type
	 */
	public ErsReimbursementType(int id, String type){
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * toString method override for testing 
	 */
	@Override
	public String toString() {
		return "ErsReimbursementType [id=" + id + ", type=" + type + "]";
	}
	
	
	
}
