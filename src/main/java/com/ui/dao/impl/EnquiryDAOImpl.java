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

import com.ui.dao.EnquiryDAO;
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
import com.ui.model.TaskEmployee;
import com.ui.model.TaskFollowUp;
import com.ui.model.TaskLogs;
import com.ui.model.UserType;

public class EnquiryDAOImpl implements EnquiryDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(EnquiryDAOImpl.class);

	/*@Override
	public List<Enquiry> getAllOpenEnquiries(HttpSession session) {
		logger.info("+++++ GET ALL OPEN ENQUIRIES IMPL +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		String s1 = "cl";
		String s2 = "cw";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2 || typeid == 3) {
			//sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number,u.state_id,s.state_name  from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u left join where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id and (ea.user_id = "
			sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number,u.city_name,u.state_id,s.state_name  from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u left join state s on u.state_id=s.state_id left join where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id and ea.user_id = "+ userid + " or e.created_by = " + userid + " order by e.enquiry_date desc";
					//sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id and (ea.user_id = "	
					+ userid + " or e.created_by = " + userid + ") order by e.enquiry_date desc";
		} else {
			//sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e, user u where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id order by e.enquiry_date desc";
		// sql ="select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number from enquiry e, user u where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id order by e.enquiry_date desc";
		 sql ="select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number,u.city_name,u.state_id,s.state_name from enquiry e, user u left join state s on u.state_id=s.state_id where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id order by e.enquiry_date desc";
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s1);
			ps.setString(3, s2);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"),rs.getString("mobile_number"),rs.getString("email"),rs.getString("state_name"),rs.getString("city_name"));
				sta.add(enquiry);
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
	}*/

	
	
	public List<Enquiry> getAllOpenEnquiries(final HttpSession session) {
        EnquiryDAOImpl.logger.info("+++++ GET ALL OPEN ENQUIRIES IMPL +++++");
        final List<Enquiry> sta = new ArrayList<Enquiry>();
        final String s = "n";
        final String s2 = "cl";
        final String s3 = "cw";
        final int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
        final int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String sql = "";
        if (typeid == 2 || typeid == 3) {
            sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status,u.mobile_number, u.user_company_name, u.first_name, u.last_name, u.email from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id and (ea.user_id = " + userid + " or e.created_by = " + userid + ") order by e.enquiry_date desc";
        }
        else {
            sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status,u.mobile_number, u.user_company_name, u.first_name, u.last_name, u.email from enquiry e, user u where e.status!=? and e.status!=? and e.status!=? and e.client_id = u.user_id order by e.enquiry_date desc";
        }
        Connection conn = null;
        try {
            conn = this.dataSource.getConnection();
            final PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setString(2, s2);
            ps.setString(3, s3);
            Enquiry enquiry = null;
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"), rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"), rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"), rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"), rs.getString("last_name"),rs.getString("mobile_number"),rs.getString("email"));
                sta.add(enquiry);
            }
            rs.close();
            ps.close();
            return sta;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
	
	
	
	@Override
	public List<Enquiry> getEnquiriesByStatus(String status, HttpSession session) {
		logger.info("+++++ GET ENQUIRIES BY STATUS +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (!status.equals("c") && !status.equals("a")) {
			if (typeid == 2 || typeid == 3) {
				sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u where e.status=? and e.client_id = u.user_id and (ea.user_id = "
						+ userid + " or e.created_by = " + userid + ") order by e.enquiry_date desc";
			} else {
				sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e, user u where e.status=? and e.client_id = u.user_id order by e.enquiry_date desc";
			}
		}

		if (status.equals("c")) {
			if (typeid == 2 || typeid == 3) {
				sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u where (e.status=? or e.status=?) and e.client_id = u.user_id and (ea.user_id = "
						+ userid + " or e.created_by = " + userid + ") order by e.enquiry_date desc";
			} else {
				sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e, user u where (e.status=? or e.status=?) and e.client_id = u.user_id order by e.enquiry_date desc";
			}
		}

		if (status.equals("a")) {
			if (typeid == 2 || typeid == 3) {
				sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u where e.status!=? and e.client_id = u.user_id and (ea.user_id = "
						+ userid + " or e.created_by = " + userid + ") order by e.enquiry_date desc";
			} else {
				sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e, user u where e.status!=? and e.client_id = u.user_id order by e.enquiry_date desc";
			}
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (!status.equals("c") && !status.equals("a")) {
				ps.setString(1, status);
			}
			if (status.equals("c")) {
				ps.setString(1, "cl");
				ps.setString(2, "cw");
			}
			if (status.equals("a")) {
				ps.setString(1, "n");
			}

			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"));
				sta.add(enquiry);
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
	public List<Enquiry> getEnquiriesByPage(String StartDate, String EndDate, int pagesize, int startindex, HttpSession session) {
		logger.info("+++++ GET ENQUIRIES BY PAGE IMPL +++++"+StartDate+EndDate);
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2 || typeid == 3) {
			//sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u where e.status!=? and e.client_id = u.user_id and (ea.user_id = "
					sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u where e.status!=? and e.client_id = u.user_id and (ea.user_id = "		
					
					+ userid + " or e.created_by = " + userid + ") order by e.enquiry_date desc limit ?,?";
		} else {
			sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number  from enquiry e join  user u where e.status!=? and e.enquiry_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and e.client_id = u.user_id order by e.enquiry_date desc limit ?,? ";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("from and to date is "+StartDate+EndDate+startindex+pagesize);
			System.out.println("from and to date is "+sql);
			ps.setString(1, s);
			ps.setString(2, StartDate);
			ps.setString(3, EndDate);
	        ps.setInt(4, startindex);
			ps.setInt(5, pagesize);
          
		
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"),rs.getString("email"),rs.getString("mobile_number"));
				
				System.out.println("+_)(_)_)_()_()_()_()_from and to date is "+StartDate+EndDate);
				
				sta.add(enquiry);
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
	public List<Enquiry> searchEnquiry(String keyword, HttpSession session) {
		logger.info("+++++ SEARCH ENQUIRIES IMPL +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		/*if (typeid == 2 || typeid == 3) {
			sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name,u.last_name,u.email,u.mobile_number,u.city_name,u.state_id,s.state_name from enquiry e left join enquiry_assign ea on ea.enquiry_id=e.enquiry_id, user u left join state s on u.state_id=s.state_id where e.status!=? and e.client_id = u.user_id and (ea.user_id = "
					+ userid + " or e.created_by = " + userid
					+ ") and concat(e.enquiry_date,'',e.enquiry_via,'',u.user_company_name,'',u.first_name,'',u.last_name) like ? order by e.enquiry_date desc";
		} else {
			sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number,u.city_name,u.state_id,s.state_name from enquiry e, user u left join state s on u.state_id=s.state_id where e.status!=? and e.client_id = u.user_id and concat(e.enquiry_date,'',e.enquiry_via,'',u.user_company_name,'',u.first_name,'',u.last_name ,'',u.city_name,'',s.state_name) like ? order by e.enquiry_date desc";
		}
		*/
		
		if (typeid == 2 || typeid == 3) {
	          sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, ep.product_detail,u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number,u.city_name,u.state_id,s.state_name  from enquiry e left join enquiry_product ep on e.enquiry_id=ep.enquiry_id   left join user u on e.client_id = u.user_id left join state s on u.state_id=s.state_id where e.status!=? and e.client_id = u.user_id and (ea.user_id = " + userid + " or e.created_by = " + userid   + ") and   concat(e.enquiry_date,'',e.enquiry_via,'',u.first_name,'',u.last_name,'',u.email,'',u.mobile_number,'',u.city_name,'',s.state_name,'',ep.product_detail)    like ? order by e.enquiry_date desc ";
	                  
	      } else {
	          sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, ep.product_detail,u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number,u.city_name,u.state_id,s.state_name  from enquiry e left join enquiry_product ep on e.enquiry_id=ep.enquiry_id   left join user u on e.client_id = u.user_id left join state s on u.state_id=s.state_id where e.status!=? and  concat(e.enquiry_date,'',e.enquiry_via,'',u.first_name,'',u.last_name,'',u.email,'',u.mobile_number,'',u.city_name,'',s.state_name,'',ep.product_detail)    like ? order by e.enquiry_date desc ";
	      }
		
		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
//						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
//						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
//						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
//						rs.getString("last_name"));
				
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"),rs.getString("product_detail"),rs.getString("email"),rs.getString("mobile_number"),rs.getString("city_name"),rs.getInt("state_id"),rs.getString("state_name"));
				sta.add(enquiry);
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
	public void addEnquiry(Enquiry e) {
		logger.info("+++++ ADD ENQUIRY IMPL +++++");
		String sql = "insert into enquiry(sequence, enquiry_no, reference_id, client_id, employee_id, enquiry_date, enquiry_via, enquiry_remarks, status, created_by, ip_address) values (?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, e.getSequence());
			ps.setString(2, e.getEnquiryNo());
			ps.setInt(3, e.getReferenceId());
			ps.setInt(4, e.getClientId());
			ps.setInt(5, e.getEmployeeId());
			ps.setString(6, e.getEnquiryDate());
			ps.setString(7, e.getEnquiryVia());
			ps.setString(8, e.getEnquriRemarks());
			ps.setString(9, e.getStatus());
			ps.setInt(10, e.getCreatedBy());
			ps.setString(11, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public int getLastEnquiryId() {
		logger.info("+++++ GET LAST ENQUIRY ID IMPL +++++");
		String sql = "select enquiry_id from enquiry order by enquiry_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("enquiry_id");
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
	public void addEnquiryProduct(EnquiryProduct e) {
		logger.info("+++++ ADD ENQUIRY PRODUCT IMPL +++++");
		String sql = "insert into enquiry_product(enquiry_id, product_id, product_detail, ready_to_invoice, created_by, ip_address) values (?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, e.getEnquiryId());
			ps.setInt(2, e.getProductId());
			ps.setString(3, e.getProductDetail());
			ps.setString(4, e.getReadyToInvoice());
			ps.setInt(5, e.getCreatedBy());
			ps.setString(6, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void addEnquiryUrl(EnquiryUrl e) {
		logger.info("+++++ ADD ENQUIRY URL IMPL +++++");
		String sql = "insert into enquiry_url(enquiry_id, url, created_by, ip_address) values (?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setString(2, e.getUrl());
			ps.setInt(3, e.getCreatedBy());
			ps.setString(4, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void addEnquiryFiles(EnquiryFile e) {
		logger.info("+++++ ADD ENQUIRY FILE IMPL +++++");
		String sql = "insert into enquiry_file(enquiry_id, file_title, file_path, created_by, ip_address) values (?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setString(2, e.getFileTitle());
			ps.setString(3, e.getFilePath());
			ps.setInt(4, e.getCreatedBy());
			ps.setString(5, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public List<EnquiryProduct> getEnquiryProductsByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY PRODUCTS BY ENQUIRY ID IMPL +++++");
		List<EnquiryProduct> sta = new ArrayList<EnquiryProduct>();
		String sql = "select enquiry_product_id, enquiry_id, product_id, product_detail, ready_to_invoice from enquiry_product where enquiry_id=? order by enquiry_product_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			EnquiryProduct enquiryProduct = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryProduct = new EnquiryProduct(rs.getInt("enquiry_product_id"), rs.getInt("enquiry_id"),
						rs.getInt("product_id"), rs.getString("product_detail"), rs.getString("ready_to_invoice"));
				sta.add(enquiryProduct);
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
	public List<EnquiryUrl> getEnquiryUrlsByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY URLS BY ENQUIRY ID IMPL +++++");
		List<EnquiryUrl> sta = new ArrayList<EnquiryUrl>();
		String sql = "select enquiry_url_id, enquiry_id, url from enquiry_url where enquiry_id=? order by enquiry_url_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			EnquiryUrl enquiryUrl = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryUrl = new EnquiryUrl(rs.getInt("enquiry_url_id"), rs.getInt("enquiry_id"), rs.getString("url"));
				sta.add(enquiryUrl);
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
	public List<EnquiryFile> getEnquiryFilesByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY FILE BY ENQUIRY ID IMPL +++++");
		List<EnquiryFile> sta = new ArrayList<EnquiryFile>();
		String sql = "select enquiry_file_id, enquiry_id, file_title, file_path from enquiry_file where enquiry_id=? order by enquiry_file_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			EnquiryFile enquiryFile = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryFile = new EnquiryFile(rs.getInt("enquiry_file_id"), rs.getInt("enquiry_id"),
						rs.getString("file_title"), rs.getString("file_path"));
				sta.add(enquiryFile);
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
	public List<EnquiryStatus> getEnquiryLogByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY LOG BY ENQUIRY ID IMPL +++++");
		List<EnquiryStatus> sta = new ArrayList<EnquiryStatus>();
		String sql = "select es.enquiry_status_id, es.enquiry_id, es.quotation_id, es.status_id, DATE_FORMAT(es.log_date_time,'%d/%m/%Y %h:%i %p') as log_date_time, es.created_by, DATE_FORMAT(CONVERT_TZ(es.created_date,'+00:00','+09:29'),'%d/%m/%Y %h:%i %p') as created_date, e.enquiry_no, s.status_name, u.first_name, u.last_name from enquiry_status es, enquiry e, user u, status s where es.enquiry_id=? and es.enquiry_id = e.enquiry_id and es.created_by = u.user_id and es.status_id = s.status_id union select es.enquiry_status_id, es.enquiry_id, es.quotation_id, es.status_id, DATE_FORMAT(es.log_date_time,'%d/%m/%Y %h:%i %p') as log_date_time, es.created_by, DATE_FORMAT(CONVERT_TZ(es.created_date,'+00:00','+09:29'),'%d/%m/%Y %h:%i %p') as created_date, e.enquiry_no, remark as status_name, u.first_name, u.last_name from enquiry_status es, enquiry e, user u where es.enquiry_id=? and es.status_id=0 and es.enquiry_id = e.enquiry_id and es.created_by = u.user_id order by enquiry_status_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			ps.setInt(2, enquiryid);
			EnquiryStatus enquiryStatus = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryStatus = new EnquiryStatus(rs.getInt("enquiry_status_id"), rs.getInt("enquiry_id"),
						rs.getInt("quotation_id"), rs.getInt("status_id"), rs.getString("log_date_time"),
						rs.getInt("created_by"), rs.getString("created_date"), rs.getString("enquiry_no"),
						rs.getString("status_name"), rs.getString("first_name"), rs.getString("last_name"));
				sta.add(enquiryStatus);
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
	public List<EnquiryFollowup> getEnquiryFollowupByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY FOLLOW-UP BY ENQUIRY ID IMPL +++++");
		List<EnquiryFollowup> sta = new ArrayList<EnquiryFollowup>();
		String sql = "select ef.followup_id, ef.enquiry_id, DATE_FORMAT(ef.followup_date_time,'%d/%m/%Y %h:%i %p') as followup_date_time, ef.remark, ef.created_by, u.first_name, u.last_name from enquiry_followup ef, user u where ef.enquiry_id=? and ef.created_by = u.user_id order by ef.followup_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			EnquiryFollowup enquiryFollowup = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiryFollowup = new EnquiryFollowup(rs.getInt("followup_id"), rs.getInt("enquiry_id"),
						rs.getString("followup_date_time"), rs.getString("remark"), rs.getInt("created_by"),
						rs.getString("first_name"), rs.getString("last_name"));
				sta.add(enquiryFollowup);
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
	public void deleteEnquiry(int enquiryid) {
		logger.info("+++++ DELETE ENQUIRY IMPL +++++");
		String status = "n";
		String sql = "update enquiry set status=? where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, enquiryid);
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
	public void deleteEnquiryProduct(int enquiryid) {
		logger.info("+++++ DELETE ENQUIRY PRODUCT +++++");
		String sql = "delete from enquiry_product where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
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
	public void deleteEnquiryUrl(int enquiryid) {
		logger.info("+++++ DELETE ENQUIRY URLS +++++");
		String sql = "delete from enquiry_url where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
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
	public void deleteEnquiryFile(int enquiryid) {
		logger.info("+++++ DELETE ENQUIRY FILES +++++");
		String sql = "delete from enquiry_file where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
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
	public void editEnquiry(Enquiry e) {
		logger.info("+++++ EDIT ENQUIRY IMPL +++++");
		String sql = "update enquiry set reference_id=?, client_id=?, employee_id=?, enquiry_date=STR_TO_DATE(?,'%d/%m/%Y'), enquiry_via=?, enquiry_remarks=?, ip_address=? where enquiry_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, e.getReferenceId());
			ps.setInt(2, e.getClientId());
			ps.setInt(3, e.getEmployeeId());
			ps.setString(4, e.getEnquiryDate());
			ps.setString(5, e.getEnquiryVia());
			ps.setString(6, e.getEnquriRemarks());			
			ps.setString(7, e.getIpAddress());
			ps.setInt(8, e.getEnquiryId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public Enquiry getEnquiryDetailsById(int enquiryid) {
		logger.info("+++++ GET ENQUIRY DETAILS BY ID IMPL +++++");
		Enquiry enquiry = null;
		/*String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e, user u where e.client_id = u.user_id and e.enquiry_id=?";*/
		String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, concat(cr.first_name,' ',cr.last_name) as created_by_name from enquiry e left join user cr on e.created_by=cr.user_id, user u where e.client_id = u.user_id and e.enquiry_id=?";
		//************************edited by yuvraj*********************
		//added created_by_name using concat
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"),rs.getString("created_by_name"));
			}
			rs.close();
			ps.close();
			return enquiry;
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
	public Enquiry getLastEnquiryDetail() {
		logger.info("+++++ GET LAST ENQUIRY DETAIL IMPL +++++");
		Enquiry enquiry = null;
		String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name from enquiry e, user u where e.client_id = u.user_id order by e.enquiry_id desc limit 0,1";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"));
			}
			rs.close();
			ps.close();
			return enquiry;
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

	public int getLastEnquirySequence() {
		logger.info("++++++++++ GET LAST ENQUIRY SEQUENCE IMPL ++++++++++");
		String sql = "select sequence from enquiry order by enquiry_id desc limit 0,1";
		int sequence = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sequence = rs.getInt("sequence");
			}
			rs.close();
			ps.close();
			return sequence;
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
	public void addEnquiryStatus(EnquiryStatus e) {
		logger.info("+++++ ADD ENQUIRY STATUS IMPL +++++");
		String sql = "insert into enquiry_status(enquiry_id, quotation_id, status_id, status, created_by, ip_address) values (?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setInt(2, e.getQuotationId());
			ps.setInt(3, e.getStatusId());
			ps.setString(4, e.getStatus());
			ps.setInt(5, e.getCreatedBy());
			ps.setString(6, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void closeEnquiry(int enquiryid) {
		logger.info("+++++ CHANGE ENQUIRY IMPL +++++");
		String status = "c";
		String sql = "update enquiry set status=? where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, enquiryid);
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
	public void openEnquiry(int enquiryid) {
		logger.info("+++++ CHANGE ENQUIRY IMPL +++++");
		String status = "o";
		String sql = "update enquiry set status=? where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, enquiryid);
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
	public int getLastEnquiryStatusId() {
		logger.info("+++++ GET LAST ENQUIRY STATUS ID +++++");
		String sql = "select enquiry_status_id from enquiry_status order by enquiry_status_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("enquiry_status_id");
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
	public void addEnquiryLog(EnquiryStatus e) {
		logger.info("+++++ ADD ENQUIRY LOG +++++");
		String sql = "insert into enquiry_status(enquiry_id, status_id, remark, log_date_time, status, created_by, ip_address) values (?,?,?,STR_TO_DATE(?,'%d/%m/%Y %h:%i %p'),?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setInt(2, e.getStatusId());
			ps.setString(3, e.getRemark());
			ps.setString(4, e.getLogDateTime());
			ps.setString(5, e.getStatus());
			ps.setInt(6, e.getCreatedBy());
			ps.setString(7, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public List<EnquiryProduct> getAllEnquiryProductName() {
		logger.info("+++++ ALL ENQUIRY PRODUCT +++++");
		List<EnquiryProduct> sta = new ArrayList<EnquiryProduct>();
		String sql = "select enquiry_product_id, enquiry_id, product_id, product_detail, ready_to_invoice from enquiry_product";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			EnquiryProduct enquiryProduct = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryProduct = new EnquiryProduct(rs.getInt("enquiry_product_id"), rs.getInt("enquiry_id"),
						rs.getInt("product_id"), rs.getString("product_detail"), rs.getString("ready_to_invoice"));
				sta.add(enquiryProduct);
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
	public void addStatusReason(EnquiryStatusReason esr) {
		logger.info("+++++ ADD STATUS REASON +++++");
		String sql = "insert into enquiry_status_reason(status_reason, status, created_by, ip_address) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, esr.getStatusReason());
			ps.setString(2, esr.getStatus());
			ps.setInt(3, esr.getCreatedBy());
			ps.setString(4, esr.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public List<EnquiryStatusReason> getStatusReason() {
		logger.info("+++++ GET STATUS REASON +++++");
		List<EnquiryStatusReason> sta = new ArrayList<EnquiryStatusReason>();
		String s = "y";
		String sql = "select reason_id, status_reason from enquiry_status_reason where status=? order by status_reason";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			EnquiryStatusReason enquiryStatusReason = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiryStatusReason = new EnquiryStatusReason(rs.getInt("reason_id"), rs.getString("status_reason"));
				sta.add(enquiryStatusReason);
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
	public void insertEnquiryStatus(AllEnquiryStatus aes) {
		logger.info("+++++ INSERT ENQUIRY STATUS +++++");
		String sql = "insert into all_enquiry_status(enquiry_id, enquiry_status, reason_id, status, created_by, ip_address) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aes.getEnquiryId());
			ps.setString(2, aes.getEnquiryStatus());
			ps.setInt(3, aes.getReasonId());
			ps.setString(4, aes.getStatus());
			ps.setInt(5, aes.getCreatedBy());
			ps.setString(6, aes.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void changeEnquiryStatus(Enquiry e) {
		logger.info("+++++ CHANGE ENQUIRY STATUS +++++");
		String sql = "update enquiry set status=?, reason_id=? where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getStatus());
			ps.setInt(2, e.getReasonId());
			ps.setInt(3, e.getEnquiryId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void addEnquiryFollowup(EnquiryFollowup e) {
		logger.info("+++++ ADD ENQUIRY FOLLOW-UP +++++");
		String sql = "insert into enquiry_followup(enquiry_id, followup_date_time, remark, status, created_by, ip_address) values (?,STR_TO_DATE(?,'%d/%m/%Y %h:%i %p'),?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setString(2, e.getFollowupDateTime());
			ps.setString(3, e.getRemark());
			ps.setString(4, e.getStatus());
			ps.setInt(5, e.getCreatedBy());
			ps.setString(6, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public List<Enquiry> getTodayFollowupEnquiries(HttpSession session) {
		logger.info("+++++ GET TODAY FOLLOWUP ENQUIRIES +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2) {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry e, enquiry_assign ea, user u, enquiry_followup ef where e.status!=? and ea.user_id = "
					+ userid
					+ " and ea.enquiry_id=e.enquiry_id and e.client_id = u.user_id and e.enquiry_id = ef.enquiry_id and date_format(ef.followup_date_time,'%Y-%m-%d') = curdate() order by ef.followup_date_time";
		} else {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry_followup ef left join enquiry e on ef.enquiry_id = e.enquiry_id and e.status!=? left join user u on e.client_id = u.user_id where date_format(ef.followup_date_time,'%Y-%m-%d') = curdate() order by ef.followup_date_time";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getString("enquiry_no"), rs.getInt("client_id"),
						rs.getString("enquiry_date"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getInt("followup_id"), rs.getString("followup_time"),
						rs.getString("remark"), rs.getString("followup_status"));
				sta.add(enquiry);
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
	public List<Enquiry> getFollowupEnquiriesByDate(HttpSession session, String fromdate, String todate) {
		logger.info("+++++ GET FOLLOWUP ENQUIRIES BY DATE +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2) {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%d/%m/%Y') as followup_date, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry e, user u, enquiry_assign ea, enquiry_followup ef where e.status!=? and ea.user_id = "
					+ userid
					+ " and ea.enquiry_id=e.enquiry_id and e.client_id = u.user_id and e.enquiry_id = ef.enquiry_id and ef.followup_date_time between str_to_date(?, '%d/%m/%Y') and str_to_date(?, '%d/%m/%Y') order by ef.followup_date_time";
		} else {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%d/%m/%Y') as followup_date, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry_followup ef left join enquiry e on ef.enquiry_id = e.enquiry_id and e.status!=? left join user u on e.client_id = u.user_id where ef.followup_date_time between str_to_date(?, '%d/%m/%Y') and str_to_date(?, '%d/%m/%Y') order by ef.followup_date_time";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, fromdate);
			ps.setString(3, todate);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getString("enquiry_no"), rs.getInt("client_id"),
						rs.getString("enquiry_date"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getInt("followup_id"), rs.getString("followup_date"),
						rs.getString("followup_time"), rs.getString("remark"), rs.getString("followup_status"));
				sta.add(enquiry);
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
	public String markCompleteFollowup(EnquiryFollowup e) {
		logger.info("+++++ MARK FOLLOWUP TO COMPLETE +++++");
		String sql = "update enquiry_followup set status=?, created_by=?, ip_address=? where followup_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getStatus());
			ps.setInt(2, e.getCreatedBy());
			ps.setString(3, e.getIpAddress());
			ps.setInt(4, e.getFollowupId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException ex) {
			return "Something wrong!";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void deleteFollowup(int followupid) {
		logger.info("+++++ DELETE FOLLOWUP +++++");
		String sql = "delete from enquiry_followup where followup_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, followupid);
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
	public List<EnquiryProduct> getEnquiryReadyToIncodeProductByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY READY TO INVOICE PRODUCT BY ENQUIRY ID IMPL +++++");
		List<EnquiryProduct> sta = new ArrayList<EnquiryProduct>();
		String readyToInvoice = "y";
		String sql = "select enquiry_product_id, enquiry_id, product_id, product_detail from enquiry_product where enquiry_id=? and ready_to_invoice=? order by product_detail";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			ps.setString(2, readyToInvoice);
			EnquiryProduct enquiryProduct = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryProduct = new EnquiryProduct(rs.getInt("enquiry_product_id"), rs.getInt("enquiry_id"),
						rs.getInt("product_id"), rs.getString("product_detail"));
				sta.add(enquiryProduct);
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
	public List<Enquiry> getTodayFiveFollowupForDashboard(HttpSession session) {
		logger.info("+++++ GET TODAY FIVE FOLLOWUP DASHBAORD +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2) {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry e, enquiry_assign ea, user u, enquiry_followup ef where e.status!=? and ea.user_id = "
					+ userid
					+ " and ea.enquiry_id=e.enquiry_id and e.client_id = u.user_id and e.enquiry_id = ef.enquiry_id and date_format(ef.followup_date_time,'%Y-%m-%d') = curdate() order by ef.followup_date_time limit 0,5";
		} else {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry_followup ef left join enquiry e on ef.enquiry_id = e.enquiry_id and e.status!=? left join user u on e.client_id = u.user_id where date_format(ef.followup_date_time,'%Y-%m-%d') = curdate() and ef.created_by = "
					+ userid + " order by ef.followup_date_time limit 0,5";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getString("enquiry_no"), rs.getInt("client_id"),
						rs.getString("enquiry_date"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getInt("followup_id"), rs.getString("followup_time"),
						rs.getString("remark"), rs.getString("followup_status"));
				sta.add(enquiry);
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
	public void addEnquiryAssign(EnquiryAssign e) {
		logger.info("+++++ ADD ENQUIRY ASSIGN +++++");
		String sql = "insert into enquiry_assign(enquiry_id, user_id, created_by, ip_address) values (?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setInt(2, e.getUserId());
			ps.setInt(3, e.getCreatedBy());
			ps.setString(4, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}
	
	@Override
	public List<EnquiryAssign> getEnquiryAssignByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY ASSIGN BY ENQUIRY ID +++++");
		List<EnquiryAssign> sta = new ArrayList<EnquiryAssign>();
		//added email and number here
		String sql = "select ea.enquiry_assign_id, ea.enquiry_id, ea.user_id, u.first_name, u.last_name, u.email, u.mobile_number from enquiry_assign ea left join user u on ea.user_id = u.user_id where ea.enquiry_id=? order by ea.enquiry_assign_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			EnquiryAssign enquiryAssign = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryAssign = new EnquiryAssign(rs.getInt("enquiry_assign_id"), rs.getInt("enquiry_id"),
						rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("mobile_number"));
				sta.add(enquiryAssign);
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
	public void deleteEnquiryAssignRow(int id) {
		logger.info("+++++ DELETE ENQUIRY ASSIGN +++++");
		String sql = "delete from enquiry_assign where enquiry_assign_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
    public void addLogMemberAssign(EnquiryLogMember e) {
        logger.info("+++++ ADD ENQUIRY LOG MEMBER +++++");
        String sql = "insert into enquiry_log_member(enquiry_status_id, enquiry_id, member_id, created_by, ip_address) values (?,?,?,?,?)";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getEnquiryLogId());
            ps.setInt(2, e.getEnquiryId());
            ps.setInt(3, e.getMemberId());
            ps.setInt(4, e.getCreatedBy());
            ps.setString(5, e.getIpAddress());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
	
	@Override
    public EnquiryStatus getLastEnquiryStatusDetail() {
        logger.info("+++++ GET LAST ENQUIRY STATUS DETAIL IMPL +++++");
        EnquiryStatus enquiryStatus = null;
        String sql = "select enquiry_status_id, enquiry_id, remark, DATE_FORMAT(log_date_time,'%d/%m/%Y %h:%i %p') as log_date_time from enquiry_status order by enquiry_status_id desc limit 0,1";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                enquiryStatus = new EnquiryStatus(rs.getInt("enquiry_status_id"), rs.getInt("enquiry_id"),
                        rs.getString("remark"), rs.getString("log_date_time"));
            }
            rs.close();
            ps.close();
            return enquiryStatus;
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
    public List<EnquiryLogMember> getEnquiryAssignMemberById(int enquirylogid) {
        logger.info("+++++ ENQUIRY ASSIGN MEMBER BY LOG ID +++++");
        List<EnquiryLogMember> sta = new ArrayList<EnquiryLogMember>();
        String sql = "select em.enquiry_log_member_id, em.enquiry_id, em.member_id, u.first_name, u.last_name, u.email, u.mobile_number from enquiry_log_member em left join user u on em.member_id = u.user_id where em.enquiry_status_id=? order by u.first_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, enquirylogid);
            EnquiryLogMember elm = null;
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
              elm = new EnquiryLogMember();
              elm.setEnquiryLogMemberId(rs.getInt("enquiry_log_member_id"));
              elm.setEnquiryId(rs.getInt("enquiry_id"));
              elm.setMemberId(rs.getInt("member_id"));
              elm.setFirstName(rs.getString("first_name"));
              elm.setLastName(rs.getString("last_name"));
              elm.setEmail(rs.getString("email"));
              elm.setMobileNumber(rs.getString("mobile_number"));
                
                sta.add(elm);
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
    public int getLastEnquiryFollowupId() {
        logger.info("+++++ GET LAST ENQUIRY FOLLOWUP ID +++++");
        String sql = "select followup_id from enquiry_followup order by followup_id desc limit 0,1";
        int id = 0;
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("followup_id");
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
    public void addFollowupMemberAssign(EnquiryFollowupMember e) {
        logger.info("+++++ ADD ENQUIRY FOLLOWUP MEMBER +++++");
        String sql = "insert into enquiry_followup_member(enquiry_followup_id, enquiry_id, member_id, created_by, ip_address) values (?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, e.getEnquiryFollowupId());
            ps.setInt(2, e.getEnquiryId());
            ps.setInt(3, e.getMemberId());
            ps.setInt(4, e.getCreatedBy());
            ps.setString(5, e.getIpAddress());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
	
	@Override
    public EnquiryFollowup getLastEnquiryFollowupDetail() {
        logger.info("+++++ GET LAST ENQUIRY FOLLOWUP DETAIL IMPL +++++");
        EnquiryFollowup ef = null;
        String sql = "select followup_id, enquiry_id, remark, DATE_FORMAT(followup_date_time,'%d/%m/%Y %h:%i %p') as followup_date_time from enquiry_followup order by followup_id desc limit 0,1";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {              
                ef = new EnquiryFollowup(rs.getInt("followup_id"), rs.getInt("enquiry_id"),
                        rs.getString("remark"), rs.getString("followup_date_time"));
            }
            rs.close();
            ps.close();
            return ef;
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
    public List<EnquiryFollowupMember> getEnquiryFollowupMemberById(int enquiryfollowupid) {
        logger.info("+++++ ENQUIRY FOLLOWUP MEMBER BY FOLLOWUP ID +++++");
        List<EnquiryFollowupMember> sta = new ArrayList<EnquiryFollowupMember>();
        String sql = "select em.enquiry_followup_member_id, em.enquiry_id, em.member_id, u.first_name, u.last_name, u.email, u.mobile_number from enquiry_followup_member em left join user u on em.member_id = u.user_id where em.enquiry_followup_id=? order by u.first_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, enquiryfollowupid);
            EnquiryFollowupMember elm = null;
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
              elm = new EnquiryFollowupMember();
              elm.setEnquiryFollowupMemberId(rs.getInt("enquiry_followup_member_id"));
              elm.setEnquiryId(rs.getInt("enquiry_id"));
              elm.setMemberId(rs.getInt("member_id"));
              elm.setFirstName(rs.getString("first_name"));
              elm.setLastName(rs.getString("last_name"));
              elm.setEmail(rs.getString("email"));
              elm.setMobileNumber(rs.getString("mobile_number"));
                
                sta.add(elm);
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
	public int getLastFollowupId() {
		logger.info("+++++ GET LAST FOLLOWUP ID +++++");
		String sql = "select followup_id from enquiry_followup order by followup_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("followup_id");
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
	public void addEmpFollowup(FollowupEmployee e) {
		logger.info("+++++ ADD FOLLOWUP EMPLOYEE +++++");
		String sql = "insert into followup_employee(followup_id, user_id, created_by, ip_address) values (?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getFollowupId());
			ps.setInt(2, e.getUserId());
			ps.setInt(3, e.getCreatedBy());
			ps.setString(4, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public List<FollowupEmployee> getFollowupEmployeeByFollowupId(int followupId) {
		logger.info("+++++ GET FOLLOWUP EMPLOYEE BY FOLLOWUP ID +++++");
		List<FollowupEmployee> sta = new ArrayList<FollowupEmployee>();
		String sql = "select fp.followup_emp_id, fp.followup_id, fp.user_id, u.first_name, u.last_name from followup_employee fp left join user u on fp.user_id = u.user_id where followup_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, followupId);
			FollowupEmployee followupEmployee = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				followupEmployee = new FollowupEmployee(rs.getInt("followup_emp_id"), rs.getInt("followup_id"),
						rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"));
				sta.add(followupEmployee);
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
	public int addTask(Task task) {
		logger.info("*************** ADD PRODUCT DAO IMPL ***************");
		String sql = "insert into task(taskDate,employeeId,Description,status,created_by,createdDate,ip_address) values (?,?,?,?,?,CURRENT_TIMESTAMP(),?)";

		Connection conn = null;
		int taskId = 0;

		try {

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

			ps.setString(1,task.getTaskDate());
			ps.setInt(2, task.getEmployeeId());
			ps.setString(3, task.getDescription());
			ps.setString(4, task.getStatus());
			ps.setInt(5, task.getCreatedBy());
			//ps.setString(6, task.getCreatedDate());
			ps.setString(6, task.getIpAddress());
			
			System.out.println("++++++++++++++++++PSS++++++++++++++"+ps);

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				taskId = rs.getInt(1);
				System.out.println("id=-------------------------" + taskId);
			}
			return taskId;
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
	public void addTaskAssign(TaskAssign taskAssign) {

		logger.info("+++++ ADD ENQUIRY ASSIGN +++++");
		String sql = "insert into task_assign(task_id, user_id, created_by, ip_address) values (?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, taskAssign.getTask_id());
			ps.setInt(2, taskAssign.getUser_id());
			ps.setInt(3, taskAssign.getCreated_by());
			ps.setString(4, taskAssign.getIp_address());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	
		
	}
	
	
	
	
	
	 @Override
		public int getLastTaskId() {
			logger.info("+++++ GET LAST ENQUIRY ID IMPL +++++");
			String sql = "select taskId from task order by taskId desc limit 0,1";
			int id = 0;
			Connection conn = null;

			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					id = rs.getInt("taskId");
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
		public List<Task> getAllTask() {

			logger.info("*************** GET ALL PRODUCTS IMPL***************");
			String s = "y";
			List<Task> sta = new ArrayList<Task>();
			String sql = "select  taskId,created_by,ip_address,status,createdDate,taskDate,employeeId,Description from task where status=?";

			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, s);
				Task p = null;
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					p = new Task();
					p.setTaskId(rs.getInt("taskId"));
					
					p.setEmployeeId(rs.getInt("employeeId"));
					p.setTaskDate(rs.getString("taskDate"));
					p.setDescription(rs.getString("Description"));
					p.setCreatedDate(rs.getString("createdDate"));
					
					
					
					sta.add(p);
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
	public List<UserType> getRefenceByRefId(int RefId) {
		logger.info("+++++ GET FOLLOWUP EMPLOYEE BY FOLLOWUP ID +++++");
		List<UserType> sta = new ArrayList<UserType>();
		String sql = "select user_type_id,user_type_name from user_type where user_type_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, RefId);
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
	public List<Enquiry> getReferencesByDateId(String startRefDate, String endRefDate, int refTypeId, HttpSession session) {
		logger.info("+++++ GET ENQUIRIES BY PAGE IMPL +++++"+startRefDate+endRefDate);
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String 	sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name,u.email,u.mobile_number,ut.user_type_name  from enquiry e join  user u join user_type ut on ut.user_type_id=e.reference_id where e.status!=? and e.enquiry_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and e.client_id = u.user_id and e.reference_id=? order by e.enquiry_date desc";
		

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("from and to date is "+startRefDate+startRefDate);
			System.out.println("from and to date is "+sql);
			ps.setString(1, s);
			ps.setString(2, startRefDate);
			ps.setString(3, endRefDate);
	        ps.setInt(4, refTypeId);
	        
	        
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"),rs.getString("email"),rs.getString("mobile_number"),rs.getString("user_type_name"));
				
				System.out.println("+_)(_)_)_()_()_()_()_from and to date is "+startRefDate+startRefDate);
				
				sta.add(enquiry);
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
	

}
