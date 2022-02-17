package com.ui.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ui.model.ClientCategory;
import com.ui.model.DealerProduct;
import com.ui.model.EmployeeActivities;
import com.ui.model.User;
import com.ui.model.UserType;

public interface UserDAO {
	public List<UserType> getUserTypes();
	public List<User> getAllUsers();		
	public void addUserType(UserType u);
	public void addUser(User u);
	public void editUser(User u);
	public void deleteUser(int userid);
	public List<User> getAllUsersByPage(int pagesize, int startindex);
	public List<User> searchUsers(String keyword);
	public List<User> getUsersById(int id);
	public User getUserDetailById(int userid);
	public int isEmailUnique(String email);
	public int isEmailUniqueWithId(String email, int userid);
	public List<User> getUserName();
	public List<User> getSuppliers();
	public List<User> getClientAndProspectName(HttpSession session);
	public List<User> getAllEmployees();
	public List<User> getUserByUserTypeId(int usertypeid);
	public int getLastUserId();
	public void addDealerProduct(DealerProduct dp);
	public List<DealerProduct> getDealerProductById(int userid);
	public void deleteDealerProduct(int dealerproductid);
	public void addEmployeeActivity(EmployeeActivities ea);
	public User getEmployeeDetails(int empcode);
	public List<EmployeeActivities> getEmpActivityByDate(String fromdate, String todate);
	public List<EmployeeActivities> getEmpActivityByDateAndCode(int code, String fromdate, String todate);
	public List getAllCategoryClient();
	public String addClientCategory(ClientCategory var1);
}
