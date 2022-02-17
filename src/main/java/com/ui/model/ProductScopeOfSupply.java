package com.ui.model;

public class ProductScopeOfSupply {
	public ProductScopeOfSupply(int scopeOfSupplyId, int productId, int sequence, String particular, String value, int unitId,
			String unitName) {
		super();
		this.scopeOfSupplyId = scopeOfSupplyId;
		this.productId = productId;
		this.sequence = sequence;
		this.particular = particular;
		this.value = value;
		this.unitId = unitId;
		this.unitName = unitName;
	}
	public ProductScopeOfSupply(int productId, int sequence, String particular, String value, int unitId, int createdBy,
			String ipAddress) {
		super();
		this.productId = productId;
		this.sequence = sequence;
		this.particular = particular;
		this.value = value;
		this.unitId = unitId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int scopeOfSupplyId;
	private int productId;
	private int sequence;
	private String particular;
	private String value;
	private int unitId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String unitName;
	public int getScopeOfSupplyId() {
		return scopeOfSupplyId;
	}
	public int getProductId() {
		return productId;
	}
	public String getParticular() {
		return particular;
	}
	public String getValue() {
		return value;
	}
	public int getUnitId() {
		return unitId;
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
	public String getUnitName() {
		return unitName;
	}
	public int getSequence() {
		return sequence;
	}
}
