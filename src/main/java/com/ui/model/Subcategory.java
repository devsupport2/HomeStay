package com.ui.model;

public class Subcategory {
	
	public Subcategory(int subcategoryId, String subcategoryName, String subcategoryCode, String image, String description,
			int categoryId, String status, int createdBy, String createdDate, String ipAddress, String categoryName) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
		this.subcategoryCode = subcategoryCode;
		this.image = image;
		this.description = description;
		this.categoryId = categoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.categoryName = categoryName;
	}
	public Subcategory(int subcategoryId, String subcategoryName, String subcategoryCode, String image, String description,
			int categoryId, int createdBy, String ipAddress) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
		this.subcategoryCode = subcategoryCode;
		this.image = image;
		this.description = description;
		this.categoryId = categoryId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Subcategory(String subcategoryName, String subcategoryCode, String image, String description, int categoryId, String status,
			int createdBy, String ipAddress) {
		super();
		this.subcategoryName = subcategoryName;
		this.subcategoryCode = subcategoryCode;
		this.image = image;
		this.description = description;
		this.categoryId = categoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int subcategoryId;
	private String subcategoryName;
	private String subcategoryCode;
	private String image;
	private String description;
	private int categoryId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String categoryName;
	
	
	public int getSubcategoryId() {
		return subcategoryId;
	}
	public String getSubcategoryName() {
		return subcategoryName;
	}
	public String getSubcategoryCode() {
		return subcategoryCode;
	}
	public String getImage() {
		return image;
	}
	public String getDescription() {
		return description;
	}
	public int getCategoryId() {
		return categoryId;
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
	public String getCategoryName() {
		return categoryName;
	}
	
	
	
		
	
}
