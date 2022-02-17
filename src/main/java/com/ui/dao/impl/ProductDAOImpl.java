package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ui.dao.ProductDAO;
import com.ui.model.PriceType;
import com.ui.model.Product;
import com.ui.model.ProductPrice;
import com.ui.model.ProductSaleType;
import com.ui.model.ProductScopeOfSupply;
import com.ui.model.ProductSpecifications;
import com.ui.model.ProductSupplier;
import com.ui.model.ProductTax;

public class ProductDAOImpl implements ProductDAO {
	JdbcTemplate jdbcTemplate;

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

	@Override
	public Map<String, ArrayList<Product>> getAllProductsForTally() {
		logger.info("Inside Get All Products Impl");
		Map<String, ArrayList<Product>> map = new HashMap<String, ArrayList<Product>>();
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name, part_number, description, brand_id, category_id, subcategory_id, product_sale_type_id, unit_id, hsn, status, created_by, created_date, ip_address from product where status=? order by product_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Product product = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("part_number"), rs.getString("description"), rs.getInt("brand_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("product_sale_type_id"),
						rs.getInt("unit_id"), rs.getInt("hsn"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"));

				sta.add(product);
				map.put("Products", (ArrayList<Product>) sta);
			}
			rs.close();
			ps.close();

			return map;
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
	public List<Product> getAllProducts() {
		logger.info("+++++ GET ALL PRODUCTS IMPL +++++");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
			//String sql = "select p.product_id, p.product_name, p.part_number, p.description, p.brand_id, p.category_id, p.subcategory_id, p.product_sale_type_id, p.unit_id, p.hsn, c.category_name, u.measurement_unit_name from product p, category c, measurement_unit u where p.status=? and c.category_id = p.category_id and u.measurement_unit_id = p.unit_id order by p.product_name";
		String sql="select product_id,product_name,part_number,description,brand_id,category_id,subcategory_id,product_sale_type_id,hsn,unit_id,status,created_by,created_date,ip_address from product where status=? ";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Product product = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("part_number"), rs.getString("description"), rs.getInt("brand_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("product_sale_type_id"),
						rs.getInt("unit_id"), rs.getInt("hsn")
						);
				sta.add(product);
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
	public List<Product> getProductName() {
		logger.info("+++++ GET PRODUCT NAME +++++");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name from product where status=? order by product_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Product product = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"));
				sta.add(product);
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
	public List<ProductSaleType> getAllProductSaleType() {
		logger.info("Inside Get All Product Sale Type Impl");
		List<ProductSaleType> sta = new ArrayList<ProductSaleType>();
		String s = "y";
		String sql = "select product_sale_type_id, product_sale_type_name, description from product_sale_type where status=? order by product_sale_type_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ProductSaleType productSaleType = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productSaleType = new ProductSaleType(rs.getInt("product_sale_type_id"),
						rs.getString("product_sale_type_name"), rs.getString("description"));

				sta.add(productSaleType);
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
	public List<PriceType> getAllPriceType() {
		logger.info("Inside Get All Price Type Impl");
		List<PriceType> sta = new ArrayList<PriceType>();
		String s = "y";
		String sql = "select price_type_id, price_type_name from price_type where status=? order by price_type_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			PriceType priceType = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				priceType = new PriceType(rs.getInt("price_type_id"), rs.getString("price_type_name"));

				sta.add(priceType);
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
	public List<Product> getAllProductsByPage(int pagesize, int startindex) {
		logger.info("Inside Get All Products By Page Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		//String sql = "select product_id, product_name, part_number, description, brand_id, category_id, subcategory_id, product_sale_type_id, unit_id, hsn, status, created_by, created_date, ip_address from product where status=? order by product_name limit ?,?";
			
		
		String sql="select product_id, product_name, part_number, description, brand_id, category_id, subcategory_id, product_sale_type_id, unit_id, hsn, status, created_by, created_date, ip_address from product where status=? order by product_id DESC";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			//ps.setInt(2, startindex);
			//ps.setInt(3, pagesize);

			Product product = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("part_number"), rs.getString("description"), rs.getInt("brand_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("product_sale_type_id"),
						rs.getInt("unit_id"), rs.getInt("hsn"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"));

				sta.add(product);
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
	public List<Product> searchProducts(String keyword) {
		logger.info("Inside Search Products Impl");
		List<Product> sta = new ArrayList<Product>();
		String s = "y";
		String sql = "select product_id, product_name, part_number, description, brand_id, category_id, subcategory_id, product_sale_type_id, unit_id, hsn, status, created_by, created_date, ip_address from product where status=? and Concat(product_name) like ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Product product = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("part_number"), rs.getString("description"), rs.getInt("brand_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("product_sale_type_id"),
						rs.getInt("unit_id"), rs.getInt("hsn"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("ip_address"));

				sta.add(product);
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
	public List<ProductScopeOfSupply> getProductScopeOfSupplyByProductId(int productid) {
		logger.info("+++++ GET PRODUCCT SCOPE OF SUPPLY BY PRODUCT ID +++++");
		List<ProductScopeOfSupply> sta = new ArrayList<ProductScopeOfSupply>();
		String sql = "select ps.scope_of_supply_id, ps.product_id, ps.sequence, ps.particular, ps.value, ps.unit_id, u.measurement_unit_name from product_scope_of_supply ps, measurement_unit u where ps.product_id=? and ps.unit_id = u.measurement_unit_id order by ps.sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
			ProductScopeOfSupply productScopeOfSupply = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				productScopeOfSupply = new ProductScopeOfSupply(rs.getInt("scope_of_supply_id"),
						rs.getInt("product_id"), rs.getInt("sequence"), rs.getString("particular"),
						rs.getString("value"), rs.getInt("unit_id"), rs.getString("measurement_unit_name"));

				sta.add(productScopeOfSupply);
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
	public List<ProductSpecifications> getProductSpecificationsByProductId(int productid) {
		logger.info("Inside Get Product Specifications By Product Id Impl");
		List<ProductSpecifications> sta = new ArrayList<ProductSpecifications>();
		String sql = "select ps.product_specification_id, ps.product_id, ps.sequence, ps.specification, ps.spec_value, ps.unit_id, u.measurement_unit_name from product_specifications ps left join measurement_unit u on ps.unit_id = u.measurement_unit_id where ps.product_id=? order by ps.sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
			ProductSpecifications productSpecifications = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				productSpecifications = new ProductSpecifications(rs.getInt("product_specification_id"),
						rs.getInt("product_id"), rs.getInt("sequence"), rs.getString("specification"),
						rs.getString("spec_value"), rs.getInt("unit_id"), rs.getString("measurement_unit_name"));

				sta.add(productSpecifications);
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
	public List<ProductSupplier> getProductSupplierByProductId(int productid) {
		logger.info("Inside Get Product Supplier By Product Id Impl");
		List<ProductSupplier> sta = new ArrayList<ProductSupplier>();
		String sql = "select ps.product_supplier_id, ps.product_id, concat(u.first_name,' ',u.last_name) as supplier_name, ps.supplier_id from product_supplier ps, user u where ps.product_id=? and u.user_id = ps.supplier_id order by ps.product_supplier_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
			ProductSupplier productSupplier = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				productSupplier = new ProductSupplier(rs.getInt("product_supplier_id"), rs.getInt("product_id"),
						rs.getString("supplier_name"), rs.getInt("supplier_id"));

				sta.add(productSupplier);
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
	public List<ProductPrice> getProductPriceByProductId(int productid) {
		logger.info("Inside Get Product Price By Product Id Impl");
		List<ProductPrice> sta = new ArrayList<ProductPrice>();
		String sql = "select pp.product_price_id, pp.product_id, pp.price_type_id, pp.price, pp.currency_id, pt.price_type_name, c.currency_code from product_price pp, price_type pt, currency c where pp.product_id=? and pt.price_type_id = pp.price_type_id and c.currency_id = pp.currency_id order by pp.product_price_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
			ProductPrice productPrice = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				productPrice = new ProductPrice(rs.getInt("product_price_id"), rs.getInt("product_id"),
						rs.getInt("price_type_id"), rs.getFloat("price"), rs.getInt("currency_id"),
						rs.getString("price_type_name"), rs.getString("currency_code"));

				sta.add(productPrice);
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
	public List<ProductTax> getProductTaxByProductId(int productid) {
		logger.info("+++++ GET PRODUCT TAX BY PRODUCT ID +++++");
		List<ProductTax> sta = new ArrayList<ProductTax>();
		String sql = "select pt.product_tax_id, pt.tax_id, pt.rate, pt.product_id, pt.created_by, pt.created_date, pt.ip_address, t.tax_name from product_tax pt, product p, tax t where pt.product_id=p.product_id and pt.tax_id=t.tax_id and pt.product_id=? group by pt.tax_id order by pt.product_tax_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
			ProductTax productTax = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productTax = new ProductTax(rs.getInt("product_tax_id"), rs.getInt("tax_id"), rs.getFloat("rate"),
						rs.getInt("product_id"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("ip_address"), rs.getString("tax_name"));

				sta.add(productTax);
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
	public String addProduct(Product p) {
		logger.info("+++++ ADD PRODUCT +++++");
		String sql = "insert into product (product_name, part_number, description, brand_id, category_id, subcategory_id, product_sale_type_id, unit_id, hsn, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.setString(2, p.getPartNumber());
			ps.setString(3, p.getDescription());
			ps.setInt(4, p.getBrandId());
			ps.setInt(5, p.getCategoryId());
			ps.setInt(6, p.getSubcategoryId());
			ps.setInt(7, p.getProductSaleTypeId());
			ps.setInt(8, p.getUnitId());
			ps.setInt(9, p.getHsn());
			ps.setString(10, p.getStatus());
			ps.setInt(11, p.getCreatedBy());
			ps.setString(12, p.getIpAddress());
			ps.executeUpdate();

			return "Success";
		} catch (SQLException e) {
			return "Data not saved! Product serial number already exist! Please submit data with another serial number!";
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
	public int getLastProductId() {
		logger.info("Inside Get Last Product Id Impl");

		String sql = "select product_id from product order by product_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("product_id");
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
	public void addProductScopeOfSupply(ProductScopeOfSupply p) {
		logger.info("+++++ ADD PRODUCT SCOPE OF SUPPLY +++++");
		String sql = "insert into product_scope_of_supply (product_id, sequence, particular, value, unit_id, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, p.getProductId());
			ps.setInt(2, p.getSequence());
			ps.setString(3, p.getParticular());
			ps.setString(4, p.getValue());
			ps.setInt(5, p.getUnitId());
			ps.setInt(6, p.getCreatedBy());
			ps.setString(7, p.getIpAddress());

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
	public void addProductSpecification(ProductSpecifications p) {
		logger.info("+++++ ADD PRODUCT SPECIFICATION +++++");

		String sql = "insert into product_specifications (product_id, sequence, specification, spec_value, unit_id, created_by, ip_address) values (?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, p.getProductId());
			ps.setInt(2, p.getSequence());
			ps.setString(3, p.getSpecification());
			ps.setString(4, p.getSpecValue());
			ps.setInt(5, p.getUnitId());
			ps.setInt(6, p.getCreatedBy());
			ps.setString(7, p.getIpAddress());

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
	public void addProductSupplier(ProductSupplier p) {
		logger.info("Inside Add Product Supplier Impl");

		String sql = "insert into product_supplier (product_id, supplier_id, created_by, ip_address) values (?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, p.getProductId());
			ps.setInt(2, p.getSupplierId());
			ps.setInt(3, p.getCreatedBy());
			ps.setString(4, p.getIpAddress());

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
	public void addProductPrice(ProductPrice p) {
		logger.info("Inside Add Product Price Impl");

		String sql = "insert into product_price (product_id, price_type_id, price, currency_id, created_by, ip_address) values (?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, p.getProductId());
			ps.setInt(2, p.getPriceTypeId());
			ps.setFloat(3, p.getPrice());
			ps.setInt(4, p.getCurrencyId());
			ps.setInt(5, p.getCreatedBy());
			ps.setString(6, p.getIpAddress());

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
	public void addProductTax(ProductTax p) {
		logger.info("+++++ ADD PRODUCT TAX +++++");
		String sql = "insert into product_tax (tax_id, rate, product_id, created_by, ip_address) values (?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getTaxId());
			ps.setFloat(2, p.getRate());
			ps.setInt(3, p.getProductId());
			ps.setInt(4, p.getCreatedBy());
			ps.setString(5, p.getIpAddress());
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
	public void deleteProductScopeOfSupply(int productid) {
		logger.info("+++++ DELETE PRODUCT SCOPE OF SUPPLY +++++");
		String sql = "delete from product_scope_of_supply where product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
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
	public void deleteProductSpecifications(int productid) {
		logger.info("Inside Delete Product Specifications Impl");
		String sql = "delete from product_specifications where product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, productid);

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
	public void deleteProductSupplier(int productid) {
		logger.info("Inside Delete Product Supplier Impl");
		String sql = "delete from product_supplier where product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
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
	public void deleteProductPrice(int productid) {
		logger.info("Inside Delete Product Price Impl");
		String sql = "delete from product_price where product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
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
	public void deleteProductTax(int productid) {
		logger.info("Inside Delete Product Tax Impl");

		String sql = "delete from product_tax where product_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, productid);

			ps.executeUpdate();
		}

		catch (SQLException e) {
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
	public String editProduct(Product p) {
		logger.info("+++++ EDIT PRODUCT +++++");
		String sql = "update product set product_name=?, part_number=?, description=?, brand_id=?, category_id=?, subcategory_id=?, product_sale_type_id=?, unit_id=?, hsn=?, created_by=?, ip_address=? where product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.setString(2, p.getPartNumber());
			ps.setString(3, p.getDescription());
			ps.setInt(4, p.getBrandId());
			ps.setInt(5, p.getCategoryId());
			ps.setInt(6, p.getSubcategoryId());
			ps.setInt(7, p.getProductSaleTypeId());
			ps.setInt(8, p.getUnitId());
			ps.setInt(9, p.getHsn());
			ps.setInt(10, p.getCreatedBy());
			ps.setString(11, p.getIpAddress());
			ps.setInt(12, p.getProductId());
			ps.executeUpdate();

			return "Success";
		} catch (SQLException e) {
			return "Data not saved! Product serial number already exist! Please submit data with another serial number!";
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
	public void deleteProduct(int productid) {
		logger.info("Inside Delete Product Impl");

		String status = "n";

		String sql = "update product set status=? where product_id=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, productid);

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

	public Product getProductDetailById(int productid) {
		logger.info("+++++ PRODUCT DETAIL BY ID IMPL +++++");
		Product product = null;
		String sql = "select product_id, product_name, part_number, description, brand_id, category_id, subcategory_id, product_sale_type_id, unit_id, hsn from product where product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("part_number"), rs.getString("description"), rs.getInt("brand_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("product_sale_type_id"),
						rs.getInt("unit_id"), rs.getInt("hsn"));
			}
			rs.close();
			ps.close();
			return product;
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
	public void addXmlProduct(Product p) {
		logger.info("Inside Add XML Product Impl");
		String sql = "insert into product (product_name, part_number, status, created_by, ip_address) values (?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.setString(2, p.getPartNumber());
			ps.setString(3, p.getStatus());
			ps.setInt(4, p.getCreatedBy());
			ps.setString(5, p.getIpAddress());
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

	public Product getProductDetailWithSalePriceById(int productid) {
		logger.info("+++++ PRODUCT DETAIL WITH SALE PRICE BY ID IMPL +++++");
		Product product = null;
		int pricetypeid = 2;
		String sql = "select p.product_id, p.product_name, p.part_number, p.description, p.brand_id, p.category_id, p.subcategory_id, p.product_sale_type_id, p.unit_id, p.hsn, pp.price, u.measurement_unit_name as unit_name from product p, product_price pp, measurement_unit u where p.product_id=? and pp.product_id=? and pp.price_type_id=? and p.unit_id = u.measurement_unit_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);
			ps.setInt(2, productid);
			ps.setInt(3, pricetypeid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("part_number"), rs.getString("description"), rs.getInt("brand_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("product_sale_type_id"),
						rs.getInt("unit_id"), rs.getInt("hsn"), rs.getFloat("price"), rs.getString("unit_name"));
			}
			rs.close();
			ps.close();
			return product;
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

	public ProductPrice getProductPriceById(int pricetypeid, int productid) {
		logger.info("+++++ PRODUCT DETAIL WITH SALE PRICE BY ID IMPL +++++");
		ProductPrice productPrice = null;
		String sql = "select product_price_id, price, currency_id from product_price where price_type_id=? and product_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pricetypeid);
			ps.setInt(2, productid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productPrice = new ProductPrice(rs.getInt("product_price_id"), rs.getFloat("price"),
						rs.getInt("currency_id"));
			}
			rs.close();
			ps.close();
			return productPrice;
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
	public List<Product> getDealerProductByDealerId(int userid) {
		logger.info("+++++ GET DEALER PRODUCT BY DEALER ID +++++");
		List<Product> sta = new ArrayList<Product>();
		String sql = "select dp.product_id, p.product_name, p.part_number from dealer_product dp left join product p on dp.product_id = p.product_id where dp.user_id=? order by p.product_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			Product product = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("part_number"));
				sta.add(product);
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
