package com.revature.beans;

import java.sql.Timestamp;

public class Reimbursement {
	private int reimbId;
	private float amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private int authorId;
	private int resolverId;
	private int statusId;
	private int typeId;

	

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Reimbursement(int reimbId, float amount, Timestamp submitted, Timestamp resolved, String description,
			int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolverId;
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + typeId;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}



	public int getReimbId() {
		return reimbId;
	}



	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}



	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
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



	public int getAuthorId() {
		return authorId;
	}



	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}



	public int getResolverId() {
		return resolverId;
	}



	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}



	public int getStatusId() {
		return statusId;
	}



	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}



	public int getTypeId() {
		return typeId;
	}



	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}



	public String toString() {
		return "ReimbId: " + reimbId + ", Amount: " + amount + ", Submitted: " + submitted + ", Resolved: " + resolved + ", Description: " + description
				+ ", Author: " + authorId + ", Resolver: " + resolverId + ", StatusId: " + statusId + ", TypeId: " + typeId + " ---END---";
	}
	
}
