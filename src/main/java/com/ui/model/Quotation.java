package com.ui.model;

public class Quotation {
	public Quotation(int quotationId, int enquiryId, String quotationNo, String quotationDate, int clientId,
			String purchaseOrder, String coverLetter) {
		super();
		this.quotationId = quotationId;
		this.enquiryId = enquiryId;
		this.quotationNo = quotationNo;
		this.quotationDate = quotationDate;
		this.clientId = clientId;
		this.purchaseOrder = purchaseOrder;
		this.coverLetter = coverLetter;
	}

	public Quotation(int quotationId, int enquiryId, String quotationNo, String quotationDate, String userCompanyName,
			String firstName, String middleName, String lastName) {
		super();
		this.quotationId = quotationId;
		this.enquiryId = enquiryId;
		this.quotationNo = quotationNo;
		this.quotationDate = quotationDate;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Quotation(int sequence, int enquiryId, String quotationNo, String quotationDate, int clientId,
			String purchaseOrder, String purchaseOrderDate, String coverLetter, String status, int createdBy,
			String ipAddress) {
		super();
		this.sequence = sequence;
		this.enquiryId = enquiryId;
		this.quotationNo = quotationNo;
		this.quotationDate = quotationDate;
		this.clientId = clientId;
		this.purchaseOrder = purchaseOrder;
		this.purchaseOrderDate = purchaseOrderDate;
		this.coverLetter = coverLetter;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Quotation(int quotationId, int sequence, String quotationNo) {
		super();
		this.quotationId = quotationId;
		this.sequence = sequence;
		this.quotationNo = quotationNo;
	}

	private int quotationId;
	private int sequence;
	private int enquiryId;
	private String quotationNo;
	private String quotationDate;
	private int clientId;
	private String purchaseOrder;
	private String purchaseOrderDate;
	private float totalAmount;
	private String coverLetter;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String userCompanyName;
	private String firstName;
	private String middleName;
	private String lastName;

	public int getQuotationId() {
		return quotationId;
	}

	public int getSequence() {
		return sequence;
	}

	public int getEnquiryId() {
		return enquiryId;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public String getQuotationDate() {
		return quotationDate;
	}

	public int getClientId() {
		return clientId;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public String getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public String getCoverLetter() {
		return coverLetter;
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

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserCompanyName() {
		return userCompanyName;
	}
}
