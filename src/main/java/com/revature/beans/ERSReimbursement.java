package com.revature.beans;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * Title: Reimbursement Class
 * Description : Class will store information of an reimbursement for a user
 * @author Shehar
 *
 */
public class ERSReimbursement {

	private int id;
	private ERSReimbursementStatus statusID;
	private ErsReimbursementType typeID;
	private ErsUser author,resolver;
	private double amount;
	private Timestamp submitted,resolved;
	private String description;
	
	/**
	 * Parameterized Constructor
	 */
	public ERSReimbursement(int id, double amount, Timestamp submission, Timestamp resolved, String desc, ErsUser authorID, ErsUser resolverID, ERSReimbursementStatus statusID, ErsReimbursementType typeID) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submission;
		this.resolved = resolved;
		this.description = desc;
		this.author = authorID;
		this.resolver = resolverID;
		this.statusID = statusID;
		this.typeID = typeID;
	}

	/**
	 * Mutator and assessor Methods 
	 * ---------------------------------------------------------------------------------------------------------------
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ERSReimbursementStatus getStatusID() {
		return statusID;
	}

	public void setStatusID(ERSReimbursementStatus statusID) {
		this.statusID = statusID;
	}

	public ErsReimbursementType getTypeID() {
		return typeID;
	}

	public void setTypeID(ErsReimbursementType typeID) {
		this.typeID = typeID;
	}

	public ErsUser getAuthor() {
		return author;
	}

	public void setAuthor(ErsUser author) {
		this.author = author;
	}

	public ErsUser getResolver() {
		return resolver;
	}

	public void setResolver(ErsUser resolver) {
		this.resolver = resolver;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
/**
 * -----------------------------------------------------------------------------------------------------------------
 */
	
	
	/**
	 * toString and some string methods to test output of an reimbursement
	 */
	@Override
	public String toString() {
		return "ERSReimbursement [id=" + id + ", statusID=" + statusID + ", typeID=" + typeID + ", author=" + author
				+ ", resolver=" + resolver + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + "]";
	}
	
	public String userToString(){
		return typeID.getType() + "\t" + description + "\t" + amount + "\t" + submitted + "\t" + statusID.getStatus() + "\t" + resolved + "\t" + resolver.getFn();	
	}
	
	public String managerToString(){
		return author.getId() + " " + 
				author.getRoleid().getRole() + " " + 
				author.getFn() + " " + 
				author.getLn() + " " + 
				typeID.getType() + " " +
				statusID.getId() + " " + 
				description + " " + 
				amount + " " + 
				submitted + " " + 
				resolved + " " + 
				resolver.getFn() + ", " + 
				resolver.getLn();
	}
	




	
	
}
