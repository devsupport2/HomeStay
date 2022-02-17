package com.ui.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.EnquiryDAO;
import com.ui.dao.FinancialYearDAO;
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


@RestController
public class EnquiryController {
	@Autowired
	EnquiryDAO enquiryDao;
	@Autowired
	FinancialYearDAO financialYearDao;	

	Enquiry enquiry;
	EnquiryProduct enquiryProduct;
	EnquiryUrl enquiryUrl;
	EnquiryFile enquiryFile;
	EnquiryStatus enquiryStatus;
	EnquiryStatusReason enquiryStatusReason;
	AllEnquiryStatus allEnquiryStatus;
	EnquiryFollowup enquiryFollowup;
	EnquiryAssign enquiryAssign;
	TaskAssign taskAssign;
	
	FollowupEmployee followupEmployee;

	private static final Logger logger = LoggerFactory.getLogger(EnquiryController.class);

	@RequestMapping(value = "/getAllOpenEnquiries", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiries(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET ENQUIRIES *****");
		List<Enquiry> enquiry = enquiryDao.getAllOpenEnquiries(session);

		for (Enquiry e : enquiry) {
			List<EnquiryProduct> ep = enquiryDao.getEnquiryProductsByEnquiryId(e.getEnquiryId());			
			e.setInvoiceGenerated("n");
			e.setReadyToInvoice("n");
			for (EnquiryProduct ep1 : ep) {
				if (ep1.getReadyToInvoice().equalsIgnoreCase("y")) {
					e.setReadyToInvoice("y");
					break;
				}
			}			
		}
		return enquiry;
	}

	@RequestMapping(value = "/getEnquiriesByStatus", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiriesByStatus(String status, HttpServletRequest request, HttpSession session) {
		logger.info("***** GET ENQUIRIES BY STATUS *****");
		List<Enquiry> enquiry = enquiryDao.getEnquiriesByStatus(status, session);

		for (Enquiry e : enquiry) {
			List<EnquiryProduct> ep = enquiryDao.getEnquiryProductsByEnquiryId(e.getEnquiryId());			
			e.setInvoiceGenerated("n");
			e.setReadyToInvoice("n");
			for (EnquiryProduct ep1 : ep) {
				if (ep1.getReadyToInvoice().equalsIgnoreCase("y")) {
					e.setReadyToInvoice("y");
					break;
				}
			}			
		}

		return enquiry;
	}

	@RequestMapping(value = "/getEnquiryDetailsById", method = RequestMethod.GET, produces = "application/json")
	public Enquiry getEnquiryDetailsById(int enquiryid, HttpServletRequest request) {
		logger.info("***** GET ENQUIRY DETAILS BY ID CONTROLLER *****");
		Enquiry enquiry = enquiryDao.getEnquiryDetailsById(enquiryid);
		
		List<EnquiryAssign> ea =  enquiryDao.getEnquiryAssignByEnquiryId(enquiryid);
		System.out.println("++++++++++++++++++++++SJJJJJJJFJJJJJJJJJJJJFDJJJJJJJJD++++++++++++++"+ea);
		enquiry.setAssignedMemberList(ea);
		return enquiry;
	}

	@RequestMapping(value = "/getEnquiriesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiriesByPage(String StartDate,String EndDate, int pagesize, int startindex, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GET ENQUIRIES BY PAGE *****");
		List<Enquiry> enquiry = enquiryDao.getEnquiriesByPage(StartDate, EndDate, pagesize, startindex, session);

		for (Enquiry e : enquiry) {
			List<EnquiryProduct> ep = enquiryDao.getEnquiryProductsByEnquiryId(e.getEnquiryId());
			// List<Invoice> i =
			// invoiceDao.getInvoiceByEnquiryId(e.getEnquiryId());
			e.setInvoiceGenerated("n");
			e.setReadyToInvoice("n");
			for (EnquiryProduct ep1 : ep) {
				if (ep1.getReadyToInvoice().equalsIgnoreCase("y")) {
					e.setReadyToInvoice("y");
					break;
				}
			}
			/*
			 * for(Invoice in : i){ if(in.getEnquiryId() == e.getEnquiryId()){
			 * e.setInvoiceGenerated("y"); break; } }
			 */
		}

		return enquiry;
	}
	
	
	
	@RequestMapping(value = "/getReferencesByDateId", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getReferencesByDateId(String StartRefDate,String EndRefDate, int RefTypeId,HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GET ENQUIRIES BY PAGE *****");
		List<Enquiry> enquiry = enquiryDao.getReferencesByDateId(StartRefDate, EndRefDate, RefTypeId,session);
			
		return enquiry;
	}

	
	
	
	

	@RequestMapping(value = "/searchEnquiry", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> searchEnquiry(String keyword, HttpServletRequest request, HttpSession session) {
		logger.info("***** SEARCH ENQUIRIES *****");
		List<Enquiry> enquiry = enquiryDao.searchEnquiry(keyword, session);

		for (Enquiry e : enquiry) {
			List<EnquiryProduct> ep = enquiryDao.getEnquiryProductsByEnquiryId(e.getEnquiryId());
			// List<Invoice> i =
			// invoiceDao.getInvoiceByEnquiryId(e.getEnquiryId());
			e.setInvoiceGenerated("n");
			e.setReadyToInvoice("n");
			for (EnquiryProduct ep1 : ep) {
				if (ep1.getReadyToInvoice().equalsIgnoreCase("y")) {
					e.setReadyToInvoice("y");
					break;
				}
			}
			/*
			 * for(Invoice in : i){ if(in.getEnquiryId() == e.getEnquiryId()){
			 * e.setInvoiceGenerated("y"); break; } }
			 */
		}

		return enquiry;
	}

	@RequestMapping(value = "addEnquiry", method = RequestMethod.POST)
	public String addEnquiry(String enquirydate, String enquiryvia, int referenceid, int clientid, int employeeid,
			String remark, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		String enquiryno = null;
		int sequence = 0;
		String currentYearCode = financialYearDao.getCurrentFinancialYearCode();
		
		//If Dealer add enquiry then set dealer's userid as reference id.
		
		if(Integer.parseInt(session.getAttribute("usertypeidadmin").toString()) == 3 && referenceid == 0){
			referenceid = id;
		}

		Enquiry enquiry = null;
		enquiry = enquiryDao.getLastEnquiryDetail();
		if (enquiry == null) {
			sequence = 1;
			enquiryno = "HSI-" + currentYearCode + "-I0001";
			enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
					remark, s, id, IpAddress);
			enquiryDao.addEnquiry(enquiry);
			int eid = enquiryDao.getLastEnquiryId();
			enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
			enquiryDao.addEnquiryStatus(enquiryStatus);
			return "Success";
		} else {
			String fc = enquiry.getEnquiryNo();
			String financialyearcode1 = fc.substring(4, 8);

			sequence = enquiryDao.getLastEnquirySequence();

			System.out.println("Last Sequence---------------------------->" + sequence);
			if (sequence == 0) {
				sequence = 1;
				enquiryno = "HSI-" + currentYearCode + "-I0001";
				enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
						remark, s, id, IpAddress);
				enquiryDao.addEnquiry(enquiry);
				int eid = enquiryDao.getLastEnquiryId();
				enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
				enquiryDao.addEnquiryStatus(enquiryStatus);
				return "Success";

			} else {
				if (currentYearCode.equals(financialyearcode1)) {
					sequence = sequence + 1;
					System.out.println("Sequence Plus---------------------------->" + sequence);
				} else {
					sequence = 1;
					System.out.println("Sequence 1---------------------------->" + sequence);
				}
				if (sequence >= 1 && sequence < 10) {
					enquiryno = "HSI-" + currentYearCode + "-I000" + Integer.toString(sequence);
				} else if (sequence >= 10 && sequence < 100) {
					enquiryno = "HSI-" + currentYearCode + "-I00" + Integer.toString(sequence);
				} else if (sequence >= 100 && sequence < 1000) {
					enquiryno = "HSI-" + currentYearCode + "-I0" + Integer.toString(sequence);
				} else if (sequence >= 1000 && sequence < 10000) {
					enquiryno = "HSI-" + currentYearCode + "-I" + Integer.toString(sequence);
					;
				}
				enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
						remark, s, id, IpAddress);
				enquiryDao.addEnquiry(enquiry);

				int eid = enquiryDao.getLastEnquiryId();
				enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
				enquiryDao.addEnquiryStatus(enquiryStatus);

				return "Success";
			}
		}
	}
	
	
	
	
	
	

	@RequestMapping(value = "addEnquiryProduct", method = RequestMethod.POST)
	public String addEnquiryProduct(int productid, String product, String readytoinvoice, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD ENQUIRY PRODUCT CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int enquiryid = enquiryDao.getLastEnquiryId();
		enquiryProduct = new EnquiryProduct(enquiryid, productid, product, readytoinvoice, id, IpAddress);
		enquiryDao.addEnquiryProduct(enquiryProduct);
		return "Success";
	}

	@RequestMapping(value = "addEnquiryUrl", method = RequestMethod.POST)
	public String addEnquiryUrl(String url, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY URL CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int enquiryid = enquiryDao.getLastEnquiryId();
		enquiryUrl = new EnquiryUrl(enquiryid, url, id, IpAddress);
		enquiryDao.addEnquiryUrl(enquiryUrl);
		return "Success";
	}

	@RequestMapping(value = "addEnquiryAssign", method = RequestMethod.POST)
	public String addEnquiryAssign(int userid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY ASSIGN *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int enquiryid = enquiryDao.getLastEnquiryId();
		enquiryAssign = new EnquiryAssign(enquiryid, userid, id, IpAddress);
		enquiryDao.addEnquiryAssign(enquiryAssign);
		return "Success";
	}

	
	

	

	
	
	
	
	@RequestMapping(value = "/addEnquiryFiles", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addEnquiryFiles(@RequestParam(value = "fileadd", required = false) MultipartFile[] fileadd,
			HttpServletRequest request, HttpSession session, String filetitle[]) {
		logger.info("********** ADD ENQUIRY FILE CONTROLLER **********");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String filepath = null;
		try {
			for (int i = 0; i < fileadd.length; i++) {
				if (fileadd[i].getOriginalFilename() != null && fileadd[i].getOriginalFilename() != "") {
					try {
						byte[] bytes = fileadd[i].getBytes();
						File dir = new File(request.getSession().getServletContext().getRealPath("")
								+ "/resources/admin/images/" + File.separator + "EnquiryFiles");
						if (!dir.exists())
							dir.mkdirs();
						String path = request.getSession().getServletContext()
								.getRealPath("/resources/admin/images/EnquiryFiles/");
						File uploadfile = new File(path + File.separator + fileadd[i].getOriginalFilename());
						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();
						File f = new File(path);
						File files[] = f.listFiles();

						for (int j = 0; j < files.length; j++) {
							if (files[j].isFile()) {
								System.out.println("File " + files[j].getName() + " " + files[j].length());
							}
						}
						// filepath = request.getScheme() + "://" +
						// request.getServerName() + ":" +
						// request.getServerPort()+"/resources/admin/images/NewsFiles/"+mainfile[i].getOriginalFilename();
						filepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/crm/resources/admin/images/EnquiryFiles/" + fileadd[i].getOriginalFilename();

						int enquiryid = enquiryDao.getLastEnquiryId();
						enquiryFile = new EnquiryFile(enquiryid, filetitle[i + 1], filepath, id, IpAddress);
						enquiryDao.addEnquiryFiles(enquiryFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}

	@RequestMapping(value = "/getEnquiryProductsByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryProduct> getEnquiryProductsByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY PRODUCTS BY ENQUIRY ID *****");
		List<EnquiryProduct> enquiryProduct = enquiryDao.getEnquiryProductsByEnquiryId(enquiryid);
		return enquiryProduct;
	}

	@RequestMapping(value = "/getEnquiryUrlsByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryUrl> getEnquiryUrlsByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY URLS BY ENQUIRY ID *****");
		List<EnquiryUrl> enquiryUrl = enquiryDao.getEnquiryUrlsByEnquiryId(enquiryid);
		return enquiryUrl;
	}

	@RequestMapping(value = "/getEnquiryAssignByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryAssign> getEnquiryAssignByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY ASSIGN BY ENQUIRY ID *****");
		List<EnquiryAssign> enquiryAssign = enquiryDao.getEnquiryAssignByEnquiryId(enquiryid);
		return enquiryAssign;
	}

	@RequestMapping(value = "/getEnquiryFilesByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryFile> getEnquiryFilesByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY FILE BY ENQUIRY ID *****");
		List<EnquiryFile> enquiryFile = enquiryDao.getEnquiryFilesByEnquiryId(enquiryid);
		return enquiryFile;
	}

	@RequestMapping(value = "/getEnquiryLogByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryStatus> getEnquiryLogByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY LOG BY ENQUIRY ID *****");
		List<EnquiryStatus> enquiryStatus = enquiryDao.getEnquiryLogByEnquiryId(enquiryid);
		return enquiryStatus;
	}

	@RequestMapping(value = "/getEnquiryFollowupByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryFollowup> getEnquiryFollowupByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY FOLLOW-UP BY ENQUIRY ID *****");
		List<EnquiryFollowup> enquiryFollowup = enquiryDao.getEnquiryFollowupByEnquiryId(enquiryid);
		return enquiryFollowup;
	}

	@RequestMapping(value = "deleteEnquiry", method = RequestMethod.DELETE)
	public void delete(int enquiryid) {
		logger.info("***** DELETE ENQUIRY CONTROLLER *****");
		enquiryDao.deleteEnquiry(enquiryid);
	}

	@RequestMapping(value = "/deleteEnquiryProduct", method = RequestMethod.DELETE)
	public void deleteEnquiryProduct(int enquiryid) {
		logger.info("***** DELETE ENQUIRY PRODUCTS CONTROLLER *****");
		enquiryDao.deleteEnquiryProduct(enquiryid);
	}

	@RequestMapping(value = "/deleteEnquiryUrl", method = RequestMethod.DELETE)
	public void deleteEnquiryUrl(int enquiryid) {
		logger.info("***** DELETE ENQUIRY URLS CONTROLLER *****");
		enquiryDao.deleteEnquiryUrl(enquiryid);
	}

	@RequestMapping(value = "/deleteEnquiryFile", method = RequestMethod.DELETE)
	public void deleteEnquiryFile(int enquiryid) {
		logger.info("***** DELETE ENQUIRY FILES CONTROLLER *****");
		enquiryDao.deleteEnquiryFile(enquiryid);
	}

	@RequestMapping(value = "editEnquiry", method = RequestMethod.POST)
	public String editEnquiry(int enquiryid, String enquirydate, String enquiryvia, int referenceid, int clientid,
			int employeeid, String remark, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		enquiry = new Enquiry(enquiryid, referenceid, clientid, employeeid, enquirydate, enquiryvia, remark, IpAddress);
		enquiryDao.editEnquiry(enquiry);

		enquiryStatus = new EnquiryStatus(enquiryid, 0, 2, "Y", id, IpAddress);
		enquiryDao.addEnquiryStatus(enquiryStatus);

		return "Success";
	}

	@RequestMapping(value = "/editNewFileList", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String editNewFileList(@RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletRequest request, HttpSession session, int enquiryid, String filetitle[]) {
		logger.info("********** EDIT ENQUIRY FILE CONTROLLER **********");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String filepath = null;
		try {
			for (int i = 0; i < file.length; i++) {
				if (file[i].getOriginalFilename() != null && file[i].getOriginalFilename() != "") {
					try {
						byte[] bytes = file[i].getBytes();
						File dir = new File(request.getSession().getServletContext().getRealPath("")
								+ "/resources/admin/images/" + File.separator + "EnquiryFiles");
						if (!dir.exists())
							dir.mkdirs();
						String path = request.getSession().getServletContext()
								.getRealPath("/resources/admin/images/EnquiryFiles/");
						File uploadfile = new File(path + File.separator + file[i].getOriginalFilename());
						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();
						File f = new File(path);
						File files[] = f.listFiles();

						for (int j = 0; j < files.length; j++) {
							if (files[j].isFile()) {
								System.out.println("File " + files[j].getName() + " " + files[j].length());
							}
						}
						
						filepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/crm/resources/admin/images/EnquiryFiles/" + file[i].getOriginalFilename();

						enquiryFile = new EnquiryFile(enquiryid, filetitle[i + 1], filepath, id, IpAddress);
						enquiryDao.addEnquiryFiles(enquiryFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}

	@RequestMapping(value = "editFileList", method = RequestMethod.POST)
	public String editFileList(int enquiryid, String filetitle, String filepath, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT ENQUIRY FILE CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		enquiryFile = new EnquiryFile(enquiryid, filetitle, filepath, id, IpAddress);
		enquiryDao.addEnquiryFiles(enquiryFile);
		return "Success";
	}

	@RequestMapping(value = "editEnquiryProduct", method = RequestMethod.POST)
	public String editEnquiryProduct(int enquiryid, int productid, String product, String readytoinvoice,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT ENQUIRY PRODUCT CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		enquiryProduct = new EnquiryProduct(enquiryid, productid, product, readytoinvoice, id, IpAddress);
		enquiryDao.addEnquiryProduct(enquiryProduct);
		return "Success";
	}

	@RequestMapping(value = "editEnquiryUrl", method = RequestMethod.POST)
	public String editEnquiryUrl(int enquiryid, String url, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT ENQUIRY URL CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		enquiryUrl = new EnquiryUrl(enquiryid, url, id, IpAddress);
		enquiryDao.addEnquiryUrl(enquiryUrl);
		return "Success";
	}

	@RequestMapping(value = "editEnquiryAssign", method = RequestMethod.POST)
	public String editEnquiryAssign(int enquiryid, int userid, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT ENQUIRY ASSIGN *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		enquiryAssign = new EnquiryAssign(enquiryid, userid, id, IpAddress);
		enquiryDao.addEnquiryAssign(enquiryAssign);
		return "Success";
	}

	@RequestMapping(value = "/deleteEnquiryAssignRow", method = RequestMethod.DELETE)
	public void deleteEnquiryAssignRow(int id) {
		logger.info("***** DELETE ENQUIRY ASSIGN ROW *****");
		enquiryDao.deleteEnquiryAssignRow(id);
	}

	@RequestMapping(value = "addEnquiryLog", method = RequestMethod.POST)
	public String addEnquiryLog(int enquiryid, String enquirylog, String logdatetime, String notifylogviaemail,
          String notifylogviasms, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD ENQUIRY LOG *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		enquirylog = enquirylog.replace("$", "&");
		enquirylog = enquirylog.replace("~", "#");
		enquirylog = enquirylog.replace("!", "%");

		enquiryStatus = new EnquiryStatus(enquiryid, 0, enquirylog, logdatetime, "y", id, IpAddress);
		enquiryDao.addEnquiryLog(enquiryStatus);
		enquiryDao.openEnquiry(enquiryid);
		session.setAttribute("notifylogviaemail", notifylogviaemail);
        session.setAttribute("notifylogviasms", notifylogviasms);
		return "Success";
	}
	
	@RequestMapping(value = "addLogMemberAssign", method = RequestMethod.POST)
    public String addLogMemberAssign(int enquiryid, int userid, HttpServletRequest request,
            HttpSession session) {
        logger.info("***** ADD ENQUIRY LOG MEMBER *****");
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        EnquiryLogMember elm = new EnquiryLogMember();
        int logid = enquiryDao.getLastEnquiryStatusId();
        elm.setEnquiryLogId(logid);
        elm.setEnquiryId(enquiryid);
        elm.setMemberId(userid);
        elm.setCreatedBy(id);
        elm.setIpAddress(IpAddress);
        enquiryDao.addLogMemberAssign(elm);
        return "Success";
    }
	
	@RequestMapping(value = "addFollowupMemberAssign", method = RequestMethod.POST)
    public String addFollowupMemberAssign(int enquiryid, int userid, HttpServletRequest request,
            HttpSession session) {
        logger.info("***** ADD ENQUIRY FOLLOWUP MEMBER *****");
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        EnquiryFollowupMember elm = new EnquiryFollowupMember();
        int followupid = enquiryDao.getLastEnquiryFollowupId();
        elm.setEnquiryFollowupId(followupid);
        elm.setEnquiryId(enquiryid);
        elm.setMemberId(userid);
        elm.setCreatedBy(id);
        elm.setIpAddress(IpAddress);
        enquiryDao.addFollowupMemberAssign(elm);
        return "Success";
    }

	@RequestMapping(value = "addEnquiryFollowup", method = RequestMethod.POST)
	public String addEnquiryFollowup(int enquiryid, String followupremark, String followupdatetime,
          String notifyfollowupviaemail, String notifyfollowupviasms,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY FOLLOW-UP *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		followupremark = followupremark.replace("$", "&");
		followupremark = followupremark.replace("~", "#");
		followupremark = followupremark.replace("!", "%");

		enquiryFollowup = new EnquiryFollowup(enquiryid, followupdatetime, followupremark, "y", id, IpAddress);
		enquiryDao.addEnquiryFollowup(enquiryFollowup);
		session.setAttribute("notifyfollow	@GetMapping(value = \"/getCategoryById\")\n" + 
				"	public Category getCategoryById(int categoryid, HttpServletRequest request, HttpServletResponse response) {\n" + 
				"		logger.info(\"*************************** GET ALL CATEGORY BY CATEGORY ID CONTROLLER***************************\");\n" + 
				"		return categoryDao.getCategoryById(categoryid);\n" + 
				"	}\n" + 
				"	upviaemail", notifyfollowupviaemail);
        session.setAttribute("notifyfollowupviasms", notifyfollowupviasms);
		return "Success";
	}

	@RequestMapping(value = "/getAllEnquiryProductName", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryProduct> getAllEnquiryProductName(HttpServletRequest request) {
		logger.info("***** ALL ENQUIRY PRODUCT NAME *****");
		List<EnquiryProduct> enquiryProduct = enquiryDao.getAllEnquiryProductName();
		return enquiryProduct;
	}
	
	
	

	
	

	@RequestMapping(value = "addStatusReason", method = RequestMethod.POST)
	public String addStatusReason(String reason, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD STATUS REASON *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		reason = reason.replace("$", "&");
		reason = reason.replace("~", "#");
		reason = reason.replace("!", "%");

		enquiryStatusReason = new EnquiryStatusReason(reason, "y", id, IpAddress);
		enquiryDao.addStatusReason(enquiryStatusReason);
		return "Success";
	}

	@RequestMapping(value = "/getStatusReason", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryStatusReason> getStatusReason(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET STATUS REASON *****");
		List<EnquiryStatusReason> enquiryStatusReason = enquiryDao.getStatusReason();
		return enquiryStatusReason;
	}

	@RequestMapping(value = "changeEnquiryStatus", method = RequestMethod.POST)
	public String changeEnquiryStatus(int enquiryid, String enquirystatus, int statusreason, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** UPDATE ENQUIRY STATUS *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		allEnquiryStatus = new AllEnquiryStatus(enquiryid, enquirystatus, statusreason, "y", id, IpAddress);
		enquiryDao.insertEnquiryStatus(allEnquiryStatus);

		enquiry = new Enquiry(enquiryid, enquirystatus, statusreason);
		enquiryDao.changeEnquiryStatus(enquiry);

		enquiryStatus = new EnquiryStatus(enquiryid, 0, 8, "Y", id, IpAddress);
		enquiryDao.addEnquiryStatus(enquiryStatus);
		return "Success";
	}
	
	@RequestMapping(value = "addEmpFollowup", method = RequestMethod.POST)
	public String addEmpFollowup(int userid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD EMPLOYEE IN FOLLOWUP *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int followupid = enquiryDao.getLastFollowupId();
		followupEmployee = new FollowupEmployee(followupid, userid, id, IpAddress);
		enquiryDao.addEmpFollowup(followupEmployee);
		return "Success";
	}

	@RequestMapping(value = "/getTodayFollowupEnquiries", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getTodayFollowupEnquiries(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET TODAY FOLLOWUP ENQUIRIES *****");
		List<Enquiry> enquiry = enquiryDao.getTodayFollowupEnquiries(session);
		return enquiry;
	}

	@RequestMapping(value = "/getFollowupEnquiriesByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getFollowupEnquiriesByDate(String fromdate, String todate, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GETFOLLOWUP ENQUIRIES BY DATE *****");
		List<Enquiry> enquiry = enquiryDao.getFollowupEnquiriesByDate(session, fromdate, todate);
		return enquiry;
	}

	@RequestMapping(value = "markCompleteFollowup", method = RequestMethod.POST)
	public String markCompleteFollowup(int followupid, String status, HttpServletRequest request, HttpSession session) {
		logger.info("***** MARK FOLLOWUP AS COMPLETE *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		enquiryFollowup = new EnquiryFollowup(followupid, status, id, IpAddress);
		result = enquiryDao.markCompleteFollowup(enquiryFollowup);
		return result;
	}

	@RequestMapping(value = "deleteFollowup", method = RequestMethod.DELETE)
	public void deleteFollowup(int followupid) {
		logger.info("***** DELETE FOLLOWUP *****");
		enquiryDao.deleteFollowup(followupid);
	}

	@RequestMapping(value = "/getEnquiryReadyToIncodeProductByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryProduct> getEnquiryReadyToIncodeProductByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY READY TO INVOICE PRODUCT BY ENQUIRY ID *****");
		List<EnquiryProduct> enquiryProduct = enquiryDao.getEnquiryReadyToIncodeProductByEnquiryId(enquiryid);
		return enquiryProduct;
	}

	
	
	
	
	@RequestMapping(value = "/getRefenceByRefId", method = RequestMethod.GET, produces = "application/json")
	public List<UserType> getRefenceByRefId(int RefId, HttpServletRequest request) {
		logger.info("***** ENQUIRY READY TO INVOICE PRODUCT BY ENQUIRY ID *****"+RefId);
		List<UserType> RerefenceType = enquiryDao.getRefenceByRefId(RefId);
		return RerefenceType;
	}
	
	
	
	@RequestMapping(value = "/getTodayFiveFollowupForDashboard", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getTodayFiveFollowupForDashboard(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET TODAY FIVE FOLLOWUP FOR DASHBOARD  *****");
		List<Enquiry> enquiry = enquiryDao.getTodayFiveFollowupForDashboard(session);
		return enquiry;
	}
	
	
	@RequestMapping(value = "addTask", method = RequestMethod.POST)
	public int addTask(@RequestBody Task task, HttpServletRequest request, HttpSession session) {
		logger.info("*************************** ADD PRODUCT CONTROLLER *******************************");

		int taskId;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		logger.info("=============================================================" + id);
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		String s = "y";

		task.setCreatedBy(id);
		task.setIpAddress(ip);
		task.setStatus(s);

		//if (task.getTaskId() == 0) {
			taskId = enquiryDao.addTask(task);
		//} else {
			logger.info("*************************** UPDATE PRODUCT CONTROLLER *******************************");
		//	taskId = enquiryDao.editTask(task);
		//}

		return taskId;

	}
	
	

	@RequestMapping(value = "addTaskAssign", method = RequestMethod.POST)
	public String addTaskAssign(int userid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY ASSIGN *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int taskid = enquiryDao.getLastTaskId();
		taskAssign = new TaskAssign(taskid, userid, id, IpAddress);
		enquiryDao.addTaskAssign(taskAssign);
		return "Success";
	}
	
	
	@RequestMapping(value = "/getAllTask", method = RequestMethod.GET, produces = "application/json")
	public List<Task> getAllTask(HttpServletRequest request) {
		logger.info("***** ALL ENQUIRY PRODUCT NAME *****");
		List<Task> task = enquiryDao.getAllTask();
		return task;
	}
	
	/*@RequestMapping(value = "importEnquiry", method = RequestMethod.POST)
	public int importEnquiry(@RequestParam(value = "enquiryfile", required = false) MultipartFile enquiryfile,
			HttpServletRequest request, HttpSession session) {

		logger.info("***** IMPORT ENQUIRY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";

		int count = 0;
		int i = 1;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(enquiryfile.getInputStream());
			HSSFSheet worksheet = workbook.getSheetAt(0);
			
		
			
while (i <= worksheet.getLastRowNum()) {
				//int sequence = 0;
				String enquirydate = null;
				String enquiryvia = null;
				int  referenceid = 0;
				int  referenceTypeid =0;
				int productId=0;
				String url=null;
				int clientid = 0;
				int employeeid = 0;
				String remarks=null;
				String status=null;
				String IpAddress1 = null;
				
				//String enquiryvia = null;
				//String remark = null;
				//int occupationid = 0;
				//int designationid = 0;
				//String chooseoption = null;
				//int budgetid = 0;
				//String features = null;
				//String consideringproject = null;
				//String expectingtime = null;
				//String s = null;
				//int id = 0;
				//String IpAddress = null;
				

				count = count + 1;
				HSSFRow row = worksheet.getRow(i++);

				try {
					if (row.getCell(2).getStringCellValue() != null) {
						enquirydate = row.getCell(2).getStringCellValue();
					} else {
						enquirydate = "";
					}
				} catch (Exception e) {
					enquirydate = "";
				}

				try {
					if (row.getCell(3).getStringCellValue() != null) {
						enquiryvia = row.getCell(3).getStringCellValue();
					} else {
						enquiryvia = "";
					}
				} catch (Exception e) {
					enquiryvia = "";
				}

				try {
					if (row.getCell(4).getNumericCellValue()!= 0) {
						referenceid = (int) row.getCell(4).getNumericCellValue();
					} else {
						referenceid = 0;
					}
				} catch (Exception e) {
					referenceid = 0;
				}

				try {
					if (row.getCell(5).getNumericCellValue() != null) {
						referenceTypeid = row.getCell(5).getNumericCellValue();
					} else {
						referenceTypeid = 0;
					}
				} catch (Exception e) {
					referenceTypeid = 0;
				}

				try {
					if (row.getCell(6).getStringCellValue() != null) {
					  enquirydate = row.getCell(6).getStringCellValue();
					} else {
					  enquirydate = null;
					}
				} catch (Exception e) {
				  enquirydate = null;
				}

				try {
					if (row.getCell(7).getStringCellValue() != null) {
					  enquiryvia = row.getCell(7).getStringCellValue();
					} else {
					  enquiryvia = null;
					}
				} catch (Exception e) {
				  enquiryvia = null;
				}

				try {
					if (row.getCell(8).getStringCellValue() != null) {
					  remark = row.getCell(8).getStringCellValue();
					} else {
					  remark = null;
					}
				} catch (Exception e) {
				  remark = null;
				}

				try {
					if (row.getCell(9).getNumericCellValue() != null) {
					  occupationid = row.getCell(9).getNumericCellValue();
					} else {
					  occupationid = 0;
					}
				} catch (Exception e) {
				  occupationid = 0;
				}

				try {
					if (row.getCell(10).getNumericCellValue() != null) {
					  designationid = row.getCell(10).getNumericCellValue();
					} else {
					  designationid = 0;
					}
				} catch (Exception e) {
				  designationid = 0;
				}
				try {
					if (row.getCell(13).getStringCellValue() != null) {
					  chooseoption = row.getCell(13).getStringCellValue();
					} else {
					  chooseoption = null;
					}
				} catch (Exception e) {
				  chooseoption = null;
				}

				try {
					if (row.getCell(15).getNumericCellValue() != null) {
					  budgetid = row.getCell(15).getNumericCellValue();
					} else {
					  budgetid = 0;
					}
				} catch (Exception e) {
				  budgetid = 0;
				}

				try {
					if (row.getCell(16).getStringCellValue() != null) {
					  features = row.getCell(16).getStringCellValue();
					} else {
					  features = null;
					}
				} catch (Exception e) {
				  features = null;
				}
				
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    consideringproject = row.getCell(16).getStringCellValue();
                  } else {
                    consideringproject = null;
                  }
              } catch (Exception e) {
                consideringproject = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    expectingtime = row.getCell(16).getStringCellValue();
                  } else {
                    expectingtime = null;
                  }
              } catch (Exception e) {
                expectingtime = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    IpAddress1 = row.getCell(16).getStringCellValue();
                  } else {
                    IpAddress1 = null;
                  }
              } catch (Exception e) {
                IpAddress1 = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    s = row.getCell(16).getStringCellValue();
                  } else {
                    s = null;
                  }
              } catch (Exception e) {
                s = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    ip = row.getCell(16).getStringCellValue();
                  } else {
                    ip = null;
                  }
              } catch (Exception e) {
                ip = null;
              }

				Enquiry enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
			            remark, occupationid, designationid, chooseoption, budgetid, features, consideringproject, expectingtime, s, id, IpAddress1);
				System.out.println("======== Hakuna matata ==================="+user.getFirstName()+" ================= "+user.getMobileNumber());    
				
				enquiryDao.addEnquiry(enquiry);

				clientid = enquiryDao.getLastEnquiryId();

				try {} catch (Exception e) {}

				try {} catch (Exception e) {}

				try {} catch (Exception e) {
					categoryid = 0;
				}

				try {} catch (Exception e) {
					subcategoryid = 0;
				}

				try {} catch (Exception e) {}

				
					} else {}
				}

				

				

				enquiryDao.addEnquiryProperty(ep);

				enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
				enquiryDao.addEnquiryStatus(enquiryStatus);
			}

			workbook.close();

		} catch (IOException e) {}

		return count;
	}*/
}
