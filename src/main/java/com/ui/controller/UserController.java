package com.ui.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.UserDAO;
import com.ui.model.ClientCategory;
import com.ui.model.DealerProduct;
import com.ui.model.EmployeeActivities;
import com.ui.model.User;
import com.ui.model.UserType;

@RestController
public class UserController {
	@Autowired
	UserDAO userDAO;

	User user;
	UserType userType;
	DealerProduct dealerProduct;
	EmployeeActivities employeeActivities;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/getUserTypes", method = RequestMethod.GET, produces = "application/json")
	public List<UserType> getUserTypes(HttpServletRequest request) {
		logger.info("***** GET USER TYPE *****");
		List<UserType> d = userDAO.getUserTypes();
		return d;
	}
	
	@RequestMapping(value = "/getUsersById", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUsersById(int id, HttpServletRequest request) {
        logger.info("***** GET USERS BY TYPE ID *****");
        List<User> user = userDAO.getUsersById(id);
        return user;
    }

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUsers(HttpServletRequest request) {
		logger.info("***** GET USERS *****");
		List<User> user = userDAO.getAllUsers();
		return user;
	}

	@RequestMapping(value = "/getUsersByPage", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUsersByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET USER BY PAGE *****");
		List<User> user = userDAO.getAllUsersByPage(pagesize, startindex);
		return user;
	}
	
	
	@RequestMapping(value = "/getEmpActivityByDate", method = RequestMethod.GET, produces = "application/json")
    public List<EmployeeActivities> getEmpActivityByDate(String fromdate, String todate, HttpServletRequest request) {
        logger.info("***** GET EMPLOYEE ACTIVITIES BY DATE *****");
        List<EmployeeActivities> ea = userDAO.getEmpActivityByDate(fromdate, todate);
        return ea;
    }
	
	@RequestMapping(value = "/getEmpActivityByDateAndCode", method = RequestMethod.GET, produces = "application/json")
    public List<EmployeeActivities> getEmpActivityByDateAndCode(int code, String fromdate, String todate, HttpServletRequest request) {
        logger.info("***** GET EMPLOYEE ACTIVITIES BY DATE AND CODE*****");
        List<EmployeeActivities> ea = userDAO.getEmpActivityByDateAndCode(code, fromdate, todate);
        return ea;
    }
	

	@RequestMapping(value = "/searchUsers", method = RequestMethod.GET, produces = "application/json")
	public List<User> searchUsers(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH USERS *****");
		List<User> user = userDAO.searchUsers(keyword);
		return user;
	}

	@RequestMapping(value = "addUserType", method = RequestMethod.POST)
	public String addUserType(String usertypename, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD USER TYPE *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		userType = new UserType(usertypename, s, id, IpAddress);
		userDAO.addUserType(userType);
		return "Success";
	}

	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public String addUser(@RequestParam(value = "profilepicture", required = false) MultipartFile profilepicture,
			String companyname, String firstname, String middlename, String lastname, int usertypename, String gender,
			String dateofbirth, String aadharnumber, String passportnumber, String pannumber, String address1,
			String address2, String address3, int statename, String cityname, String pincode, String mobilenumber,
			String landlinenumber, String email, String password, int code, float hourlywages, float overtimewages,int clientcategory,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD USER CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		int checkEmail = 0;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		String image1 = "";
		if (dateofbirth == "") {
			dateofbirth = null;
		}
		if (gender.equals("")) {
			gender = null;
		}
		if(companyname.equals("") || companyname.equalsIgnoreCase("undefined"))
		{
			companyname = null;
		}
		if(!email.isEmpty()){		
		  checkEmail = userDAO.isEmailUnique(email);
		}
		if (checkEmail == 0) {
			try {
				if (profilepicture != null) {
					try {
						byte[] bytes = profilepicture.getBytes();
						File dir = new File(request.getSession().getServletContext().getRealPath("")
								+ "/resources/admin/images/" + File.separator + "user");
						if (!dir.exists())
							dir.mkdirs();
						String path = request.getSession().getServletContext()
								.getRealPath("/resources/admin/images/user/");
						File uploadfile = new File(path + File.separator + profilepicture.getOriginalFilename());
						int height = 480, width = 700;
						ByteArrayInputStream in = new ByteArrayInputStream(bytes);
						try {
							BufferedImage img = ImageIO.read(in);
							int original_width = img.getWidth();
							int original_height = img.getHeight();
							int bound_width = 700;
							int bound_height = 480;
							if (original_height / bound_height > original_width / bound_width) {
								bound_width = (int) (bound_height * original_width / original_height);
							} else {
								bound_height = (int) (bound_width * original_height / original_width);
							}
							Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);
							BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
							Graphics2D drawer = imageBuff.createGraphics();
							drawer.setBackground(Color.WHITE);
							drawer.clearRect(0, 0, width, height);
							imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
									(height - bound_height) / 2, new Color(0, 0, 0), null);
							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							ImageIO.write(imageBuff, "jpg", buffer);
							bytes = buffer.toByteArray();
						} catch (IOException e) {

						}
						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();
						image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/everest/resources/admin/images/user/" + profilepicture.getOriginalFilename();

					} catch (Exception e) {
						e.printStackTrace();
						user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
								passportnumber, pannumber, image1, address1, address2, address3, statename, cityname,
								pincode, mobilenumber, landlinenumber, email, password, usertypename, code, hourlywages,
								overtimewages, clientcategory,s, id, IpAddress);
						userDAO.addUser(user);
						return "";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
						passportnumber, pannumber, image1, address1, address2, address3, statename, cityname, pincode,
						mobilenumber, landlinenumber, email, password, usertypename, code, hourlywages, overtimewages,clientcategory,
						s, id, IpAddress);
				userDAO.addUser(user);
				return "";
			}

			user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
					passportnumber, pannumber, image1, address1, address2, address3, statename, cityname, pincode,
					mobilenumber, landlinenumber, email, password, usertypename, code, hourlywages, overtimewages,clientcategory, s,
					id, IpAddress);
			userDAO.addUser(user);

			return "Success";

		} else {
			return "Data not saved! Email already exists!";
		}
	}

	@RequestMapping(value = "editUser", method = RequestMethod.POST)
	public String editUser(@RequestParam(value = "profilepicture", required = false) MultipartFile profilepicture,
			int userid, String companyname, String firstname, String middlename, String lastname, int usertypename,
			String gender, String dateofbirth, String aadharnumber, String passportnumber, String pannumber,
			String address1, String address2, String address3, int statename, String cityname, String pincode,
			String mobilenumber, String landlinenumber, String email, String password, String profilepicture1, int code, float hourlywages, float overtimewages,int clientcategory,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT USER CONTROLLER *****");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String image1 = "";
		if (dateofbirth == "") {
			dateofbirth = null;
		}

		int checkEmail = 0;

		if(!email.isEmpty()){
		  checkEmail = userDAO.isEmailUniqueWithId(email, userid);
		}
		if (checkEmail == 0) {

			try {
				if (profilepicture != null) {
					try {
						byte[] bytes = profilepicture.getBytes();
						File dir = new File(request.getSession().getServletContext().getRealPath("")
								+ "/resources/admin/images/" + File.separator + "user");
						if (!dir.exists())
							dir.mkdirs();
						String path = request.getSession().getServletContext()
								.getRealPath("/resources/admin/images/user/");
						File uploadfile = new File(path + File.separator + profilepicture.getOriginalFilename());
						int height = 480, width = 700;
						ByteArrayInputStream in = new ByteArrayInputStream(bytes);
						try {
							BufferedImage img = ImageIO.read(in);
							int original_width = img.getWidth();
							int original_height = img.getHeight();
							int bound_width = 700;
							int bound_height = 480;
							if (original_height / bound_height > original_width / bound_width) {
								bound_width = (int) (bound_height * original_width / original_height);
							} else {
								bound_height = (int) (bound_width * original_height / original_width);
							}
							Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);
							BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
							Graphics2D drawer = imageBuff.createGraphics();
							drawer.setBackground(Color.WHITE);
							drawer.clearRect(0, 0, width, height);
							imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
									(height - bound_height) / 2, new Color(0, 0, 0), null);
							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							ImageIO.write(imageBuff, "jpg", buffer);
							bytes = buffer.toByteArray();
						} catch (IOException e) {

						}
						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();
						image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/everest/resources/admin/images/user/" + profilepicture.getOriginalFilename();

						user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth,
								aadharnumber, passportnumber, pannumber, image1, address1, address2, address3,
								statename, cityname, pincode, mobilenumber, landlinenumber, email, password, code, hourlywages, overtimewages,
								usertypename,clientcategory, IpAddress);
						userDAO.editUser(user);
						return "Success";
					} catch (Exception e) {
						e.printStackTrace();

						user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth,
								aadharnumber, passportnumber, pannumber, profilepicture1, address1, address2, address3,
								statename, cityname, pincode, mobilenumber, landlinenumber, email, password, code, hourlywages, overtimewages,
								usertypename, clientcategory,IpAddress);
						userDAO.editUser(user);
						return "Success";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
						passportnumber, pannumber, profilepicture1, address1, address2, address3, statename, cityname,
						pincode, mobilenumber, landlinenumber, email, password, code, hourlywages, overtimewages, usertypename,clientcategory, IpAddress);
				userDAO.editUser(user);
				return "Success";
			}
			user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
					passportnumber, pannumber, profilepicture1, address1, address2, address3, statename, cityname,
					pincode, mobilenumber, landlinenumber, email, password, code, hourlywages, overtimewages, usertypename,clientcategory, IpAddress);
			userDAO.editUser(user);
			return "Success";
		} else {
			return "Data not updated! Email already exists!";
		}

	}

	@RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
	public void delete(int userid) {
		logger.info("Inside Delete User Controller...");
		userDAO.deleteUser(userid);
	}

	@RequestMapping(value = "/getUserDetailById", method = RequestMethod.GET, produces = "application/json")
	public User getUserDetailById(int userid, HttpServletRequest request) {
		logger.info("***** GET USER DETAIL BY ID *****");
		User user = userDAO.getUserDetailById(userid);
		return user;
	}

	@RequestMapping(value = "/getUserName", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserName(HttpServletRequest request) {
		logger.info("***** GET USER NAME *****");
		List<User> user = userDAO.getUserName();
		return user;
	}

	@RequestMapping(value = "/getSuppliers", method = RequestMethod.GET, produces = "application/json")
	public List<User> getSuppliers(HttpServletRequest request) {
		logger.info("***** GET SUPPLIERS *****");
		List<User> user = userDAO.getSuppliers();
		return user;
	}

	@RequestMapping(value = "/getClientAndProspectName", method = RequestMethod.GET, produces = "application/json")
	public List<User> getClientAndProspectName(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET CLIENT AND PROSPECT NAME *****");
		List<User> user = userDAO.getClientAndProspectName(session);
		return user;
	}

	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET, produces = "application/json")
	public List<User> getEmployees(HttpServletRequest request) {
		logger.info("***** GET EMPLOYEE *****");
		List<User> user = userDAO.getAllEmployees();
		return user;
	}

	@RequestMapping(value = "/getUserNameByUserType", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserNameByUserType(int usertypeid, HttpServletRequest request) {
		logger.info("***** GET USER BY USER TYPE ID *****");
		List<User> user = userDAO.getUserByUserTypeId(usertypeid);
		return user;
	}

	@RequestMapping(value = "addDealerProduct", method = RequestMethod.POST)
	public String addDealerProduct(int productid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD DEALER PRODUCT *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int userid = userDAO.getLastUserId();
		dealerProduct = new DealerProduct(userid, productid, id, IpAddress);
		userDAO.addDealerProduct(dealerProduct);
		return "Success";
	}

	@RequestMapping(value = "/getDealerProductById", method = RequestMethod.GET, produces = "application/json")
	public List<DealerProduct> getDealerProductById(int userid, HttpServletRequest request) {
		logger.info("***** GET DEALER PRODUCT BY ID *****");
		List<DealerProduct> dealerProduct = userDAO.getDealerProductById(userid);
		return dealerProduct;
	}

	@RequestMapping(value = "editDealerProduct", method = RequestMethod.POST)
	public String editDealerProduct(int userid, int productid, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT DEALER PRODUCT *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		dealerProduct = new DealerProduct(userid, productid, id, IpAddress);
		userDAO.addDealerProduct(dealerProduct);
		return "Success";
	}

	@RequestMapping(value = "deleteDealerProduct", method = RequestMethod.DELETE)
	public void deleteDealerProduct(int dealerproductid) {
		logger.info("***** DELETE DEALER PRODUCT *****");
		userDAO.deleteDealerProduct(dealerproductid);
	}
	
	
	
	@RequestMapping(value = "importEmployeeActivities", method = RequestMethod.POST)
    public int importEmployeeActivities(@RequestParam(value = "employeefile", required = false) MultipartFile employeefile,
            HttpServletRequest request, HttpSession session) {

        logger.info("***** IMPORT Employee Activities *****");
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String s = "y";

        int count = 0;
        int i = 1;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(employeefile.getInputStream());
            HSSFSheet worksheet = workbook.getSheetAt(0);

            while (i <= worksheet.getLastRowNum()) {
                String empl = null;
                String emphworked = null;
                String empovertime = null;
                int employeecode = 0;
                String date = null;
                float hoursworked = 0;
                float thoursworked = 0;
                float overtime = 0;
                String empstatus = null;
                float overtimesalary = 0;
                float hourlywages = 0;
                float regularworked = 0;
                float overtimewages = 0;
                float mainovertime = 0;
                float regularsalary = 0;

                count = count + 1;
                HSSFRow row = worksheet.getRow(i++);
                
                /*try {
                    if (row.getCell(1).getNumericCellValue() != 0) {
                      empdate = row.getCell(1).getNumericCellValue();
                      date = Double.toString(empdate);
                      System.out.println(date+"------------date");
                    } else {
                      date = null;
                    }
                } catch (Exception e) {
                  date = null;
                }*/
                
                try {
                  if (row.getCell(1) != null) {
                      DataFormatter formatter = new DataFormatter();
                      date = formatter.formatCellValue(row.getCell(1));
                  } else {
                      date = null;
                  }
                } catch (Exception e) {
                    date = null;
                }
                
                try {
                    if (row.getCell(2).getNumericCellValue() != 0) {
                        DataFormatter formatter = new DataFormatter();
                        emphworked= formatter.formatCellValue(row.getCell(2));
                        hoursworked = Float.valueOf(emphworked);
                        /*hoursworked = Integer.parseInt(emphworked);*/
                    } else {
                      hoursworked = 0;
                    }
                } catch (Exception e) {
                  hoursworked = 0;
                }

                try {
                    if (row.getCell(3).getNumericCellValue() != 0) {
                        DataFormatter formatter = new DataFormatter();
                        empovertime= formatter.formatCellValue(row.getCell(3));
                        overtime = Float.valueOf(empovertime);
                        
                    } else {
                      overtime = 0;
                    }
                } catch (Exception e) {
                  overtime = 0;
                }
                
                try {
                    if (row.getCell(4).getStringCellValue() != null) {
                      empstatus = row.getCell(4).getStringCellValue();
                    } else {
                      empstatus = null;
                    }
                } catch (Exception e) {
                  empstatus = null;
                }

                try {
                    if (row.getCell(0).getNumericCellValue() != 0) {
                        DataFormatter formatter = new DataFormatter();
                        empl = formatter.formatCellValue(row.getCell(0));
                        employeecode = Integer.parseInt(empl);
                    } else {
                      employeecode = 0;
                    }
                } catch (Exception e) {
                  employeecode = 0;
                }
                
                
                if(hoursworked !=0){
                  
                  double doubleNum = hoursworked;
                  doubleNum =Double.parseDouble(new DecimalFormat("##.####").format(doubleNum));
                  String doubleString = String.valueOf(doubleNum);
                  int indexDecimal = doubleString.indexOf(".");
                  String thrs = doubleString.substring(0, indexDecimal);
                  String tdec = doubleString.substring(indexDecimal);
                  
                  float dectflot = Float.valueOf(tdec);
                  float hrstflot = Float.valueOf(thrs);
                  
                  float maindec = dectflot * 100 / 60;
                  thoursworked = hrstflot + maindec;
                  
                }
                
                
                if(overtime !=0){
                  
                  double doubleNumber = overtime;
                  doubleNumber =Double.parseDouble(new DecimalFormat("##.####").format(doubleNumber));
                  String doubleAsString = String.valueOf(doubleNumber);
                  int indexOfDecimal = doubleAsString.indexOf(".");
                  String hrs = doubleAsString.substring(0, indexOfDecimal);
                  String dec = doubleAsString.substring(indexOfDecimal);
                  
                  float decflot = Float.valueOf(dec);
                  float hrsflot = Float.valueOf(hrs);
                  
                  float maindec = decflot * 100 / 60;
                  mainovertime = hrsflot + maindec;
                  
                }                
                
                regularworked = thoursworked - mainovertime;
                
                User u = userDAO.getEmployeeDetails(employeecode);
                
                overtimewages = u.getOvertimeWages();
                hourlywages = u.getHourlyWages();
                
                overtimesalary = mainovertime*overtimewages;
                regularsalary = regularworked*hourlywages;
                
                System.out.println("Integer Part Regular : " + overtimesalary);
                EmployeeActivities ea = new EmployeeActivities();
                
                ea.setEmployeeCode(employeecode);
                ea.setDate(date);
                ea.setHoursWorked(thoursworked);
                ea.setOverTime(overtime);
                ea.setRegularWorked(regularworked);
                ea.setRegularWages(hourlywages);
                ea.setRegularSalary(regularsalary);
                ea.setOverTimeSalary(overtimesalary);
                ea.setTotalOverTime(mainovertime);
                ea.setOverTimeWages(overtimewages);
                ea.setEmployeeStatus(empstatus);
                ea.setStatus(s);
                ea.setCreatedBy(id);
                ea.setIpAddress(IpAddress);
                
                userDAO.addEmployeeActivity(ea);
            }

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
	
	
	
	
	
	/*
	 * @RequestMapping(value = "/getAllCategory", method = RequestMethod.GET,
	 * produces = "application/json") public List<DealerProduct>
	 * getDealerProductById(HttpServletRequest request) {
	 * logger.info("***** GET DEALER PRODUCT BY ID *****"); public List
	 * getAllUsersList(HttpServletRequest request)
	 * 
	 * return this.userDAO.getAllCategoryClient(); }
	 */
	
	@RequestMapping(value = "/getAllCategory", method = RequestMethod.GET, produces = "application/json")
	public List<ClientCategory> getAllCategory(HttpServletRequest request) {
		logger.info("***** GET EMPLOYEE *****");
		List<ClientCategory> user =userDAO.getAllCategoryClient();
		return user;
	}
	
	
	
	@RequestMapping(value = "addClientCategory", method = RequestMethod.POST)
	public String addClientCategory(String categoryname, String categorycode,
			  HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD Client category *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		ClientCategory clientCategory=new ClientCategory();
		  clientCategory.setCreatedBy(id);
		   clientCategory.setName(categoryname);
		    clientCategory.setCode(categorycode);
		    clientCategory.setIpAddress(IpAddress);
		    clientCategory.setStatus(s);
		
		    
		  userDAO.addClientCategory(clientCategory);
		//userType = new UserType(usertypename, s, id, IpAddress);
		//userDAO.addUserType(userType);
		return "Success";
	}
	

	
}