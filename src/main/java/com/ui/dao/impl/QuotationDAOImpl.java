package com.ui.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.ui.dao.QuotationDAO;
import com.ui.model.CoverLetter;
import com.ui.model.Quotation;
import com.ui.model.QuotationProduct;
import com.ui.model.QuotationProductScopeOfSupply;
import com.ui.model.QuotationProductSpecification;
import com.ui.model.QuotationTermStatement;
import com.ui.model.TermMaster;
import com.ui.model.TermStatement;

public class QuotationDAOImpl implements QuotationDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(QuotationDAOImpl.class);

	@Override
	public void editCoverLetter(CoverLetter c) {
		logger.info("+++++ EDIT COVER LETTER +++++");
		String sql = "update cover_letter set cover_letter_description=?, created_by=?, ip_address=? where cover_letter_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getCoverLetterDescription());
			ps.setInt(2, c.getCreatedBy());
			ps.setString(3, c.getIpAddress());
			ps.setInt(4, c.getCoverLetterId());
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
	public CoverLetter getCoverLetterDetailById(int id) {
		logger.info("+++++ GET COVER LETTER DETAIL BY ID +++++");
		String sql = "select cover_letter_id, cover_letter_title, cover_letter_description from cover_letter where cover_letter_id=?";

		Connection conn = null;
		CoverLetter coverLetter = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coverLetter = new CoverLetter(rs.getInt("cover_letter_id"), rs.getString("cover_letter_title"),
						URLDecoder.decode(rs.getString("cover_letter_description"), "UTF-8"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return coverLetter;
	}

	@Override
	public List<TermMaster> getAllTerm() {
		logger.info("+++++ GET ALL TERM +++++");
		List<TermMaster> sta = new ArrayList<TermMaster>();
		String s = "y";
		String sql = "select term_master_id, term_title from term_master where status=? order by term_master_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			TermMaster termMaster = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				termMaster = new TermMaster(rs.getInt("term_master_id"), rs.getString("term_title"));
				sta.add(termMaster);
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
	public void deleteTerm(int termid) {
		logger.info("+++++ DELETE TERM +++++");
		String status = "n";
		String sql = "update term_master set status=? where term_master_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, termid);
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
	public List<TermStatement> getTermStatementById(int termid) {
		logger.info("+++++ GET TERM STATEMENT BY ID +++++");
		List<TermStatement> sta = new ArrayList<TermStatement>();
		String sql = "select term_statement_id, term_master_id, sequence, label, statement from term_statement where term_master_id=? order by sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, termid);
			TermStatement termStatement = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				termStatement = new TermStatement(rs.getInt("term_statement_id"), rs.getInt("term_master_id"),
						rs.getInt("sequence"), rs.getString("label"), rs.getString("statement"));
				sta.add(termStatement);
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
	public void addTermItemRow(TermStatement t) {
		logger.info("+++++ ADD TERM ITEM ROW +++++");
		String sql = "insert into term_statement (term_master_id, sequence, label, statement, created_by, ip_address) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getTermMasterId());
			ps.setInt(2, t.getSequence());
			ps.setString(3, t.getLabel());
			ps.setString(4, t.getStatement());
			ps.setInt(5, t.getCreatedBy());
			ps.setString(6, t.getIpAddress());
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
	public void deleteTermItem(int termstatementid) {
		logger.info("+++++ DELETE TERM ITEM +++++");
		String sql = "delete from term_statement where term_statement_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, termstatementid);
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
	public void editTerm(TermMaster t) {
		logger.info("+++++ EDIT TERM +++++");
		String sql = "update term_master set term_title=?, created_by=?, ip_address=? where term_master_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTermTitle());
			ps.setInt(2, t.getCreatedBy());
			ps.setString(3, t.getIpAddress());
			ps.setInt(4, t.getTermMasterId());
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
	public List<TermStatement> getQuotationTermStatement() {
		logger.info("+++++ GET ALL TERM STATEMENT +++++");
		String s = "y";
		List<TermStatement> sta = new ArrayList<TermStatement>();
		String sql = "select s.term_statement_id, s.term_master_id, s.sequence, s.label, s.statement, t.term_title from term_statement s, term_master t where t.status = ? and t.term_master_id = s.term_master_id order by s.term_master_id and s.sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			TermStatement termStatement = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				termStatement = new TermStatement(rs.getInt("term_statement_id"), rs.getInt("term_master_id"),
						rs.getInt("sequence"), rs.getString("label"), rs.getString("statement"),
						rs.getString("term_title"));
				sta.add(termStatement);
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
	public Quotation getLastQuotationDetail() {
		logger.info("+++++ GET LAST QUOTATION DETAIL +++++");
		Quotation quotation = null;
		String s = "y";
		String sql = "select quotation_id, sequence, quotation_no from quotation where status = ? order by quotation_id desc limit 0,1";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotation = new Quotation(rs.getInt("quotation_id"), rs.getInt("sequence"),
						rs.getString("quotation_no"));
			}
			rs.close();
			ps.close();
			return quotation;
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
	public void createQuotation(Quotation q) {
		logger.info("+++++ CREATE QUOTATION +++++");
		String sql = "insert into quotation (sequence, enquiry_id, quotation_no, quotation_date, client_id, purchase_order, purchase_order_date, cover_letter, status, created_by, ip_address) values (?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, q.getSequence());
			ps.setInt(2, q.getEnquiryId());
			ps.setString(3, q.getQuotationNo());
			ps.setString(4, q.getQuotationDate());
			ps.setInt(5, q.getClientId());
			ps.setString(6, q.getPurchaseOrder());
			ps.setString(7, q.getPurchaseOrderDate());
			ps.setString(8, q.getCoverLetter());
			ps.setString(9, q.getStatus());
			ps.setInt(10, q.getCreatedBy());
			ps.setString(11, q.getIpAddress());
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
	public void addQuotationProduct(QuotationProduct q) {
		logger.info("+++++ ADD QUOTATION PRODUCT +++++");
		String sql = "insert into quotation_product (quotation_id, product_id, product_name, part_number, product_qty, sale_price, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, q.getQuotationId());
			ps.setInt(2, q.getProductId());
			ps.setString(3, q.getProductName());
			ps.setString(4, q.getPartNumber());
			ps.setFloat(5, q.getProductQty());
			ps.setFloat(6, q.getSalePrice());
			ps.setInt(7, q.getCreatedBy());
			ps.setString(8, q.getIpAddress());
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
	public void addQuotationProductScopeOfSupply(QuotationProductScopeOfSupply q) {
		logger.info("+++++ ADD QUOTATION PRODUCT SCOPE OF SUPPLY +++++");
		String sql = "insert into quotation_product_scope_of_supply (quotation_id, product_id, particular, value, qty, unit_name, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, q.getQuotationId());
			ps.setInt(2, q.getProductId());
			ps.setString(3, q.getParticular());
			ps.setString(4, q.getValue());
			ps.setFloat(5, q.getQty());
			ps.setString(6, q.getUnitName());
			ps.setInt(7, q.getCreatedBy());
			ps.setString(8, q.getIpAddress());
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
	public void addQuotationProductSpecification(QuotationProductSpecification q) {
		logger.info("+++++ ADD QUOTATION PRODUCT SPECIFICATION +++++");
		String sql = "insert into quotation_product_specification (quotation_id, product_id, specification, spec_value, unit_name, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, q.getQuotationId());
			ps.setInt(2, q.getProductId());
			ps.setString(3, q.getSpecification());
			ps.setString(4, q.getSpecValue());
			ps.setString(5, q.getUnitName());
			ps.setInt(6, q.getCreatedBy());
			ps.setString(7, q.getIpAddress());
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
	public void addQuotationTermStatement(QuotationTermStatement q) {
		logger.info("+++++ ADD QUOTATION TERM STATEMENT +++++");
		String sql = "insert into quotation_term_statement (quotation_id, term_master_id, term_title, sequence, label, statement, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, q.getQuotationId());
			ps.setInt(2, q.getTermMasterId());
			ps.setString(3, q.getTermTitle());
			ps.setInt(4, q.getSequence());
			ps.setString(5, q.getLabel());
			ps.setString(6, q.getStatement());
			ps.setInt(7, q.getCreatedBy());
			ps.setString(8, q.getIpAddress());
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
	public List<Quotation> getQuotationByPage(int pagesize, int startindex) {
		logger.info("+++++ GET QUOTATION BY PAGE +++++");
		List<Quotation> sta = new ArrayList<Quotation>();
		String s = "y";
		String sql = "select q.quotation_id, q.enquiry_id, q.quotation_no, DATE_FORMAT(q.quotation_date,'%d/%m/%Y') as quotation_date, u.user_company_name, u.first_name, u.middle_name, u.last_name from quotation q, user u where q.status=? and q.client_id = u.user_id order by q.quotation_no desc limit ?,?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Quotation quotation = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotation = new Quotation(rs.getInt("quotation_id"), rs.getInt("enquiry_id"),
						rs.getString("quotation_no"), rs.getString("quotation_date"), rs.getString("user_company_name"),
						rs.getString("first_name"), rs.getString("middle_name"), rs.getString("last_name"));

				sta.add(quotation);
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
	public List<Quotation> getAllQuotation() {
		logger.info("+++++ GET ALL QUOTATION +++++");
		List<Quotation> sta = new ArrayList<Quotation>();
		String s = "y";
		String sql = "select q.quotation_id, q.enquiry_id, q.quotation_no, DATE_FORMAT(q.quotation_date,'%d/%m/%Y') as quotation_date, u.user_company_name, u.first_name, u.middle_name, u.last_name from quotation q, user u where q.status=? and q.client_id = u.user_id order by q.quotation_no desc";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			Quotation quotation = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotation = new Quotation(rs.getInt("quotation_id"), rs.getInt("enquiry_id"),
						rs.getString("quotation_no"), rs.getString("quotation_date"), rs.getString("user_company_name"),
						rs.getString("first_name"), rs.getString("middle_name"), rs.getString("last_name"));

				sta.add(quotation);
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
	public List<Quotation> searchQuotation(String keyword) {
		logger.info("+++++ GET QUOTATION BY PAGE +++++");
		List<Quotation> sta = new ArrayList<Quotation>();
		String s = "y";
		String sql = "select q.quotation_id, q.enquiry_id, q.quotation_no, DATE_FORMAT(q.quotation_date,'%d/%m/%Y') as quotation_date, u.user_company_name, u.first_name, u.middle_name, u.last_name from quotation q, user u where q.status=? and q.client_id = u.user_id order by q.quotation_no desc  and concat(q.quotation_no,'',q.quotation_date,'',u.user_company_name,u.first_nam,'',u.middle_name,'',u.last_name) like ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Quotation quotation = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotation = new Quotation(rs.getInt("quotation_id"), rs.getInt("enquiry_id"),
						rs.getString("quotation_no"), rs.getString("quotation_date"), rs.getString("user_company_name"),
						rs.getString("first_name"), rs.getString("middle_name"), rs.getString("last_name"));

				sta.add(quotation);
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
	public void deleteQuotation(int quotationid) {
		logger.info("+++++ DELETE QUOTATION +++++");
		String status = "n";
		String sql = "update quotation set status=? where quotation_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, quotationid);
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
	public Quotation getQuotationDetailById(int quotationid) {
		logger.info("+++++ GET QUOTATION DETAIL BY ID +++++");
		Quotation quotation = null;

		String sql = "select quotation_id, enquiry_id, quotation_no, DATE_FORMAT(quotation_date,'%d/%m/%Y') as quotation_date, client_id, purchase_order, cover_letter from quotation where quotation_id = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quotationid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotation = new Quotation(rs.getInt("quotation_id"), rs.getInt("enquiry_id"),
						rs.getString("quotation_no"), rs.getString("quotation_date"), rs.getInt("client_id"),
						rs.getString("purchase_order"), rs.getString("cover_letter"));
			}
			rs.close();
			ps.close();

			return quotation;
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
	public List<QuotationProduct> getQuotationProductByQuotationId(int quotationid) {
		logger.info("+++++ GET QUOTATION PRODUCT BY QUOTATION ID +++++");
		List<QuotationProduct> sta = new ArrayList<QuotationProduct>();
		String sql = "select quotation_product_id, quotation_id, product_id, product_name, part_number, product_qty, sale_price from quotation_product where quotation_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quotationid);
			QuotationProduct quotationProduct = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotationProduct = new QuotationProduct(rs.getInt("quotation_product_id"), rs.getInt("quotation_id"),
						rs.getInt("product_id"), rs.getString("product_name"), rs.getString("part_number"),
						rs.getFloat("product_qty"), rs.getFloat("sale_price"));

				sta.add(quotationProduct);
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
	public List<QuotationProductSpecification> getQuotationProductSpecificationByQuotationId(int quotationid) {
		logger.info("+++++ GET QUOTATION PRODUCT SPECIFICATION BY QUOTATION ID +++++");
		List<QuotationProductSpecification> sta = new ArrayList<QuotationProductSpecification>();
		String sql = "select quotation_product_specification_id, quotation_id, product_id, specification, spec_value, unit_name from quotation_product_specification where quotation_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quotationid);
			QuotationProductSpecification quotationProductSpecification = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotationProductSpecification = new QuotationProductSpecification(
						rs.getInt("quotation_product_specification_id"), rs.getInt("quotation_id"),
						rs.getInt("product_id"), rs.getString("specification"), rs.getString("spec_value"),
						rs.getString("unit_name"));

				sta.add(quotationProductSpecification);
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
	public List<QuotationProductSpecification> getQuotationProductSpecificationByQuotationIdAndProductId(
			int quotationid, int productid) {
		logger.info("+++++ GET QUOTATION PRODUCT SPECIFICATION BY QUOTATION ID AND PRODUCT ID +++++");
		List<QuotationProductSpecification> sta = new ArrayList<QuotationProductSpecification>();
		String sql = "select quotation_product_specification_id, quotation_id, product_id, specification, spec_value, unit_name from quotation_product_specification where quotation_id=? and product_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quotationid);
			ps.setInt(2, productid);
			QuotationProductSpecification quotationProductSpecification = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotationProductSpecification = new QuotationProductSpecification(
						rs.getInt("quotation_product_specification_id"), rs.getInt("quotation_id"),
						rs.getInt("product_id"), rs.getString("specification"), rs.getString("spec_value"),
						rs.getString("unit_name"));

				sta.add(quotationProductSpecification);
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
	public List<QuotationProductScopeOfSupply> getQuotationProductScopeOfSupplyByQuotationId(int quotationid) {
		logger.info("+++++ GET QUOTATION PRODUCT SCOPE OF SUPPLY BY QUOTATION ID +++++");
		List<QuotationProductScopeOfSupply> sta = new ArrayList<QuotationProductScopeOfSupply>();
		String sql = "select quotation_scope_of_supply_id, quotation_id, product_id, particular, value, qty, unit_name from quotation_product_scope_of_supply where quotation_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quotationid);
			QuotationProductScopeOfSupply quotationProductScopeOfSupply = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotationProductScopeOfSupply = new QuotationProductScopeOfSupply(
						rs.getInt("quotation_scope_of_supply_id"), rs.getInt("quotation_id"), rs.getInt("product_id"),
						rs.getString("particular"), rs.getString("value"), rs.getFloat("qty"),
						rs.getString("unit_name"));

				sta.add(quotationProductScopeOfSupply);
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
	public List<QuotationProductScopeOfSupply> getQuotationProductScopeOfSupplyByQuotationIdAndProductId(
			int quotationid, int productid) {
		logger.info("+++++ GET QUOTATION PRODUCT SCOPE OF SUPPLY BY QUOTATION ID AND PRODUCT ID +++++");
		List<QuotationProductScopeOfSupply> sta = new ArrayList<QuotationProductScopeOfSupply>();
		String sql = "select quotation_scope_of_supply_id, quotation_id, product_id, particular, value, qty, unit_name from quotation_product_scope_of_supply where quotation_id=? and product_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quotationid);
			ps.setInt(2, productid);
			QuotationProductScopeOfSupply quotationProductScopeOfSupply = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotationProductScopeOfSupply = new QuotationProductScopeOfSupply(
						rs.getInt("quotation_scope_of_supply_id"), rs.getInt("quotation_id"), rs.getInt("product_id"),
						rs.getString("particular"), rs.getString("value"), rs.getFloat("qty"),
						rs.getString("unit_name"));

				sta.add(quotationProductScopeOfSupply);
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
	public List<QuotationTermStatement> getQuotationTermStatementByQuotationId(int quotationid, int termid) {
		logger.info("+++++ GET QUOTATION TERM STATEMENT BY QUOTATION ID +++++");
		List<QuotationTermStatement> sta = new ArrayList<QuotationTermStatement>();
		String sql = "select quotation_term_statement_id, quotation_id, term_master_id, term_title, sequence, label, statement from quotation_term_statement where quotation_id=? and term_master_id=? order by sequence";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, quotationid);
			ps.setInt(2, termid);
			QuotationTermStatement quotationTermStatement = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quotationTermStatement = new QuotationTermStatement(rs.getInt("quotation_term_statement_id"),
						rs.getInt("quotation_id"), rs.getInt("term_master_id"), rs.getString("term_title"),
						rs.getInt("sequence"), rs.getString("label"), rs.getString("statement"));

				sta.add(quotationTermStatement);
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
