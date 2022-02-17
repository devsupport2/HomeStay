package com.ui.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ui.dao.CompanyDAO;
import com.ui.model.Company;

@RestController
public class CompanyController {
	@Autowired
	CompanyDAO companyDao;
	Company company;

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@RequestMapping(value = "/getAllCompany", method = RequestMethod.GET, produces = "application/json")
	public List<Company> getAllCompany(HttpServletRequest request) {
		logger.info("***** GET ALL COMPANY *****");
		List<Company> company = companyDao.getAllCompany();
		return company;
	}	

	@RequestMapping(value = "/getAllCompanyTitle", method = RequestMethod.GET, produces = "application/json")
	public List<Company> getAllCompanyTitle(HttpServletRequest request) {
		logger.info("***** GET ALL COMPANY TITLES *****");
		List<Company> company = companyDao.getAllCompanyTitle();
		return company;
	}

	@RequestMapping(value = "/getCompanyByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Company> getCompanyByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET COMPANY BY PAGE *****");
		List<Company> company = companyDao.getCompanyByPage(pagesize, startindex);
		return company;
	}

	@RequestMapping(value = "/searchCompany", method = RequestMethod.GET, produces = "application/json")
	public List<Company> searchCompany(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH COMPANY *****");
		List<Company> company = companyDao.searchCompany(keyword);
		return company;
	}

	@RequestMapping(value = "/getCompanyById", method = RequestMethod.GET, produces = "application/json")
	public Company getCompanyById(int companyid, HttpServletRequest request) {
		logger.info("***** GET COMPANY BY ID *****");
		Company company = companyDao.getCompanyById(companyid);
		return company;
	}

	@RequestMapping(value = "addCompany", method = RequestMethod.POST)
	public String addCompany(@RequestParam(value = "image", required = false) MultipartFile image, String companyname,
			String gstno, String contactno1, String contactno2, String faxno, String website, String email,
			String bankname, String bankadd, String accno, String ifscode, String swift, String panno, String add1, String add2,
			int countryid, int stateid, String city, String pincode, HttpServletRequest request, HttpSession session) {
		logger.info("Inside Add Brand Controller");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String image1 = "";

		if (image != null) {
			try {
				byte[] bytes = image.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "Company");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext().getRealPath("/resources/admin/images/Company/");
				File uploadfile = new File(path + File.separator + image.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						System.out.println("File " + files[i].getName() + " " + files[i].length());
					}
				}
				image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/opal/resources/admin/images/Company/" + image.getOriginalFilename();

				company = new Company(companyname, image1, gstno, contactno1, contactno2, faxno, website, email,
						bankname, bankadd, accno, ifscode, swift, panno, add1, add2, countryid, stateid, city, pincode, "y",
						id, IpAddress);
				companyDao.addCompany(company);
			} catch (Exception e) {
				company = new Company(companyname, null, gstno, contactno1, contactno2, faxno, website, email, bankname,
						bankadd, accno, ifscode, swift, panno, add1, add2, countryid, stateid, city, pincode, "y", id,
						IpAddress);
				companyDao.addCompany(company);
			}
		} else {
			company = new Company(companyname, null, gstno, contactno1, contactno2, faxno, website, email, bankname,
					bankadd, accno, ifscode, swift, panno, add1, add2, countryid, stateid, city, pincode, "y", id, IpAddress);
			companyDao.addCompany(company);
		}

		return "Success";
	}

	@RequestMapping(value = "editCompany", method = RequestMethod.POST)
	public String editCompany(@RequestParam(value = "image", required = false) MultipartFile image, int companyid,
			String companyname, String companylogo, String gstno, String contactno1, String contactno2, String faxno,
			String website, String email, String bankname, String bankadd, String accno, String ifscode, String swift, String panno,
			String add1, String add2, int countryid, int stateid, String city, String pincode,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT COMPANY *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String image1 = "";

		if (image != null) {
			try {
				byte[] bytes = image.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "Company");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext().getRealPath("/resources/admin/images/Company/");
				File uploadfile = new File(path + File.separator + image.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						System.out.println("File " + files[i].getName() + " " + files[i].length());
					}
				}
				image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/opal/resources/admin/images/Company/" + image.getOriginalFilename();

				company = new Company(companyid, companyname, image1, gstno, contactno1, contactno2, faxno, website,
						email, bankname, bankadd, accno, ifscode, swift, panno, add1, add2, countryid, stateid, city, pincode,
						id, IpAddress);
				companyDao.editCompany(company);
			} catch (Exception e) {
				company = new Company(companyid, companyname, null, gstno, contactno1, contactno2, faxno, website,
						email, bankname, bankadd, accno, ifscode, swift, panno, add1, add2, countryid, stateid, city, pincode,
						id, IpAddress);
				companyDao.editCompany(company);
			}
		} else {
			company = new Company(companyid, companyname, companylogo, gstno, contactno1, contactno2, faxno, website,
					email, bankname, bankadd, accno, ifscode, swift, panno, add1, add2, countryid, stateid, city, pincode, id,
					IpAddress);
			companyDao.editCompany(company);
		}

		return "Success";
	}

	@RequestMapping(value = "deleteCompany", method = RequestMethod.DELETE)
	public void delete(int companyid) {
		logger.info("***** DELETE COMPANY *****");
		companyDao.deleteCompany(companyid);
	}
}
