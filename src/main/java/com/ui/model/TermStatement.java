package com.ui.model;

public class TermStatement {
	public TermStatement(int termStatementId, int termMasterId, int sequence, String label, String statement, String termTitle) {
		super();
		this.termStatementId = termStatementId;
		this.termMasterId = termMasterId;
		this.sequence = sequence;
		this.label = label;
		this.statement = statement;
		this.termTitle = termTitle;
	}
	public TermStatement(int termMasterId, int sequence, String label, String statement, int createdBy, String ipAddress) {
		super();
		this.termMasterId = termMasterId;
		this.sequence = sequence;
		this.label = label;
		this.statement = statement;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public TermStatement(int termStatementId, int termMasterId, int sequence, String label, String statement) {
		super();
		this.termStatementId = termStatementId;
		this.termMasterId = termMasterId;
		this.sequence = sequence;
		this.label = label;
		this.statement = statement;
	}
	private int termStatementId;
	private int termMasterId;
	private int sequence;
	private String label;
	private String statement;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String termTitle;
	public int getTermStatementId() {
		return termStatementId;
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
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public void setTermStatementId(int termStatementId) {
		this.termStatementId = termStatementId;
	}
	public void setTermMasterId(int termMasterId) {
		this.termMasterId = termMasterId;
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
	public void setTermTitle(String termTitle) {
		this.termTitle = termTitle;
	}
}
