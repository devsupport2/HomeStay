
package com.ui.model;

public class User {

	public User(int userId, int code, float hourlyWages, float overtimeWages, String firstName, String lastName) {
    super();
    this.userId = userId;
    this.code = code;
    this.hourlyWages = hourlyWages;
    this.overtimeWages = overtimeWages;
    this.firstName = firstName;
    this.lastName = lastName;
    
  }

  public User(String email) {
		super();
		this.email = email;
	}

	public User(int userId, String userCompanyName, String firstName, String middleName, String lastName, String gender,
			String dateOfBirth, String aadharNumber, String passportNumber, String panNumber, String profilePicture,
			String address1, String address2, String address3, int stateId, String cityName, String pincode,
			String mobileNumber, String landlineNumber, String email, String password, int code, float hourlyWages, float overtimeWages, int userTypeId,int clientcategory,
			String ipAddress) {
		super();
		this.userId = userId;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.aadharNumber = aadharNumber;
		this.passportNumber = passportNumber;
		this.panNumber = panNumber;
		this.profilePicture = profilePicture;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.stateId = stateId;
		this.cityName = cityName;
		this.pincode = pincode;
		this.mobileNumber = mobileNumber;
		this.landlineNumber = landlineNumber;
		this.email = email;
		this.password = password;
		this.code = code;
        this.hourlyWages = hourlyWages;
        this.overtimeWages = overtimeWages;
		this.userTypeId = userTypeId;
		this.clientcategory=clientcategory;
		this.ipAddress = ipAddress;
	}

	public User(int userId, String userCompanyName, String firstName, String middleName, String lastName, String gender,
			String dateOfBirth, String aadharNumber, String passportNumber, String panNumber, String profilePicture,
			String address1, String address2, String address3, int stateId, String cityName, String pincode,
			String mobileNumber, String landlineNumber, String email, String password, int userTypeId, int code, float hourlyWages, float overtimeWages) {
		super();
		this.userId = userId;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.aadharNumber = aadharNumber;
		this.passportNumber = passportNumber;
		this.panNumber = panNumber;
		this.profilePicture = profilePicture;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.stateId = stateId;
		this.cityName = cityName;
		this.pincode = pincode;
		this.mobileNumber = mobileNumber;
		this.landlineNumber = landlineNumber;
		this.email = email;
		this.password = password;
		this.userTypeId = userTypeId;
		this.code = code;
        this.hourlyWages = hourlyWages;
        this.overtimeWages = overtimeWages;
        
	}

	public User(int userId, String userCompanyName, String firstName, String middleName, String lastName) {
		super();
		this.userId = userId;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public User(int userId, String userCompanyName) {
		super();
		this.userId = userId;
		this.userCompanyName = userCompanyName;
	}

	public User(int userId, String userCompanyName, String firstName, String middleName, String lastName,
			String mobileNumber, String email) {
		super();
		this.userId = userId;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public User(String userCompanyName, String firstName, String middleName, String lastName, String gender,
			String dateOfBirth, String aadharNumber, String passportNumber, String panNumber, String profilePicture,
			String address1, String address2, String address3, int stateId, String cityName, String pincode,
			String mobileNumber, String landlineNumber, String email, String password, int userTypeId, int code,
			float hourlyWages, float overtimeWages,int clientcategory,String status, int createdBy, String ipAddress) {
		super();
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.aadharNumber = aadharNumber;
		this.passportNumber = passportNumber;
		this.panNumber = panNumber;
		this.profilePicture = profilePicture;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.stateId = stateId;
		this.cityName = cityName;
		this.pincode = pincode;
		this.mobileNumber = mobileNumber;
		this.landlineNumber = landlineNumber;
		this.email = email;
		this.password = password;
		this.userTypeId = userTypeId;
		this.code = code;
		this.hourlyWages = hourlyWages;
		this.overtimeWages = overtimeWages;
		this.clientcategory= clientcategory;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		
	}

	public User(String email, String password, String ipAddress) {
		super();
		this.email = email;
		this.password = password;
		this.ipAddress = ipAddress;
	}

	public User(int userId, String firstName, String middleName, String lastName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public User(int userId, String firstName, String middleName, String lastName, String profilePicture,
			String mobileNumber, String email, int userTypeId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.profilePicture = profilePicture;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.userTypeId = userTypeId;
	}

	private int userId;
	private String userCompanyName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String dateOfBirth;
	private String aadharNumber;
	private String passportNumber;
	private String panNumber;
	private String profilePicture;
	private String address1;
	private String address2;
	private String address3;
	private int stateId;
	private String cityName;
	private String pincode;
	private String mobileNumber;
	private String landlineNumber;
	private String email;
	private String password;
	private int userTypeId;
	private int dealerId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String userTypeName;

	private int code;
	private float hourlyWages;
	private float overtimeWages;
	private int clientcategory;

	public int getUserId() {
		return userId;
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

	public String getGender() {
		return gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getAddress3() {
		return address3;
	}

	public int getStateId() {
		return stateId;
	}

	public String getCityName() {
		return cityName;
	}

	public String getPincode() {
		return pincode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getLandlineNumber() {
		return landlineNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getUserTypeId() {
		return userTypeId;
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

	public String getUserTypeName() {
		return userTypeName;
	}

	public String getUserCompanyName() {
		return userCompanyName;
	}

	public int getDealerId() {
		return dealerId;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public float getHourlyWages() {
		return hourlyWages;
	}

	public void setHourlyWages(float hourlyWages) {
		this.hourlyWages = hourlyWages;
	}

	public float getOvertimeWages() {
		return overtimeWages;
	}

	public void setOvertimeWages(float overtimeWages) {
		this.overtimeWages = overtimeWages;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserCompanyName(String userCompanyName) {
		this.userCompanyName = userCompanyName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public int getClientcategory() {
		return clientcategory;
	}

	public void setClientcategory(int clientcategory) {
		this.clientcategory = clientcategory;
	}
	
	
	
	
}
