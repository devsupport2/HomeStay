package com.ui.model;

public class Company {
	public Company(int companyId, String companyName, String companyLogo, String gstNo, String contactNo1,
			String contactNo2, String faxNo, String website, String email, String bankName, String bankAdd,
			String accountNumber, String ifscCode, String swiftCode, String panNo, String add1, String add2,
			int countryId, int stateId, String city, String pincode, int createdBy, String ipAddress) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyLogo = companyLogo;
		this.gstNo = gstNo;
		this.contactNo1 = contactNo1;
		this.contactNo2 = contactNo2;
		this.faxNo = faxNo;
		this.website = website;
		this.email = email;
		this.bankName = bankName;
		this.bankAdd = bankAdd;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.swiftCode = swiftCode;
		this.panNo = panNo;
		this.add1 = add1;
		this.add2 = add2;
		this.countryId = countryId;
		this.stateId = stateId;
		this.city = city;
		this.pincode = pincode;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Company(int companyId, String companyName) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public Company(int companyId, String companyName, String companyLogo, String gstNo, String contactNo1,
			String contactNo2, String faxNo, String website, String email, String bankName, String bankAdd,
			String accountNumber, String ifscCode, String swiftCode, String panNo, String add1, String add2,
			int countryId, int stateId, String city, String pincode) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyLogo = companyLogo;
		this.gstNo = gstNo;
		this.contactNo1 = contactNo1;
		this.contactNo2 = contactNo2;
		this.faxNo = faxNo;
		this.website = website;
		this.email = email;
		this.bankName = bankName;
		this.bankAdd = bankAdd;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.swiftCode = swiftCode;
		this.panNo = panNo;
		this.add1 = add1;
		this.add2 = add2;
		this.countryId = countryId;
		this.stateId = stateId;
		this.city = city;
		this.pincode = pincode;
	}

	public Company(int companyId, String companyName, String companyLogo, String gstNo, String contactNo1,
			String contactNo2, String faxNo, String website, String add1, String add2, int countryId, int stateId,
			String city, String pincode) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyLogo = companyLogo;
		this.gstNo = gstNo;
		this.contactNo1 = contactNo1;
		this.contactNo2 = contactNo2;
		this.faxNo = faxNo;
		this.website = website;
		this.add1 = add1;
		this.add2 = add2;
		this.countryId = countryId;
		this.stateId = stateId;
		this.city = city;
		this.pincode = pincode;
	}

	public Company(String companyName, String companyLogo, String gstNo, String contactNo1, String contactNo2,
			String faxNo, String website, String email, String bankName, String bankAdd, String accountNumber,
			String ifscCode, String swiftCode, String panNo, String add1, String add2, int countryId, int stateId,
			String city, String pincode, String status, int createdBy, String ipAddress) {
		super();
		this.companyName = companyName;
		this.companyLogo = companyLogo;
		this.gstNo = gstNo;
		this.contactNo1 = contactNo1;
		this.contactNo2 = contactNo2;
		this.faxNo = faxNo;
		this.website = website;
		this.email = email;
		this.bankName = bankName;
		this.bankAdd = bankAdd;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.swiftCode = swiftCode;
		this.panNo = panNo;
		this.add1 = add1;
		this.add2 = add2;
		this.countryId = countryId;
		this.stateId = stateId;
		this.city = city;
		this.pincode = pincode;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int companyId;
	private String companyName;
	private String companyLogo;
	private String gstNo;
	private String contactNo1;
	private String contactNo2;
	private String faxNo;
	private String website;
	private String email;
	private String bankName;
	private String bankAdd;
	private String accountNumber;
	private String ifscCode;
	private String swiftCode;
	private String panNo;
	private String add1;
	private String add2;
	private int countryId;
	private int stateId;
	private String city;
	private String pincode;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getGstNo() {
		return gstNo;
	}

	public String getContactNo1() {
		return contactNo1;
	}

	public String getContactNo2() {
		return contactNo2;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public String getWebsite() {
		return website;
	}

	public String getAdd1() {
		return add1;
	}

	public String getAdd2() {
		return add2;
	}

	public String getCity() {
		return city;
	}

	public int getStateId() {
		return stateId;
	}

	public int getCountryId() {
		return countryId;
	}

	public String getPincode() {
		return pincode;
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

	public String getCompanyLogo() {
		return companyLogo;
	}

	public String getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankAdd() {
		return bankAdd;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public String getPanNo() {
		return panNo;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
}
