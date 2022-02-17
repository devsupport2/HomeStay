package com.ui.model;

public class EnquiryUrl {
	public EnquiryUrl(int enquiryUrlId, int enquiryId, String url) {
		super();
		this.enquiryUrlId = enquiryUrlId;
		this.enquiryId = enquiryId;
		this.url = url;
	}
	public EnquiryUrl(int enquiryId, String url, int createdBy, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.url = url;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int enquiryUrlId;
	private int enquiryId;
	private String url;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getEnquiryUrlId() {
		return enquiryUrlId;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public String getUrl() {
		return url;
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
