package com.ui.model;

import java.util.List;

public class ProductTax {

	public ProductTax(int productTaxId, int taxId, float rate, int productId, int createdBy, String createdDate,
			String ipAddress, String taxName) {
		super();
		this.productTaxId = productTaxId;
		this.taxId = taxId;
		this.rate = rate;
		this.productId = productId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.taxName = taxName;
	}

	public ProductTax(int taxId, float rate, int productId, int createdBy, String ipAddress) {
		super();
		this.taxId = taxId;
		this.rate = rate;
		this.productId = productId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int productTaxId;
	private int taxId;
	private float rate;
	private int productId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	private String taxName;
	private String stateName;

	List<State> states;

	public int getProductTaxId() {
		return productTaxId;
	}

	public int getTaxId() {
		return taxId;
	}

	public float getRate() {
		return rate;
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

	public String getTaxName() {
		return taxName;
	}

	public String getStateName() {
		return stateName;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

}
