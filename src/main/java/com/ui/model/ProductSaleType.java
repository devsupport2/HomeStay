package com.ui.model;

public class ProductSaleType {
	public ProductSaleType(int productSaleTypeId, String productSaleTypeName,
			String description) {
		super();
		this.productSaleTypeId = productSaleTypeId;
		this.productSaleTypeName = productSaleTypeName;
		this.description = description;
	}
	private int productSaleTypeId;
	private String productSaleTypeName;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getProductSaleTypeId() {
		return productSaleTypeId;
	}
	public String getProductSaleTypeName() {
		return productSaleTypeName;
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
