package com.ui.model;

public class QuotationTermStatement {
	public QuotationTermStatement(int quotationTermStatementId, int quotationId, int termMasterId, String termTitle,
			int sequence, String label, String statement) {
		super();
		this.quotationTermStatementId = quotationTermStatementId;
		this.quotationId = quotationId;
		this.termMasterId = termMasterId;
		this.termTitle = termTitle;
		this.sequence = sequence;
		this.label = label;
		this.statement = statement;
	}

	public QuotationTermStatement(int quotationId, int termMasterId, String termTitle, int sequence, String label,
			String statement, int createdBy, String ipAddress) {
		super();
		this.quotationId = quotationId;
		this.termMasterId = termMasterId;
		this.termTitle = termTitle;
		this.sequence = sequence;
		this.label = label;
		this.statement = statement;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int quotationTermStatementId;
	private int quotationId;
	private int termMasterId;
	private String termTitle;
	private int sequence;
	private String label;
	private String statement;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getQuotationTermStatementId() {
		return quotationTermStatementId;
	}

	public int getTermMasterId() {
		return termMasterId;
	}

	public String getLabel() {
		return label;
	}

	public String getStatement() {
		return statement;
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

	public String getTermTitle() {
		return termTitle;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void setQuotationTermStatementId(int quotationTermStatementId) {
		this.quotationTermStatementId = quotationTermStatementId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public void setTermMasterId(int termMasterId) {
		this.termMasterId = termMasterId;
	}

	public void setTermTitle(String termTitle) {
		this.termTitle = termTitle;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
