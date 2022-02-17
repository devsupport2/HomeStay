package com.ui.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ui.model.AllEnquiryStatus;
import com.ui.model.Enquiry;
import com.ui.model.EnquiryAssign;
import com.ui.model.EnquiryFile;
import com.ui.model.EnquiryFollowup;
import com.ui.model.EnquiryFollowupMember;
import com.ui.model.EnquiryLogMember;
import com.ui.model.EnquiryProduct;
import com.ui.model.EnquiryStatus;
import com.ui.model.EnquiryStatusReason;
import com.ui.model.EnquiryUrl;
import com.ui.model.FollowupEmployee;
import com.ui.model.Task;
import com.ui.model.TaskAssign;
import com.ui.model.UserType;


public interface EnquiryDAO {
	public List<Enquiry> getAllOpenEnquiries(HttpSession session);
	public List<Enquiry> getEnquiriesByStatus(String status, HttpSession session);
	public List<Enquiry> getEnquiriesByPage(String StartDate, String EndDate, int pagesize, int startindex, HttpSession session);
	public List<Enquiry> searchEnquiry(String keyword, HttpSession session);
	public void addEnquiry(Enquiry e);
	public int getLastEnquiryId();
	public void addEnquiryProduct(EnquiryProduct e);
	public void addEnquiryUrl(EnquiryUrl e);
	public void addEnquiryFiles(EnquiryFile e);
	public List<EnquiryProduct> getEnquiryProductsByEnquiryId(int enquiryid);
	public List<EnquiryUrl> getEnquiryUrlsByEnquiryId(int enquiryid);
	public List<EnquiryFile> getEnquiryFilesByEnquiryId(int enquiryid);
	public List<EnquiryStatus> getEnquiryLogByEnquiryId(int enquiryid);
	public List<EnquiryFollowup> getEnquiryFollowupByEnquiryId(int enquiryid);
	public void deleteEnquiry(int enquiryid);
	public void deleteEnquiryProduct(int enquiryid);
	public void deleteEnquiryUrl(int enquiryid);
	public void deleteEnquiryFile(int enquiryid);
	public void editEnquiry(Enquiry e);
	public Enquiry getEnquiryDetailsById(int enquiryid);
	public Enquiry getLastEnquiryDetail();
	public int getLastEnquirySequence();
	public void addEnquiryStatus(EnquiryStatus e);
	public void addEnquiryFollowup(EnquiryFollowup e);
	public void closeEnquiry(int enquiryid);
	public void openEnquiry(int enquiryid);
	public int getLastEnquiryStatusId();
	public void addEnquiryLog(EnquiryStatus e);
	public List<EnquiryProduct> getAllEnquiryProductName();
	public void addStatusReason(EnquiryStatusReason esr);
	public List<EnquiryStatusReason> getStatusReason();
	public void insertEnquiryStatus(AllEnquiryStatus aes);
	public void changeEnquiryStatus(Enquiry e);
	public List<Enquiry> getTodayFollowupEnquiries(HttpSession session);
	public List<Enquiry> getFollowupEnquiriesByDate(HttpSession session, String fromdate, String todate);
	public String markCompleteFollowup(EnquiryFollowup ef);
	public void deleteFollowup(int followupid);
	public List<EnquiryProduct> getEnquiryReadyToIncodeProductByEnquiryId(int enquiryid);
	public List<Enquiry> getTodayFiveFollowupForDashboard(HttpSession session);
	public void addEnquiryAssign(EnquiryAssign e);
	public List<EnquiryAssign> getEnquiryAssignByEnquiryId(int enquiryid);
	public void deleteEnquiryAssignRow(int id);
	public void addLogMemberAssign(EnquiryLogMember e);
	public EnquiryStatus getLastEnquiryStatusDetail();
	public List<EnquiryLogMember> getEnquiryAssignMemberById(int enquirylogid);
	public int getLastEnquiryFollowupId();
	public void addFollowupMemberAssign(EnquiryFollowupMember e);
	public EnquiryFollowup getLastEnquiryFollowupDetail();
	public List<EnquiryFollowupMember> getEnquiryFollowupMemberById(int enquiryfollowupid);
	
	public int getLastFollowupId();
	public void addEmpFollowup(FollowupEmployee followupEmployee);
	
	public List<FollowupEmployee> getFollowupEmployeeByFollowupId(int followupId);
	
	//TASK PORTION
	public int addTask(Task task);
	public void addTaskAssign(TaskAssign taskAssign);
	int getLastTaskId();
	public List<Task> getAllTask();
	public List<UserType> getRefenceByRefId(int RefId);
	public List<Enquiry> getReferencesByDateId(String startRefDate, String endRefDate, int refTypeId, HttpSession session);
	
	
}
