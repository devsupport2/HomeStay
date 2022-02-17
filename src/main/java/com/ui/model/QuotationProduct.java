package com.ui.model;

import java.util.List;

public class QuotationProduct {
	public QuotationProduct(int quotationProductId, int quotationId, int productId, String productName,
			String partNumber, float productQty, float salePrice) {
		super();
		this.quotationProductId = quotationProductId;
		this.quotationId = quotationId;
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.productQty = productQty;
		this.salePrice = salePrice;
	}
	public QuotationProduct(int quotationId, int productId, String productName, String partNumber, float productQty,
			float salePrice, int createdBy, String ipAddress) {
		super();
		this.quotationId = quotationId;
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.productQty = productQty;
		this.salePrice = salePrice;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int quotationProductId;
	private int quotationId;
	private int productId;
	private String productName;
	private String partNumber;
	private float productQty;
	private float salePrice;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	List<QuotationProductSpecification> specifications;
	List<QuotationProductScopeOfSupply> scopeOfSupplies;	
	
	public int getQuotationProductId() {
		return quotationProductId;
	}
	public int getQuotationId() {
		return quotationId;
	}
	public int getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public float getProductQty() {
		return productQty;
	}
	public float getSalePrice() {
		return salePrice;
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
	public List<QuotationProductSpecification> getSpecifications() {
		return specifications;
	}
	public void setSpecifications(List<QuotationProductSpecification> specifications) {
		this.specifications = specifications;
	}
	public List<QuotationProductScopeOfSupply> getScopeOfSupplies() {
		return scopeOfSupplies;
	}
	public void setScopeOfSupplies(List<QuotationProductScopeOfSupply> scopeOfSupplies) {
		this.scopeOfSupplies = scopeOfSupplies;
	}
	public void setQuotationProductId(int quotationProductId) {
		this.quotationProductId = quotationProductId;
	}
	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public void setProductQty(float productQty) {
		this.productQty = productQty;
	}
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
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
