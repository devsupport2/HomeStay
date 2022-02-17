package com.ui.model;

public class Category {
	
	public Category(int categoryId, String categoryName, String categoryCode, String image, String description,
			String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryCode = categoryCode;
		this.image = image;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Category(int categoryId, String categoryName, String categoryCode, String image, String description,
			int createdBy, String ipAddress) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryCode = categoryCode;
		this.image = image;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Category(String categoryName, String categoryCode, String image, String description, String status,
			int createdBy, String ipAddress) {
		super();
		this.categoryName = categoryName;
		this.categoryCode = categoryCode;
		this.image = image;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int categoryId;
	private String categoryName;
	private String categoryCode;
	private String image;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getCategoryId() {
		return categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public String getImage() {
		return image;
	}
	public String getDescription() {
		return description;
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
