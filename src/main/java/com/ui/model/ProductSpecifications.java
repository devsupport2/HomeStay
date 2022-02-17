package com.ui.model;

public class ProductSpecifications {
	public ProductSpecifications(int productSpecificationId, int productId, int sequence, String specification, String specValue,
			int unitId, String unitName) {
		super();
		this.productSpecificationId = productSpecificationId;
		this.productId = productId;
		this.sequence = sequence;
		this.specification = specification;
		this.specValue = specValue;
		this.unitId = unitId;
		this.unitName = unitName;
	}

	public ProductSpecifications(int productId, int sequence, String specification, String specValue, int unitId,
			int createdBy, String ipAddress) {
		super();
		this.productId = productId;
		this.sequence = sequence;
		this.specification = specification;
		this.specValue = specValue;
		this.unitId = unitId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int productSpecificationId;
	private int productId;
	private int sequence;
	private String specification;
	private String specValue;
	private int unitId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String unitName;

	public int getProductSpecificationId() {
		return productSpecificationId;
	}

	public int getProductId() {
		return productId;
	}

	public String getSpecification() {
		return specification;
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

	public String getSpecValue() {
		return specValue;
	}

	public int getUnitId() {
		return unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public int getSequence() {
		return sequence;
	}
}
