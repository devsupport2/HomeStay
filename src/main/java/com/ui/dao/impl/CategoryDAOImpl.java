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

import com.ui.dao.CategoryDAO;
import com.ui.model.Category;

public class CategoryDAOImpl implements CategoryDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@Override
	public List<Category> getAllCategories() {
		logger.info("+++++ GET ALL CATEGORY +++++");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, status, created_by, created_date, ip_address from category where status=? order by category_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			Category category = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				category = new Category(rs.getInt("category_id"), rs.getString("category_name"),
						rs.getString("category_code"), rs.getString("image"), rs.getString("description"),
						rs.getString("status"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("ip_address"));

				sta.add(category);
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
	public List<Category> getAllCategoriesByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL CATEGORY BY PAGE +++++");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, status, created_by, created_date, ip_address from category where status=? order by category_name limit ?,?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Category category = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				category = new Category(rs.getInt("category_id"), rs.getString("category_name"),
						rs.getString("category_code"), rs.getString("image"), rs.getString("description"),
						rs.getString("status"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("ip_address"));

				sta.add(category);
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
	public List<Category> searchCategories(String keyword) {
		logger.info("+++++ SEARCH CATEGORY +++++");
		List<Category> sta = new ArrayList<Category>();
		String s = "y";
		String sql = "select category_id, category_name, category_code, image, description, status, created_by, created_date, ip_address from category where status=? and Concat(category_name, '', category_code) like ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');

			Category category = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				category = new Category(rs.getInt("category_id"), rs.getString("category_name"),
						rs.getString("category_code"), rs.getString("image"), rs.getString("description"),
						rs.getString("status"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("ip_address"));

				sta.add(category);
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
	public String addCategory(Category c) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into category (category_name, category_code, image, description, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getCategoryName());
			ps.setString(2, c.getCategoryCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setString(5, c.getStatus());
			ps.setInt(6, c.getCreatedBy());
			ps.setString(7, c.getIpAddress());

			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not saved! Category code already exist! Please submit your data with another code!";
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
	public String editCategory(Category c) {
		logger.info("+++++ EDIT CATEGORY +++++");
		String sql = "update category set category_name=?, category_code=?, image=?, description=?, created_by=?, ip_address=? where category_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getCategoryName());
			ps.setString(2, c.getCategoryCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setInt(5, c.getCreatedBy());
			ps.setString(6, c.getIpAddress());
			ps.setInt(7, c.getCategoryId());
			ps.executeUpdate();
			
			return "Success";
		} catch (SQLException e) {
			return "Data not saved! Category code already exist! Please submit your data with another code!";
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
	public void deleteCategory(int categoryid) {
		logger.info("+++++ DELETE CATEGORY +++++");
		String status = "n";
		String sql = "update category set status=? where category_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, categoryid);
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
