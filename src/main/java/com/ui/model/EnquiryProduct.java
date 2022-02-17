package com.ui.model;

public class EnquiryProduct {
	public EnquiryProduct(int enquiryProductId, int enquiryId, int productId, String productDetail,
			String readyToInvoice) {
		super();
		this.enquiryProductId = enquiryProductId;
		this.enquiryId = enquiryId;
		this.productId = productId;
		this.productDetail = productDetail;
		this.readyToInvoice = readyToInvoice;
	}
	
	public EnquiryProduct(int enquiryProductId, int enquiryId, int productId, String productDetail) {
		super();
		this.enquiryProductId = enquiryProductId;
		this.enquiryId = enquiryId;
		this.productId = productId;
		this.productDetail = productDetail;		
	}

	public EnquiryProduct(int enquiryId, int productId, String productDetail, String readyToInvoice, int createdBy,
			String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.productId = productId;
		this.productDetail = productDetail;
		this.readyToInvoice = readyToInvoice;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int enquiryProductId;
	private int enquiryId;
	private int productId;
	private String productDetail;
	private String readyToInvoice;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getEnquiryProductId() {
		return enquiryProductId;
	}

	public int getEnquiryId() {
		return enquiryId;
	}

	public String getProductDetail() {
		return productDetail;
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

	public int getProductId() {
		return productId;
	}

	public String getReadyToInvoice() {
		return readyToInvoice;
	}
}
