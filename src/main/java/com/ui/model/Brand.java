package com.ui.model;

public class Brand {
	
	public Brand(int brandId, String brandName, String logo, String description, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.logo = logo;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Brand(int brandId, String brandName, String logo, String description, int createdBy, String ipAddress) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.logo = logo;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Brand(String brandName, String logo, String description, String status, int createdBy, String ipAddress) {
		super();
		this.brandName = brandName;
		this.logo = logo;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int brandId;
	private String brandName;
	private String logo;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getBrandId() {
		return brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public String getLogo() {
		return logo;
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
