package com.ui.model;

public class DealerProduct {
	public DealerProduct(int dealerProductId, int productId, String productName) {
		super();
		this.dealerProductId = dealerProductId;
		this.productId = productId;
		this.productName = productName;
	}
	public DealerProduct(int userId, int productId, int createdBy, String ipAddress) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int dealerProductId;
	private int userId;
	private int productId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String productName;
	
	public int getDealerProductId() {
		return dealerProductId;
	}
	public int getUserId() {
		return userId;
	}
	public int getProductId() {
		return productId;
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
	public String getProductName() {
		return productName;
	}
}
