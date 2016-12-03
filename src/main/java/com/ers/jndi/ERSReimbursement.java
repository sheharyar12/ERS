package com.ers.jndi;

import java.sql.Date;

public class ERSReimbursement {

	private int id,author,resolver,statusID,typeID;
	private double amount;
	private Date submitted,resolved;
	private String description;
	
	public ERSReimbursement(int id, double amount, Date submission, Date resolved, String desc, int authorID, int resolverID, int statusID, int typeID) {
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
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
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
		return "ERSReimbursement [id=" + id + ", author=" + author + ", resolver=" + resolver + ", statusID=" + statusID
				+ ", typeID=" + typeID + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + "]";
	}
	
	
}
