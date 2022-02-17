package com.ui.model;

import java.util.List;

public class Task {
	
	
	private int taskId;
	private String taskDate;
	private String addignTo;
	private String Description;
	private String status;
	private int createdBy;
	private String ipAddress;
	private String createdDate;
	private List<TaskAssign> TaskAssignMemberList;
	private int EmployeeId;
	private int employeeidadd;
	
	private int user_id;
	
	
	
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getEmployeeidadd() {
		return employeeidadd;
	}
	public void setEmployeeidadd(int employeeidadd) {
		this.employeeidadd = employeeidadd;
	}
	
	public Task(int sequence, int employeeid, String taskdate2, String description2, String s, int id,
			String ipAddress2) {
		
		
		
	}
	public Task() {
		// TODO Auto-generated constructor stub
	}
	public Task(String taskdate2, int employeeid2, String description2, String s, int id, String ipAddress2) {
		
		
		this.taskDate=taskdate2;
		this.EmployeeId=employeeid2;
		this.Description=description2;
		

		
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	public String getAddignTo() {
		return addignTo;
	}
	public void setAddignTo(String addignTo) {
		this.addignTo = addignTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public List<TaskAssign> getTaskAssignMemberList() {
		return TaskAssignMemberList;
	}
	public void setTaskAssignMemberList(List<TaskAssign> taskAssignMemberList) {
		TaskAssignMemberList = taskAssignMemberList;
	}
	
	
	
	
	
	
	
	

	
    
  
    
    
    
    
	

}
