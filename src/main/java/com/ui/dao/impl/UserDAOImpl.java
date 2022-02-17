package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.UserDAO;
import com.ui.model.ClientCategory;
import com.ui.model.DealerProduct;
import com.ui.model.EmployeeActivities;
import com.ui.model.User;
import com.ui.model.UserType;

public class UserDAOImpl implements UserDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public List<UserType> getUserTypes() {
		logger.info("+++++ GET USER TYPE +++++");
		List<UserType> sta = new ArrayList<UserType>();
		String s = "y";
		String sql = "select user_type_id, user_type_name from user_type where status=? order by user_type_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			UserType userType = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userType = new UserType(rs.getInt("user_type_id"), rs.getString("user_type_name"));
				sta.add(userType);
			}
			rs.close();
			ps.close();

			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> getAllUsers() {
		logger.info("+++++ GET ALL USERS +++++");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? order by first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
						rs.getString("email"));
				sta.add(user);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> getAllUsersByPage(int pagesize, int startindex) {
		logger.info("+++++ GET USERS BY PAGE +++++");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? order by user_company_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
						rs.getString("email"));
				sta.add(user);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> searchUsers(String keyword) {
		logger.info("+++++ SERACH USERS +++++");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? and concat(user_company_name, '', first_name, '', middle_name, '', last_name) like ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
						rs.getString("email"));
				sta.add(user);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void addUserType(UserType d) {
		logger.info("+++++ ADD USER TYPE +++++");
		String sql = "insert into user_type (user_type_name, status, created_by, ip_address) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, d.getUserTypeName());
			ps.setString(2, d.getStatus());
			ps.setInt(3, d.getCreatedBy());
			ps.setString(4, d.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void addUser(User u) {
		logger.info("+++++ ADD USER IMPL +++++");
		String sql = "insert into user (user_company_name, first_name, middle_name, last_name, gender, date_of_birth, aadhar_number, passport_number, pan_number, profile_picture, address1, address2, address3, state_id, city_name, pincode, mobile_number, landline_number, email, password, user_type_id, code, hourly_wages, overtime_wages, status, created_by, ip_address,clientcategory) values (?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUserCompanyName());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getMiddleName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getGender());
			ps.setString(6, u.getDateOfBirth());
			ps.setString(7, u.getAadharNumber());
			ps.setString(8, u.getPassportNumber());
			ps.setString(9, u.getPanNumber());
			ps.setString(10, u.getProfilePicture());
			ps.setString(11, u.getAddress1());
			ps.setString(12, u.getAddress2());
			ps.setString(13, u.getAddress3());
			ps.setInt(14, u.getStateId());
			ps.setString(15, u.getCityName());
			ps.setString(16, u.getPincode());
			ps.setString(17, u.getMobileNumber());
			ps.setString(18, u.getLandlineNumber());
			ps.setString(19, u.getEmail());
			ps.setString(20, u.getPassword());
			ps.setInt(21, u.getUserTypeId());
			ps.setInt(22, u.getCode());
			ps.setFloat(23, u.getHourlyWages());
			ps.setFloat(24, u.getOvertimeWages());
			ps.setString(25, u.getStatus());
			ps.setInt(26, u.getCreatedBy());
			ps.setString(27, u.getIpAddress());
			ps.setInt(28, u.getClientcategory());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void editUser(User u) {
		logger.info("+++++ EDIT USER IMPL +++++");
		String sql = "update user set user_company_name=?, first_name=?, middle_name=?, last_name=?, gender=?, date_of_birth=STR_TO_DATE(?,'%d/%m/%Y'), aadhar_number=?, passport_number=?, pan_number=?, profile_picture=?, address1=?, address2=?, address3=?, state_id=?, city_name=?, pincode=?, mobile_number=?, landline_number=?, email=?, password=?, code=?, hourly_wages=?, overtime_wages=?, user_type_id=?, ip_address=? ,clientcategory=? where user_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUserCompanyName());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getMiddleName());
			ps.setString(4, u.getLastName());
			ps.setString(5, u.getGender());
			ps.setString(6, u.getDateOfBirth());
			ps.setString(7, u.getAadharNumber());
			ps.setString(8, u.getPassportNumber());
			ps.setString(9, u.getPanNumber());
			ps.setString(10, u.getProfilePicture());
			ps.setString(11, u.getAddress1());
			ps.setString(12, u.getAddress2());
			ps.setString(13, u.getAddress3());
			ps.setInt(14, u.getStateId());
			ps.setString(15, u.getCityName());
			ps.setString(16, u.getPincode());
			ps.setString(17, u.getMobileNumber());
			ps.setString(18, u.getLandlineNumber());
			ps.setString(19, u.getEmail());
			ps.setString(20, u.getPassword());
			ps.setInt(21, u.getCode());
			ps.setFloat(22, u.getHourlyWages());
			ps.setFloat(23, u.getOvertimeWages());
			ps.setInt(24, u.getUserTypeId());			
			ps.setString(25, u.getIpAddress());
			ps.setInt(26, u.getClientcategory());
			ps.setInt(27, u.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void deleteUser(int userid) {
		logger.info("+++++ DELETE USER IMPL +++++");
		String status = "n";
		String sql = "update user set status=? where user_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public User getUserDetailById(int userid) {
		logger.info("+++++ GET USER DETAIL BY ID +++++");
		User user = null;

		String sql = "select user_id, user_company_name, first_name, middle_name, last_name, gender, DATE_FORMAT(date_of_birth,'%d/%m/%Y') as date_of_birth, aadhar_number, passport_number, pan_number, profile_picture, address1, address2, address3, state_id, city_name, pincode, mobile_number, landline_number, email, password, user_type_id, code, hourly_wages, overtime_wages from user where user_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"), rs.getString("gender"),
						rs.getString("date_of_birth"), rs.getString("aadhar_number"), rs.getString("passport_number"),
						rs.getString("pan_number"), rs.getString("profile_picture"), rs.getString("address1"),
						rs.getString("address2"), rs.getString("address3"), rs.getInt("state_id"),
						rs.getString("city_name"), rs.getString("pincode"), rs.getString("mobile_number"),
						rs.getString("landline_number"), rs.getString("email"), rs.getString("password"),
						rs.getInt("user_type_id"), rs.getInt("code"), rs.getFloat("hourly_wages"), rs.getFloat("overtime_wages"));
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public int isEmailUnique(String email) {
		logger.info("+++++ IS EMAIL UNIQUE +++++");
		String sql = "select user_id from user where status=? and email=?";
		int id = 0;
		String s = "y";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("user_id");
			}
			rs.close();
			ps.close();
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public int isEmailUniqueWithId(String email, int userid) {
		logger.info("+++++ IS EMAIL UNIQUE +++++");
		String sql = "select user_id from user where status=? and email=? and user_id!=?";
		int id = 0;
		String s = "y";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, email);
			ps.setInt(3, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("user_id");
			}
			rs.close();
			ps.close();
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> getUserName() {
		logger.info("+++++ GET USER NAME +++++");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? order by first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"));
				sta.add(user);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> getSuppliers() {
		logger.info("+++++ GET SUPPLIERS +++++");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		int usertypeid = 5;
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? and user_type_id=? order by first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, usertypeid);
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"));
				sta.add(user);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> getClientAndProspectName(HttpSession session) {
		logger.info("+++++ GET CLIENT NAME +++++");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		int usertypeid1 = 4;
		int usertypeid2 = 6;
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if(typeid == 3) {
			sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? and created_by = " + userid + " and (user_type_id = ? or user_type_id = ?) order by first_name";
		} else {
			sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? and (user_type_id = ? or user_type_id = ?) order by first_name";
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, usertypeid1);
			ps.setInt(3, usertypeid2);
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"));
				sta.add(user);
			}
			rs.close();
			ps.close();

			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> getAllEmployees() {
		logger.info("Inside Get All Employees Impl");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		int usertypeid = 2;
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? and user_type_id = ? order by first_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, usertypeid);
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"));
				sta.add(user);
			}
			rs.close();
			ps.close();

			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<User> getUserByUserTypeId(int usertypeid) {
		logger.info("Inside Get User By User Type Id Impl");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? and user_type_id=? order by first_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, usertypeid);
			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"));
				sta.add(user);
			}
			rs.close();
			ps.close();

			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public int getLastUserId() {
		logger.info("+++++ GET LAST USERID +++++");
		String sql = "select user_id from user order by user_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("user_id");
			}
			rs.close();
			ps.close();
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void addDealerProduct(DealerProduct dp) {
		logger.info("+++++ ADD DEALER PRODUCT +++++");
		String sql = "insert into dealer_product (user_id, product_id, created_by, ip_address) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, dp.getUserId());
			ps.setInt(2, dp.getProductId());
			ps.setInt(3, dp.getCreatedBy());
			ps.setString(4, dp.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<DealerProduct> getDealerProductById(int userid) {
		logger.info("+++++ GET DEALER PRODUCT BY ID +++++");
		List<DealerProduct> sta = new ArrayList<DealerProduct>();
		String sql = "select dp.dealer_product_id, dp.product_id, p.product_name from dealer_product dp left join product p on dp.product_id = p.product_id where dp.user_id=? order by p.product_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			DealerProduct dealerProduct = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dealerProduct = new DealerProduct(rs.getInt("dealer_product_id"), rs.getInt("product_id"),
						rs.getString("product_name"));
				sta.add(dealerProduct);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	@Override
	public void deleteDealerProduct(int dealerproductid) {
		logger.info("+++++ DELETE DEALER PRODUCT +++++");		
		String sql = "delete from dealer_product where dealer_product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, dealerproductid);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	
	@Override
    public void addEmployeeActivity(EmployeeActivities ea) {
        logger.info("+++++ ADD EMPLOYEE ACTIVITIES +++++");
        String sql = "insert into employee_activities (employee_code, date, hours_worked, over_time, regular_worked, regular_wages, regular_salary, over_time_salary, total_over_time, over_time_wages, employee_status, status, created_by, ip_address) values (?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ea.getEmployeeCode());
            ps.setString(2, ea.getDate());
            ps.setFloat(3, ea.getHoursWorked());
            ps.setFloat(4, ea.getOverTime());
            ps.setFloat(5, ea.getRegularWorked());
            ps.setFloat(6, ea.getRegularWages());
            ps.setFloat(7, ea.getRegularSalary());
            ps.setFloat(8, ea.getOverTimeSalary());
            ps.setFloat(9, ea.getTotalOverTime());
            ps.setFloat(10, ea.getOverTimeWages());
            ps.setString(11, ea.getEmployeeStatus());
            ps.setString(12, ea.getStatus());
            ps.setInt(13, ea.getCreatedBy());
            ps.setString(14, ea.getIpAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
	
	
	@Override
    public User getEmployeeDetails(int empcode) {
        logger.info("+++++ GET USER DETAIL BY ID +++++");
        User user = null;

        String sql = "select user_id, code, hourly_wages, overtime_wages, first_name, last_name from user where code=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, empcode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getInt("code"), rs.getFloat("hourly_wages"),
                        rs.getFloat("overtime_wages"), rs.getString("first_name"), rs.getString("last_name"));
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
	
	@Override
    public List<EmployeeActivities> getEmpActivityByDate(String fromdate, String todate) {
        logger.info("+++++ GET EMPLOYEE ACTIVITIES BY DATE +++++");
        List<EmployeeActivities> sta = new ArrayList<EmployeeActivities>();
        String sql = "select ea.employee_activities_id, ea.employee_code, sum(ea.regular_salary) as regular_salary, sum(ea.hours_worked) as total_hours, sum(ea.regular_worked) as regular_hours, sum(ea.over_time_salary) as over_time_salary, SUM(ea.total_over_time) as total_over_time, u.first_name, u.last_name from employee_activities ea left join user u on ea.employee_code=u.code where date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by employee_code";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fromdate);
            ps.setString(2, todate);
            EmployeeActivities ea = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ea = new EmployeeActivities();
                ea.setEmployeeActivitiesId(rs.getInt("employee_activities_id"));
                ea.setEmployeeCode(rs.getInt("employee_code"));
                ea.setRegularSalary(rs.getFloat("regular_salary"));
                ea.setOverTimeSalary(rs.getFloat("over_time_salary"));
                ea.setTotalOverTime(rs.getFloat("total_over_time"));
                ea.setFirstName(rs.getString("first_name"));
                ea.setLastName(rs.getString("last_name"));
                ea.setHoursWorked(rs.getFloat("total_hours"));
                ea.setRegularWorked(rs.getFloat("regular_hours"));
                
                sta.add(ea);
            }
            rs.close();
            ps.close();
            return sta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
	
	
	@Override
    public List<EmployeeActivities> getEmpActivityByDateAndCode(int code, String fromdate, String todate) {
        logger.info("+++++ GET EMPLOYEE ACTIVITIES BY DATE AND CODE +++++");
        List<EmployeeActivities> sta = new ArrayList<EmployeeActivities>();
        String sql = "select ea.employee_activities_id, ea.date, ea.employee_code, ea.hours_worked, ea.over_time, ea.regular_wages, ea.over_time_wages, ea.regular_salary, ea.over_time_salary, ea.total_over_time, u.first_name, u.last_name, ea.employee_status from employee_activities ea left join user u on ea.employee_code=u.code where date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and ea.employee_code=? order by ea.date";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fromdate);
            ps.setString(2, todate);
            ps.setInt(3, code);
            EmployeeActivities ea = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ea = new EmployeeActivities();
                ea.setEmployeeActivitiesId(rs.getInt("employee_activities_id"));
                ea.setDate(rs.getString("date"));
                ea.setEmployeeCode(rs.getInt("employee_code"));
                ea.setHoursWorked(rs.getFloat("hours_worked"));
                ea.setOverTime(rs.getFloat("over_time"));
                ea.setRegularWages(rs.getFloat("regular_wages"));
                ea.setRegularSalary(rs.getFloat("regular_salary"));
                ea.setOverTimeSalary(rs.getFloat("over_time_salary"));
                ea.setOverTimeWages(rs.getFloat("over_time_wages"));
                ea.setTotalOverTime(rs.getFloat("total_over_time"));
                ea.setFirstName(rs.getString("first_name"));
                ea.setLastName(rs.getString("last_name"));
                ea.setEmployeeStatus(rs.getString("employee_status"));
                
                sta.add(ea);
            }
            rs.close();
            ps.close();
            return sta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
	
	@Override
    public List<User> getUsersById(int id) {
        logger.info("+++++ GET USERS BY TYPE ID +++++");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? and user_type_id=? order by first_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, id);
            User user = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
                        rs.getString("email"));
                sta.add(user);
            }
            rs.close();
            ps.close();
            return sta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
	
	 public List getAllCategoryClient() {
	      logger.info("Inside Get All Client Impl");
	      ArrayList sta = new ArrayList();
	      String s = "y";
	      String sql = "select categoryid, categoryName, code from client_category where status=? ";
	      Connection conn = null;

	      try {
	         conn = this.dataSource.getConnection();
	         PreparedStatement e = conn.prepareStatement(sql);
	         e.setString(1, s);
	         ClientCategory c = null;
	         ResultSet rs = e.executeQuery();

	         while(rs.next()) {
	            c = new ClientCategory();
	            c.setCategoryId(rs.getInt("categoryid"));
	            c.setName(rs.getString("categoryName"));
	            c.setCode(rs.getString("code"));
	            sta.add(c);
	         }

	         rs.close();
	         e.close();
	         ArrayList var9 = sta;
	         return var9;
	      } catch (SQLException var16) {
	         throw new RuntimeException(var16);
	      } finally {
	         if(conn != null) {
	            try {
	               conn.close();
	            } catch (SQLException var15) {
	               ;
	            }
	         }

	      }
	   }

	@Override
	public String addClientCategory(ClientCategory c) {
	      logger.info("+++++ ADD Client Category IMPL +++++");
	      String sql = "insert into client_category( categoryName, code, status, created_by, ip_address) values (?,?,?,?,?)";
	      Connection conn = null;

	      try {
	         conn = this.dataSource.getConnection();
	         PreparedStatement e = conn.prepareStatement(sql);
	         e.setString(1, c.getName());
	         e.setString(2, c.getCode());
	         e.setString(3, c.getStatus());
	         e.setInt(4, c.getCreatedBy());
	         e.setString(5, c.getIpAddress());
	         e.executeUpdate();
	      } catch (SQLException var12) {
	         throw new RuntimeException(var12);
	      } finally {
	         if(conn != null) {
	            try {
	               conn.close();
	            } catch (SQLException var11) {
	               ;
	            }
	         }

	      }

	      return "Success";
	   }
	
	
	 
	
}//end
