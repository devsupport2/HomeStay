package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.AllCountDAO;
import com.ui.model.AllCount;

public class AllCountDAOImpl implements AllCountDAO {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(AllCountDAOImpl.class);

	public AllCount getAllCounts() {
		logger.info("+++++ GET ALL COUNTS +++++");
		AllCount allCount = null;
		Connection con = null;
		String sql = "select (select count(country_id) from country where status = 'y') as country_row_count, (select count(currency_id) from currency where status = 'y') as currency_row_count, (select count(financial_year_id) from financial_year where status = 'y') as financial_year_row_count, (select count(measurement_unit_id) from measurement_unit where status = 'y') as measurement_unit_row_count, (select count(state_id) from state where status='y' and state_id) as state_row_count, (select count(tax_id) from tax where status='y') as tax_row_count, (select count(user_id) from user where status='y') as user_row_count, (select count(brand_id) from brand where status='y') as brand_row_count, (select count(category_id) from category where status='y') as category_row_count, (select count(subcategory_id) from subcategory where status='y') as subcategory_row_count, (select count(product_id) from product where status='y') as product_row_count, (select count(enquiry_id) from enquiry where status!='n' && status!='cl' && status!='cw') as enquiry_row_count, (select count(quotation_id) from quotation where status='y') as quotation_row_count";
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				allCount = new AllCount(rs.getInt("country_row_count"), rs.getInt("currency_row_count"),
						rs.getInt("financial_year_row_count"), rs.getInt("measurement_unit_row_count"),
						rs.getInt("state_row_count"), rs.getInt("tax_row_count"), rs.getInt("user_row_count"),
						rs.getInt("brand_row_count"), rs.getInt("category_row_count"),
						rs.getInt("subcategory_row_count"), rs.getInt("product_row_count"),
						rs.getInt("enquiry_row_count"), rs.getInt("quotation_row_count"));
			}
			rs.close();
			ps.close();

			return allCount;
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
				}
			}
		}
	}
}
