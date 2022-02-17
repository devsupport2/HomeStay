package com.ui.dao;

import java.util.List;
import com.ui.model.Company;

public interface CompanyDAO {
	public List<Company> getAllCompany();	
	public List<Company> getAllCompanyTitle();
	public List<Company> getCompanyByPage(int pagesize, int startindex);
	public List<Company> searchCompany(String keyword);
	public Company getCompanyById(int boxid);
	public void addCompany(Company c);
	public void editCompany(Company c);
	public void deleteCompany(int companyid);
}
