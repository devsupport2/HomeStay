package com.ui.model;

public class PriceType {
	public PriceType(int priceTypeId, String priceTypeName) {
		super();
		this.priceTypeId = priceTypeId;
		this.priceTypeName = priceTypeName;
	}
	private int priceTypeId;
	private String priceTypeName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getPriceTypeId() {
		return priceTypeId;
	}
	public String getPriceTypeName() {
		return priceTypeName;
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
