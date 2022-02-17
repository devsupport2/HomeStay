package com.ui.model;

public class CoverLetter {
	public CoverLetter(int coverLetterId, String coverLetterTitle, String coverLetterDescription) {
		super();
		this.coverLetterId = coverLetterId;
		this.coverLetterTitle = coverLetterTitle;
		this.coverLetterDescription = coverLetterDescription;
	}
	public CoverLetter(int coverLetterId, String coverLetterDescription, int createdBy, String ipAddress) {
		super();
		this.coverLetterId = coverLetterId;
		this.coverLetterDescription = coverLetterDescription;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int coverLetterId;
	private String coverLetterTitle;
	private String coverLetterDescription;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getCoverLetterId() {
		return coverLetterId;
	}
	public String getCoverLetterTitle() {
		return coverLetterTitle;
	}
	public String getCoverLetterDescription() {
		return coverLetterDescription;
	}
	public String getStatus() {
		return status;
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
