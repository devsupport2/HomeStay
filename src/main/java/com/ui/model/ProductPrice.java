package com.ui.model;

public class ProductPrice {
	public ProductPrice(int productPriceId, float price, int currencyId) {
		super();
		this.productPriceId = productPriceId;
		this.price = price;
		this.currencyId = currencyId;
	}
	public ProductPrice(int productPriceId, int productId, int priceTypeId,
			float price, int currencyId, String priceTypeName,
			String currencyCode) {
		super();
		this.productPriceId = productPriceId;
		this.productId = productId;
		this.priceTypeId = priceTypeId;
		this.price = price;
		this.currencyId = currencyId;
		this.priceTypeName = priceTypeName;
		this.currencyCode = currencyCode;
	}
	public ProductPrice(int productId, int priceTypeId, float price,
			int currencyId, int createdBy, String ipAddress) {
		super();
		this.productId = productId;
		this.priceTypeId = priceTypeId;
		this.price = price;
		this.currencyId = currencyId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int productPriceId;
	private int productId;
	private int priceTypeId;
	private float price;
	private int currencyId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String priceTypeName;
	private String currencyCode;
	public int getProductPriceId() {
		return productPriceId;
	}
	public int getProductId() {
		return productId;
	}
	public int getPriceTypeId() {
		return priceTypeId;
	}
	public float getPrice() {
		return price;
	}
	public int getCurrencyId() {
		return currencyId;
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
	public String getPriceTypeName() {
		return priceTypeName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
}
