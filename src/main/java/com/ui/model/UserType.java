package com.ui.model;

public class UserType {
	
	public UserType(int userTypeId, String userTypeName) {
		super();
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;		
	}
	public UserType(int userTypeId, String userTypeName, int createdBy, String ipAddress) {
		super();
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public UserType(String userTypeName, String status, int createdBy, String ipAddress) {
		super();
		this.userTypeName = userTypeName;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int userTypeId;
	private String userTypeName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getUserTypeId() {
		return userTypeId;
	}
	public String getUserTypeName() {
		return userTypeName;
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
