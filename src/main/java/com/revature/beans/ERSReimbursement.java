package com.revature.beans;

import java.sql.Date;

public class ERSReimbursement {

	private int id;
	private ERSReimbursementStatus statusID;
	private ErsReimbursementType typeID;
	private ErsUser author,resolver;
	private double amount;
	private Date submitted,resolved;
	private String description;
	
	public ERSReimbursement(int id, double amount, Date submission, Date resolved, String desc, ErsUser authorID, ErsUser resolverID, ERSReimbursementStatus statusID, ErsReimbursementType typeID) {
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

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
				description + " " + 
				amount + " " + 
				submitted + " " + 
				resolved + " " + 
				resolver.getFn() + ", " + 
				resolver.getLn();
	}
	




	
	
}
