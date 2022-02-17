package com.ui.model;

public class TaskAssign {

	
	
	
	private int task_assign_id;
	private int task_id;
	private int user_id;
	private int created_by;
	private String created_date;
	private String ip_address;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String taskDate;
	private String  Description;
	private String first_name;
	private String last_name;
	
	private String mobile_number;
	
	
	
	public TaskAssign(int taskid,int userid, int id, String ipAddress) {
		
		this.task_id=taskid;
		this.user_id=userid;
		this.created_by=id;
		this.ip_address=ipAddress;
		
	}
	
	
	
	
	public TaskAssign() {
		// TODO Auto-generated constructor stub
	}




	public int getTask_assign_id() {
		return task_assign_id;
	}
	public void setTask_assign_id(int task_assign_id) {
		this.task_assign_id = task_assign_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}




	public String getTaskDate() {
		return taskDate;
	}




	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}




	public String getDescription() {
		return Description;
	}




	public void setDescription(String description) {
		Description = description;
	}




	public String getFirst_name() {
		return first_name;
	}




	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}




	public String getLast_name() {
		return last_name;
	}




	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}




	public String getMobile_number() {
		return mobile_number;
	}




	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
