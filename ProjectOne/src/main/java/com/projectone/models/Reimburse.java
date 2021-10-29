package com.projectone.models;

public class Reimburse{
	
	private int imbursId;
	private int recipientid;
	private String imburseType;
	private String imburseStatus;
	private String imbursedescription;
	private double imburseAmount;
	
	
	public Reimburse() {
		super();
	}

	public Reimburse(int imbursId, int recipientid, String imburseType, String imburseStatus, String imbursedescription,
			double imburseAmount ) {
		super();
		this.imbursId = imbursId;
		this.recipientid = recipientid;
		this.imburseType = imburseType;
		this.imburseStatus = imburseStatus;
		this.imbursedescription = imbursedescription;
		this.imburseAmount = imburseAmount;
		
	}

	public int getImbursId() {
		return imbursId;
	}

	public void setImbursId(int imbursId) {
		this.imbursId = imbursId;
	}

	public String getImbursType() {
		return imburseType;
	}

	public void setImbursType(String imbursType) {
		this.imburseType = imbursType;
	}

	public String getImbursStatus() {
		return imburseStatus;
	}

	public void setImbursStatus(String imbursStatus) {
		this.imburseStatus = imbursStatus;
	}
	
	public String getImbursedescription() {
		return imbursedescription;
	}

	public void setImbursedescription(String imbursedescription) {
		this.imbursedescription = imbursedescription;
	}

	public double getImburseAmount() {
		return imburseAmount;
	}

	public void setImburseAmount(double imburseAmount) {
		this.imburseAmount = imburseAmount;
	}

	public int getRecipientid() {
		return recipientid;
	}

	public void setRecipientid(int recipientid) {
		this.recipientid = recipientid;
	}

	
}
