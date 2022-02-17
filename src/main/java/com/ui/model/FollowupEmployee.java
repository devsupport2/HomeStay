package com.ui.model;

public class FollowupEmployee {
	public FollowupEmployee(int followupEmpId, int followupId, int userId, String firstName, String lastName) {
		super();
		this.followupEmpId = followupEmpId;
		this.followupId = followupId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public FollowupEmployee(int followupId, int userId, int createdBy, String ipAddress) {
		super();
		this.followupId = followupId;
		this.userId = userId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int followupEmpId;
	private int followupId;
	private int userId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String firstName;
	private String lastName;
	
	public int getFollowupEmpId() {
		return followupEmpId;
	}
	public int getFollowupId() {
		return followupId;
	}
	public int getUserId() {
		return userId;
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
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public void setFollowupEmpId(int followupEmpId) {
    this.followupEmpId = followupEmpId;
  }
  public void setFollowupId(int followupId) {
    this.followupId = followupId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
}
