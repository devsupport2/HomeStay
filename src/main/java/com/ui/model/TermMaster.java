package com.ui.model;

public class TermMaster {
	public TermMaster(int termMasterId, String termTitle, int createdBy, String ipAddress) {
		super();
		this.termMasterId = termMasterId;
		this.termTitle = termTitle;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public TermMaster(int termMasterId, String termTitle) {
		super();
		this.termMasterId = termMasterId;
		this.termTitle = termTitle;
	}
	private int termMasterId;
	private String termTitle;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getTermMasterId() {
		return termMasterId;
	}
	public String getTermTitle() {
		return termTitle;
	}
	public String getStatus() {
		return status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
}
