package com.ui.model;

public class ProductImage {
	
	public ProductImage(int productImageId, int sequence, String imageTitle, String image, int productId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.productImageId = productImageId;
		this.sequence = sequence;
		this.imageTitle = imageTitle;
		this.image = image;
		this.productId = productId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public ProductImage(int sequence, String imageTitle, String image, int productId, int createdBy, String ipAddress) {
		super();
		this.sequence = sequence;
		this.imageTitle = imageTitle;
		this.image = image;
		this.productId = productId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int productImageId;
	private int sequence;
	private String imageTitle;
	private String image;
	private int productId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getProductImageId() {
		return productImageId;
	}
	public int getSequence() {
		return sequence;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public String getImage() {
		return image;
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
	
	

		
	
}
