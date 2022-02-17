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

import com.ui.dao.SubcategoryDAO;
import com.ui.model.Subcategory;

public class SubcategoryDAOImpl implements SubcategoryDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(SubcategoryDAOImpl.class);

	@Override
	public List<Subcategory> getAllSubcategories() {
		logger.info("+++++ GET ALL SUBCATEGORY +++++");
		List<Subcategory> sta = new ArrayList<Subcategory>();
		String s = "y";
		String sql = "select s.subcategory_id, s.subcategory_name, s.subcategory_code, s.image, s.description, s.category_id, s.status, s.created_by, s.created_date, s.ip_address, c.category_name from subcategory s, category c where s.category_id=c.category_id and s.status=? order by s.subcategory_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			Subcategory subcategory = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subcategory = new Subcategory(rs.getInt("subcategory_id"), rs.getString("subcategory_name"),
						rs.getString("subcategory_code"), rs.getString("image"), rs.getString("description"),
						rs.getInt("category_id"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"), rs.getString("category_name"));

				sta.add(subcategory);
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
	public List<Subcategory> getAllSubcategoriesByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL SUBCATEGORY BY PAGE +++++");
		List<Subcategory> sta = new ArrayList<Subcategory>();
		String s = "y";
		String sql = "select s.subcategory_id, s.subcategory_name, s.subcategory_code, s.image, s.description, s.category_id, s.status, s.created_by, s.created_date, s.ip_address, c.category_name from subcategory s, category c where s.category_id=c.category_id and s.status=? order by s.subcategory_name limit ?,?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Subcategory subcategory = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subcategory = new Subcategory(rs.getInt("subcategory_id"), rs.getString("subcategory_name"),
						rs.getString("subcategory_code"), rs.getString("image"), rs.getString("description"),
						rs.getInt("category_id"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"), rs.getString("category_name"));

				sta.add(subcategory);
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
	public List<Subcategory> searchSubcategories(String keyword) {
		logger.info("+++++ SERACH SUBCATEGORY +++++");
		List<Subcategory> sta = new ArrayList<Subcategory>();
		String s = "y";
		String sql = "select s.subcategory_id, s.subcategory_name, s.subcategory_code, s.image, s.description, s.category_id, s.status, s.created_by, s.created_date, s.ip_address, c.category_name from subcategory s, category c where s.category_id=c.category_id and s.status=? and Concat(s.subcategory_name, '', s.subcategory_code) like ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');

			Subcategory subcategory = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subcategory = new Subcategory(rs.getInt("subcategory_id"), rs.getString("subcategory_name"),
						rs.getString("subcategory_code"), rs.getString("image"), rs.getString("description"),
						rs.getInt("category_id"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"), rs.getString("category_name"));

				sta.add(subcategory);
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
	public List<Subcategory> getSubcategoriesByCategoryId(int categoryid) {
		logger.info("+++++ GET SUBCATEGORY BY CATEGORYID +++++");
		List<Subcategory> sta = new ArrayList<Subcategory>();
		String s = "y";
		String sql = "select s.subcategory_id, s.subcategory_name, s.subcategory_code, s.image, s.description, s.category_id, s.status, s.created_by, s.created_date, s.ip_address, c.category_name from subcategory s, category c where s.category_id=c.category_id and s.category_id=? and s.status=? order by s.subcategory_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, categoryid);
			ps.setString(2, s);

			Subcategory subcategory = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subcategory = new Subcategory(rs.getInt("subcategory_id"), rs.getString("subcategory_name"),
						rs.getString("subcategory_code"), rs.getString("image"), rs.getString("description"),
						rs.getInt("category_id"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"), rs.getString("category_name"));

				sta.add(subcategory);
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
	public String addSubcategory(Subcategory c) {
		logger.info("+++++ ADD SUBCATEGORY +++++");
		String sql = "insert into subcategory (subcategory_name, subcategory_code, image, description, category_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getSubcategoryName());
			ps.setString(2, c.getSubcategoryCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setInt(5, c.getCategoryId());
			ps.setString(6, c.getStatus());
			ps.setInt(7, c.getCreatedBy());
			ps.setString(8, c.getIpAddress());
			ps.executeUpdate();
			
			return "Success";
		} catch (SQLException e) {
			return "Data not saved! Subcategory code already exist! Please submit your data with another code!";
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
	public String editSubcategory(Subcategory c) {
		logger.info("+++++ EDIT SUBCATEGORY +++++");
		String sql = "update subcategory set subcategory_name=?, subcategory_code=?, image=?, description=?, category_id=?, created_by=?, ip_address=? where subcategory_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getSubcategoryName());
			ps.setString(2, c.getSubcategoryCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setInt(5, c.getCategoryId());
			ps.setInt(6, c.getCreatedBy());
			ps.setString(7, c.getIpAddress());
			ps.setInt(8, c.getSubcategoryId());
			ps.executeUpdate();
			
			return "Success";
		} catch (SQLException e) {
			return "Data not saved! Subcategory code already exist! Please submit your data with another code!";
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
	public void deleteSubcategory(int subcategoryid) {
		logger.info("+++++ DELETE SUBCATEGORY +++++");
		String status = "n";
		String sql = "update subcategory set status=? where subcategory_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, subcategoryid);
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
