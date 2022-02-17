package com.ui.model;

public class QuotationProductSpecification {
	public QuotationProductSpecification(int quotationProductSpecificationId, int quotationId, int productId,
			String specification, String specValue, String unitName) {
		super();
		this.quotationProductSpecificationId = quotationProductSpecificationId;
		this.quotationId = quotationId;
		this.productId = productId;
		this.specification = specification;
		this.specValue = specValue;
		this.unitName = unitName;
	}
	public QuotationProductSpecification(int quotationId, int productId, String specification, String specValue,
			String unitName, int createdBy, String ipAddress) {
		super();
		this.quotationId = quotationId;
		this.productId = productId;
		this.specification = specification;
		this.specValue = specValue;
		this.unitName = unitName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int quotationProductSpecificationId;	
	private int quotationId;
	private int productId;
	private String specification;
	private String specValue;
	private String unitName;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getQuotationProductSpecificationId() {
		return quotationProductSpecificationId;
	}	
	public int getQuotationId() {
		return quotationId;
	}
	public int getProductId() {
		return productId;
	}
	public String getSpecification() {
		return specification;
	}
	public String getSpecValue() {
		return specValue;
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
}
