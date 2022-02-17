package com.ui.model;

public class TaskEmployee {
	
	
	
	private int TaskEmployeeId;
	private int employeeId;
	private String status;
	private int created_by;
	private String created_date;
	private String ip_address;
	
	
	
	
	public int getTaskEmployeeId() {
		return TaskEmployeeId;
	}
	public void setTaskEmployeeId(int taskEmployeeId) {
		TaskEmployeeId = taskEmployeeId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
	
	

}
