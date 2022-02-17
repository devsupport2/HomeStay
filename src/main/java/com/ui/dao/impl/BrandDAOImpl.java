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
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.BrandDAO;
import com.ui.model.Brand;

public class BrandDAOImpl implements BrandDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(BrandDAOImpl.class);
	@Override
	public List<Brand> getAllBrands() {
		logger.info("+++++ GET ALL BRAND +++++");
		List<Brand> sta = new ArrayList<Brand>();
		String s = "y";
		String sql = "select brand_id, brand_name, logo, description, status, created_by, created_date, ip_address from brand where status=? order by brand_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Brand brand = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				brand = new Brand(rs.getInt("brand_id"), rs.getString("brand_name"), rs.getString("logo"),
						rs.getString("description"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"));
				sta.add(brand);
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
	public List<Brand> getAllBrandsByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL BRANDS BY PAGE +++++");
		List<Brand> sta = new ArrayList<Brand>();
		String s = "y";
		String sql = "select brand_id, brand_name, logo, description, status, created_by, created_date, ip_address from brand where status=? order by brand_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Brand brand = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				brand = new Brand(rs.getInt("brand_id"), rs.getString("brand_name"), rs.getString("logo"),
						rs.getString("description"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"));
				sta.add(brand);
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
	public List<Brand> searchBrands(String keyword) {
		logger.info("+++++ SEARCH BRANDS +++++");
		List<Brand> sta = new ArrayList<Brand>();
		String s = "y";
		String sql = "select brand_id, brand_name, logo, description, status, created_by, created_date, ip_address from brand where status=? and Concat(brand_name) like ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Brand brand = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				brand = new Brand(rs.getInt("brand_id"), rs.getString("brand_name"), rs.getString("logo"),
						rs.getString("description"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"));
				sta.add(brand);
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
	public void addBrand(Brand b) {
		logger.info("+++++ ADD BRAND +++++");
		String sql = "insert into brand (brand_name, logo, description, status, created_by, ip_address) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, b.getBrandName());
			ps.setString(2, b.getLogo());
			ps.setString(3, b.getDescription());
			ps.setString(4, b.getStatus());
			ps.setInt(5, b.getCreatedBy());
			ps.setString(6, b.getIpAddress());
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
	public void editBrand(Brand b) {
		logger.info("+++++ EDIT BRAND +++++");
		String sql = "update brand set brand_name=?, logo=?, description=?, created_by=?, ip_address=? where brand_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, b.getBrandName());
			ps.setString(2, b.getLogo());
			ps.setString(3, b.getDescription());
			ps.setInt(4, b.getCreatedBy());
			ps.setString(5, b.getIpAddress());
			ps.setInt(6, b.getBrandId());
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
	public void deleteBrand(int brandid) {
		logger.info("+++++ DELETE BRAND +++++");
		String status = "n";
		String sql = "update brand set status=? where brand_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, brandid);
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
