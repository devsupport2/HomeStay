package com.ui.model;

public class QuotationProductScopeOfSupply {
	public QuotationProductScopeOfSupply(int quotationScopeOfSupplyId, int quotationId, int productId,
			String particular, String value, float qty, String unitName) {
		super();
		this.quotationScopeOfSupplyId = quotationScopeOfSupplyId;
		this.quotationId = quotationId;
		this.productId = productId;
		this.particular = particular;
		this.value = value;
		this.qty = qty;
		this.unitName = unitName;
	}
	public QuotationProductScopeOfSupply(int quotationId, int productId, String particular, String value, float qty,
			String unitName, int createdBy, String ipAddress) {
		super();
		this.quotationId = quotationId;
		this.productId = productId;
		this.particular = particular;
		this.value = value;
		this.qty = qty;
		this.unitName = unitName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int quotationScopeOfSupplyId;
	private int quotationId;
	private int productId;
	private String particular;
	private String value;
	private float qty;
	private String unitName;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getQuotationScopeOfSupplyId() {
		return quotationScopeOfSupplyId;
	}
	public int getQuotationId() {
		return quotationId;
	}	
	public String getParticular() {
		return particular;
	}
	public String getValue() {
		return value;
	}
	public String getUnitName() {
		return unitName;
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
	public float getQty() {
		return qty;
	}
	public int getProductId() {
		return productId;
	}
}
