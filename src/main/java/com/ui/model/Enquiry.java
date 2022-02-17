package com.ui.model;

import java.util.List;

public class Enquiry {
	public Enquiry(int enquiryId, String enquiryNo, int clientId, String enquiryDate, String userCompanyName,
			String firstName, String lastName, int followupId, String followupDate, String followupTime, String remark,
			String followupStatus) {
		super();
		this.enquiryId = enquiryId;
		this.enquiryNo = enquiryNo;
		this.clientId = clientId;
		this.enquiryDate = enquiryDate;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.followupId = followupId;
		this.followupDate = followupDate;
		this.followupTime = followupTime;
		this.remark = remark;
		this.followupStatus = followupStatus;
	}

	public Enquiry(int enquiryId, String enquiryNo, int clientId, String enquiryDate, String userCompanyName,
			String firstName, String lastName, int followupId, String followupTime, String remark,
			String followupStatus) {
		super();
		this.enquiryId = enquiryId;
		this.enquiryNo = enquiryNo;
		this.clientId = clientId;
		this.enquiryDate = enquiryDate;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.followupId = followupId;
		this.followupTime = followupTime;
		this.remark = remark;
		this.followupStatus = followupStatus;
	}

	public Enquiry(int enquiryId, String status, int reasonId) {
		super();
		this.enquiryId = enquiryId;
		this.status = status;
		this.reasonId = reasonId;
	}

	public Enquiry(int enquiryId, int referenceId, int clientId, int employeeId, String enquiryDate, String enquiryVia,
			String enquriRemarks, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;		
		this.ipAddress = ipAddress;
	}	
//*********************edited by yuvraj*************************************************************
	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
			String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
			String firstName, String lastName, String createdByName) {
		super();
		this.enquiryId = enquiryId;
		this.sequence = sequence;
		this.enquiryNo = enquiryNo;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.status = status;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdByName = createdByName;
	}
//****************************************************
	
	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
			String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
			String firstName, String lastName) {
		super();
		this.enquiryId = enquiryId;
		this.sequence = sequence;
		this.enquiryNo = enquiryNo;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.status = status;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	//mobileno and email
	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
			String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
			String firstName, String lastName,String phone,String email,String state,String city) {
		super();
		this.enquiryId = enquiryId;
		this.sequence = sequence;
		this.enquiryNo = enquiryNo;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.status = status;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone=phone;
		this.email=email;
		this.state=state;
		this.city=city;
	}

	public Enquiry(int sequence, String enquiryNo, int referenceId, int clientId, int employeeId, String enquiryDate,
			String enquiryVia, String enquriRemarks, String status, int createdBy, String ipAddress) {
		super();
		this.sequence = sequence;
		this.enquiryNo = enquiryNo;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	
	
	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
	          String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
	          String firstName, String lastName,String productDetail,String email,String MobileNo,String city,int stateId, String cityName) {
		  super();
	      this.enquiryId = enquiryId;
	      this.sequence = sequence;
	      this.enquiryNo = enquiryNo;
	      this.referenceId = referenceId;
	      this.clientId = clientId;
	      this.employeeId = employeeId;
	      this.enquiryDate = enquiryDate;
	      this.enquiryVia = enquiryVia;
	      this.enquriRemarks = enquriRemarks;
	      this.status = status;
	      this.userCompanyName = userCompanyName;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.ProductDetails=productDetail;
	      this.email=email;
	      this.phone=MobileNo;
	      this.city=city;
	      this.stateId=stateId;
	      this.state=cityName;
	      
	      
	      
	  }

	
	
	

	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
          String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
          String firstName, String lastName,String email,String MobileNo ) {
    // TODO Auto-generated constructor stub
	  
	  this.enquiryId = enquiryId;
      this.sequence = sequence;
      this.enquiryNo = enquiryNo;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.status = status;
      this.userCompanyName = userCompanyName;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email=email;
      this.phone=MobileNo;

 
  }





  public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
          String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
          String firstName, String lastName,String email,String MobileNo,String UserType ) {
		// TODO Auto-generated constructor stub
	  
	  this.enquiryId = enquiryId;
      this.sequence = sequence;
      this.enquiryNo = enquiryNo;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.status = status;
      this.userCompanyName = userCompanyName;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email=email;
      this.phone=MobileNo;
      this.UserType=UserType;
      
      
	}





private int enquiryId;
	private int sequence;
	private String enquiryNo;
	private int referenceId;
	private int clientId;
	private int employeeId;
	private String enquiryDate;
	private String enquiryVia;
	private String enquriRemarks;
	private String status;
	private int reasonId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String userCompanyName;
	private String firstName;
	private String lastName;
	private int followupId;
	private String followupDate;
	private String followupTime;
	private String remark;
	private String followupStatus;
	private String createdByName;
	private String phone;
	private String email;
	private String readyToInvoice;
	private String invoiceGenerated;
	private List<EnquiryAssign> assignedMemberList;
    private String state;
    private String city;
    private String ProductDetails;
    private String StartDate;
    private String EndDate;
    private int stateId;
	
    
    private String StartRefDate;
    private String EndRefDate;
    
    private String UserType;
    
    public int getEnquiryId() {
		return enquiryId;
	}

	public int getClientId() {
		return clientId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getEnquiryDate() {
		return enquiryDate;
	}

	public String getEnquiryVia() {
		return enquiryVia;
	}

	public String getEnquriRemarks() {
		return enquriRemarks;
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

	public String getLastName() {
		return lastName;
	}

	public int getSequence() {
		return sequence;
	}

	public String getEnquiryNo() {
		return enquiryNo;
	}

	public int getReferenceId() {
		return referenceId;
	}

	public String getUserCompanyName() {
		return userCompanyName;
	}

	public int getReasonId() {
		return reasonId;
	}

	public String getFollowupTime() {
		return followupTime;
	}

	public int getFollowupId() {
		return followupId;
	}

	public String getRemark() {
		return remark;
	}

	public String getFollowupDate() {
		return followupDate;
	}

	public String getFollowupStatus() {
		return followupStatus;
	}
	
	public String getReadyToInvoice() {
		return readyToInvoice;
	}

	public void setReadyToInvoice(String readyToInvoice) {
		this.readyToInvoice = readyToInvoice;
	}
	
	public String getInvoiceGenerated() {
		return invoiceGenerated;
	}

	public void setInvoiceGenerated(String invoiceGenerated) {
		this.invoiceGenerated = invoiceGenerated;
	}

  public List<EnquiryAssign> getAssignedMemberList() {
    return assignedMemberList;
  }

  public void setAssignedMemberList(List<EnquiryAssign> assignedMemberList) {
    this.assignedMemberList = assignedMemberList;
  }

  public void setEnquiryId(int enquiryId) {
    this.enquiryId = enquiryId;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public void setEnquiryNo(String enquiryNo) {
    this.enquiryNo = enquiryNo;
  }

  public void setReferenceId(int referenceId) {
    this.referenceId = referenceId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public void setEnquiryDate(String enquiryDate) {
    this.enquiryDate = enquiryDate;
  }

  public void setEnquiryVia(String enquiryVia) {
    this.enquiryVia = enquiryVia;
  }

  public void setEnquriRemarks(String enquriRemarks) {
    this.enquriRemarks = enquriRemarks;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setReasonId(int reasonId) {
    this.reasonId = reasonId;
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

  public void setUserCompanyName(String userCompanyName) {
    this.userCompanyName = userCompanyName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFollowupId(int followupId) {
    this.followupId = followupId;
  }

  public void setFollowupDate(String followupDate) {
    this.followupDate = followupDate;
  }

  public void setFollowupTime(String followupTime) {
    this.followupTime = followupTime;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public void setFollowupStatus(String followupStatus) {
    this.followupStatus = followupStatus;
  }

public String getCreatedByName() {
	return createdByName;
}

public void setCreatedByName(String createdByName) {
	this.createdByName = createdByName;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getProductDetails() {
	return ProductDetails;
}

public void setProductDetails(String productDetails) {
	ProductDetails = productDetails;
}

public int getStateId() {
	return stateId;
}

public void setStateId(int stateId) {
	this.stateId = stateId;
}

public String getStartDate() {
  return StartDate;
}

public void setStartDate(String startDate) {
  StartDate = startDate;
}

public String getEndDate() {
  return EndDate;
}

public void setEndDate(String endDate) {
  EndDate = endDate;
}

public String getUserType() {
	return UserType;
}

public void setUserType(String userType) {
	UserType = userType;
}

public String getStartRefDate() {
	return StartRefDate;
}

public void setStartRefDate(String startRefDate) {
	StartRefDate = startRefDate;
}

public String getEndRefDate() {
	return EndRefDate;
}

public void setEndRefDate(String endRefDate) {
	EndRefDate = endRefDate;
}





  
	
}
