package com.ui.model;

public class Invoice {
	public Invoice(int invoiceId, int enquiryId) {
		super();
		this.invoiceId = invoiceId;
		this.enquiryId = enquiryId;
	}

	public Invoice(int invoiceId, int invoiceTypeId, String invoiceDate, int companyId, int clientId,
			String purchaseOrder, String purchaseOrderDate, int createdBy, String ipAddress) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceTypeId = invoiceTypeId;
		this.invoiceDate = invoiceDate;
		this.companyId = companyId;
		this.clientId = clientId;
		this.purchaseOrder = purchaseOrder;
		this.purchaseOrderDate = purchaseOrderDate;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Invoice(int invoiceId, int enquiryId, String invoiceNo, int invoiceTypeId, String invoiceDate, int companyId,
			int clientId, String purchaseOrder, String purchaseOrderDate) {
		super();
		this.invoiceId = invoiceId;
		this.enquiryId = enquiryId;
		this.invoiceNo = invoiceNo;
		this.invoiceTypeId = invoiceTypeId;
		this.invoiceDate = invoiceDate;
		this.companyId = companyId;
		this.clientId = clientId;
		this.purchaseOrder = purchaseOrder;
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public Invoice(int invoiceId, int enquiryId, String invoiceNo, String invoiceDate, String invoiceTypeName,
			String companyName, String firstName, String middleName, String lastName) {
		super();
		this.invoiceId = invoiceId;
		this.enquiryId = enquiryId;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.invoiceTypeName = invoiceTypeName;
		this.companyName = companyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Invoice(int sequence, int enquiryId, String invoiceNo, int invoiceTypeId, String invoiceDate, int companyId,
			int clientId, String purchaseOrder, String purchaseOrderDate, String status, int createdBy,
			String ipAddress) {
		super();
		this.sequence = sequence;
		this.enquiryId = enquiryId;
		this.invoiceNo = invoiceNo;
		this.invoiceTypeId = invoiceTypeId;
		this.invoiceDate = invoiceDate;
		this.companyId = companyId;
		this.clientId = clientId;
		this.purchaseOrder = purchaseOrder;
		this.purchaseOrderDate = purchaseOrderDate;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Invoice(int invoiceId, int sequence, String invoiceNo) {
		super();
		this.invoiceId = invoiceId;
		this.sequence = sequence;
		this.invoiceNo = invoiceNo;
	}

	private int invoiceId;
	private int sequence;
	private int enquiryId;
	private String invoiceNo;
	private int invoiceTypeId;
	private String invoiceDate;
	private int companyId;
	private int clientId;
	private String purchaseOrder;
	private String purchaseOrderDate;
	private float totalAmount;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String invoiceTypeName;
	private String companyName;
	private String firstName;
	private String middleName;
	private String lastName;

	public int getInvoiceId() {
		return invoiceId;
	}

	public int getSequence() {
		return sequence;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public int getCompanyId() {
		return companyId;
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

	public int getInvoiceTypeId() {
		return invoiceTypeId;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public String getCompanyName() {
		return companyName;
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

	public int getEnquiryId() {
		return enquiryId;
	}
}
