package com.ui.model;

public class EnquiryFile {
	public EnquiryFile(int enquiryFileId, int enquiryId, String fileTitle, String filePath) {
		super();
		this.enquiryFileId = enquiryFileId;
		this.enquiryId = enquiryId;
		this.fileTitle = fileTitle;
		this.filePath = filePath;
	}
	public EnquiryFile(int enquiryId, String fileTitle, String filePath, int createdBy, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.fileTitle = fileTitle;
		this.filePath = filePath;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int enquiryFileId;
	private int enquiryId;
	private String fileTitle;
	private String filePath;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getEnquiryFileId() {
		return enquiryFileId;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public String getFilePath() {
		return filePath;
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
