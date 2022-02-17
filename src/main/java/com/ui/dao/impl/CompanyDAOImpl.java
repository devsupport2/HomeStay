package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ui.dao.CompanyDAO;
import com.ui.model.Company;

public class CompanyDAOImpl implements CompanyDAO {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(CompanyDAOImpl.class);

	@Override
	public List<Company> getAllCompany() {
		logger.info("+++++ GET ALL COMPANY +++++");
		List<Company> sta = new ArrayList<Company>();
		String s = "y";
		String sql = "select company_id, company_name, company_logo, gst_no, contact_no1, contact_no2, fax_no, website, add1, add2, country_id, state_id, city, pincode from company where status=? order by company_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Company company = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = new Company(rs.getInt("company_id"), rs.getString("company_name"),
						rs.getString("company_logo"), rs.getString("gst_no"), rs.getString("contact_no1"),
						rs.getString("contact_no2"), rs.getString("fax_no"), rs.getString("website"),
						rs.getString("add1"), rs.getString("add2"), rs.getInt("country_id"), rs.getInt("state_id"),
						rs.getString("city"), rs.getString("pincode"));
				sta.add(company);
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
	public List<Company> getAllCompanyTitle() {
		logger.info("+++++ GET ALL COMPANY TITLES +++++");
		List<Company> sta = new ArrayList<Company>();
		String s = "y";
		String sql = "select company_id, company_name from company where status=? order by company_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Company company = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = new Company(rs.getInt("company_id"), rs.getString("company_name"));
				sta.add(company);
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
	public List<Company> getCompanyByPage(int pagesize, int startindex) {
		logger.info("+++++ GET COMPANY BY PAGE +++++");
		List<Company> sta = new ArrayList<Company>();
		String s = "y";
		String sql = "select company_id, company_name, company_logo, gst_no, contact_no1, contact_no2, fax_no, website, add1, add2, country_id, state_id, city, pincode from company where status=? order by company_name limit ?,?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Company company = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = new Company(rs.getInt("company_id"), rs.getString("company_name"),
						rs.getString("company_logo"), rs.getString("gst_no"), rs.getString("contact_no1"),
						rs.getString("contact_no2"), rs.getString("fax_no"), rs.getString("website"),
						rs.getString("add1"), rs.getString("add2"), rs.getInt("country_id"), rs.getInt("state_id"),
						rs.getString("city"), rs.getString("pincode"));
				sta.add(company);
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
	public List<Company> searchCompany(String keyword) {
		logger.info("+++++ SEARCH COMPANY +++++");
		List<Company> sta = new ArrayList<Company>();
		String s = "y";
		String sql = "select company_id, company_name, company_logo, gst_no, contact_no1, contact_no2, fax_no, website, add1, add2, country_id, state_id, city, pincode from company where status=? and concat(company_name, '', gst_no, '', city) like ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Company company = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = new Company(rs.getInt("company_id"), rs.getString("company_name"),
						rs.getString("company_logo"), rs.getString("gst_no"), rs.getString("contact_no1"),
						rs.getString("contact_no2"), rs.getString("fax_no"), rs.getString("website"),
						rs.getString("add1"), rs.getString("add2"), rs.getInt("country_id"), rs.getInt("state_id"),
						rs.getString("city"), rs.getString("pincode"));
				sta.add(company);
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
	public Company getCompanyById(int companyid) {
		logger.info("+++++ GET COMPANY BY ID +++++");
		Company company = null;
		String sql = "select company_id, company_name, company_logo, gst_no, contact_no1, contact_no2, fax_no, website, email, bank_name, bank_add, account_number, ifsc_code, swift_code, pan_no, add1, add2, country_id, state_id, city, pincode from company where company_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, companyid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				company = new Company(rs.getInt("company_id"), rs.getString("company_name"),
						rs.getString("company_logo"), rs.getString("gst_no"), rs.getString("contact_no1"),
						rs.getString("contact_no2"), rs.getString("fax_no"), rs.getString("website"),
						rs.getString("email"), rs.getString("bank_name"), rs.getString("bank_add"),
						rs.getString("account_number"), rs.getString("ifsc_code"), rs.getString("swift_code"),
						rs.getString("pan_no"), rs.getString("add1"), rs.getString("add2"), rs.getInt("country_id"),
						rs.getInt("state_id"), rs.getString("city"), rs.getString("pincode"));
			}
			rs.close();
			ps.close();

			return company;
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
	public void addCompany(Company c) {
		logger.info("+++++ ADD COMPANY +++++");
		String sql = "insert into company (company_name, company_logo, gst_no, contact_no1, contact_no2, fax_no, website, email, bank_name, bank_add, account_number, ifsc_code, swift_code, pan_no, add1, add2, city, state_id, country_id, pincode, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getCompanyName());
			ps.setString(2, c.getCompanyLogo());
			ps.setString(3, c.getGstNo());
			ps.setString(4, c.getContactNo1());
			ps.setString(5, c.getContactNo2());
			ps.setString(6, c.getFaxNo());
			ps.setString(7, c.getWebsite());
			ps.setString(8, c.getEmail());
			ps.setString(9, c.getBankName());
			ps.setString(10, c.getBankAdd());
			ps.setString(11, c.getAccountNumber());
			ps.setString(12, c.getIfscCode());
			ps.setString(13, c.getSwiftCode());
			ps.setString(14, c.getPanNo());
			ps.setString(15, c.getAdd1());
			ps.setString(16, c.getAdd2());
			ps.setString(17, c.getCity());
			ps.setInt(18, c.getCountryId());
			ps.setInt(19, c.getStateId());
			ps.setString(20, c.getPincode());
			ps.setString(21, c.getStatus());
			ps.setInt(22, c.getCreatedBy());
			ps.setString(23, c.getIpAddress());
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
	public void editCompany(Company c) {
		logger.info("+++++ EDIT COMPANY +++++");
		String sql = "update company set company_name=?, company_logo=?, gst_no=?, contact_no1=?, contact_no2=?, fax_no=?, website=?, email=?, bank_name=?, bank_add=?, account_number=?, ifsc_code=?, swift_code=?, pan_no=?, add1=?, add2=?, city=?, state_id=?, country_id=?, pincode=?, created_by=?, ip_address=? where company_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getCompanyName());
			ps.setString(2, c.getCompanyLogo());
			ps.setString(3, c.getGstNo());
			ps.setString(4, c.getContactNo1());
			ps.setString(5, c.getContactNo2());
			ps.setString(6, c.getFaxNo());
			ps.setString(7, c.getWebsite());
			ps.setString(8, c.getEmail());
			ps.setString(9, c.getBankName());
			ps.setString(10, c.getBankAdd());
			ps.setString(11, c.getAccountNumber());
			ps.setString(12, c.getIfscCode());
			ps.setString(13, c.getSwiftCode());
			ps.setString(14, c.getPanNo());
			ps.setString(15, c.getAdd1());
			ps.setString(16, c.getAdd2());
			ps.setString(17, c.getCity());
			ps.setInt(18, c.getCountryId());
			ps.setInt(19, c.getStateId());
			ps.setString(20, c.getPincode());
			ps.setInt(21, c.getCreatedBy());
			ps.setString(22, c.getIpAddress());
			ps.setInt(23, c.getCompanyId());

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
	public void deleteCompany(int companyid) {
		logger.info("+++++ DELETE COMPANY +++++");
		String status = "n";
		String sql = "update company set status=? where company_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, companyid);

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
}
