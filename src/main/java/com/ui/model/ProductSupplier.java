package com.ui.model;

public class ProductSupplier {
	public ProductSupplier(int productSupplierId, int productId,  String supplierName,
			int supplierId) {
		super();
		this.productSupplierId = productSupplierId;
		this.productId = productId;
		this.supplierName = supplierName;
		this.supplierId = supplierId;		
	}
	public ProductSupplier(int productId, int supplierId, int createdBy,
			String ipAddress) {
		super();
		this.productId = productId;
		this.supplierId = supplierId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int productSupplierId;
	private int productId;
	private int supplierId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String supplierName;	
	public int getProductSupplierId() {
		return productSupplierId;
	}
	public int getProductId() {
		return productId;
	}
	public int getSupplierId() {
		return supplierId;
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
	public String getSupplierName() {
		return supplierName;
	}
}
