package com.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.CategoryDAO;
import com.ui.dao.ProductDAO;
import com.ui.dao.SubcategoryDAO;
import com.ui.model.PriceType;
import com.ui.model.Product;
import com.ui.model.ProductImage;
import com.ui.model.ProductPrice;
import com.ui.model.ProductSaleType;
import com.ui.model.ProductScopeOfSupply;
import com.ui.model.ProductSpecifications;
import com.ui.model.ProductSupplier;
import com.ui.model.ProductTax;

@RestController
public class ProductController {
	@Autowired
	ProductDAO productDAO;

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	SubcategoryDAO subcategoryDAO;

	Product product;
	ProductImage productImage;
	ProductTax productTax;
	ProductSaleType productSaleType;
	PriceType priceType;
	ProductSpecifications productSpecifications;
	ProductPrice productPrice;
	ProductSupplier productSupplier;
	ProductScopeOfSupply productScopeOfSupply;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(value = "/getAllProductsForTally", method = RequestMethod.GET, produces = "application/json")
	public Map<String, ArrayList<Product>> getAllProductsForTally(HttpServletRequest request) {
		logger.info("Inside Get All Products Controller");
		Map<String, ArrayList<Product>> d = productDAO.getAllProductsForTally();
		return d;
	}

	@RequestMapping(value = "/getProducts", method = RequestMethod.GET, produces = "application/json")
	public List<Product> getProducts(HttpServletRequest request, HttpSession session) {
		logger.info("Inside Get All Products Controller");
		List<Product> product = new ArrayList<Product>();
		int id = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		if (id == 3) {
			product = productDAO.getDealerProductByDealerId(userid);
		} else {
			product = productDAO.getAllProducts();
		}
		return product;
	}

	@RequestMapping(value = "/getProductName", method = RequestMethod.GET, produces = "application/json")
	public List<Product> getProductName(HttpServletRequest request) {
		logger.info("***** GET PRODUCT NAME *****");
		List<Product> product = productDAO.getProductName();
		return product;
	}

	@RequestMapping(value = "/getProductDetailById", method = RequestMethod.GET, produces = "application/json")
	public Product getProductDetailById(int productid, HttpServletRequest request) {
		logger.info("***** PRODUCT DETAIL BY ID CONTROLLER *****");
		Product product = productDAO.getProductDetailById(productid);
		return product;
	}

	@RequestMapping(value = "/getProductSaleType", method = RequestMethod.GET, produces = "application/json")
	public List<ProductSaleType> getProductSaleType(HttpServletRequest request) {
		logger.info("Inside Get All Product Sale Type");
		List<ProductSaleType> productSaleType = productDAO.getAllProductSaleType();
		return productSaleType;
	}

	@RequestMapping(value = "/getPriceType", method = RequestMethod.GET, produces = "application/json")
	public List<PriceType> getPriceType(HttpServletRequest request) {
		logger.info("Inside Get All Price Type");
		List<PriceType> priceType = productDAO.getAllPriceType();
		return priceType;
	}

	@RequestMapping(value = "/getProductsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Product> getProductsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("Inside Get All Products By Page Controller");
		List<Product> product = productDAO.getAllProductsByPage(pagesize, startindex);
		return product;
	}

	@RequestMapping(value = "/searchProducts", method = RequestMethod.GET, produces = "application/json")
	public List<Product> searchProducts(String keyword, HttpServletRequest request) {
		logger.info("Inside Search Products Controller");
		List<Product> product = productDAO.searchProducts(keyword);
		return product;
	}

	@RequestMapping(value = "/getProductScopeOfSupplyByProductId", method = RequestMethod.GET, produces = "application/json")
	public List<ProductScopeOfSupply> getProductScopeOfSupplyByProductId(int productid, HttpServletRequest request) {
		logger.info("***** GET PRODUCT SCOPE OF SUPPLY BY PRODUCT ID *****");
		List<ProductScopeOfSupply> productScopeOfSupply = productDAO.getProductScopeOfSupplyByProductId(productid);
		return productScopeOfSupply;
	}

	@RequestMapping(value = "/getProductSpecificationsByProductId", method = RequestMethod.GET, produces = "application/json")
	public List<ProductSpecifications> getProductSpecificationsByProductId(int productid, HttpServletRequest request) {
		logger.info("Inside Get Product Specifications By Product Id Controller");
		List<ProductSpecifications> productspecifications = productDAO.getProductSpecificationsByProductId(productid);
		return productspecifications;
	}

	@RequestMapping(value = "/getProductSupplierByProductId", method = RequestMethod.GET, produces = "application/json")
	public List<ProductSupplier> getProductSupplierByProductId(int productid, HttpServletRequest request) {
		logger.info("Inside Get Product Supplier By Product Id Controller");
		List<ProductSupplier> productsupplier = productDAO.getProductSupplierByProductId(productid);
		return productsupplier;
	}

	@RequestMapping(value = "/getProductPriceByProductId", method = RequestMethod.GET, produces = "application/json")
	public List<ProductPrice> getProductPriceByProductId(int productid, HttpServletRequest request) {
		logger.info("Inside Get Product Price By Product Id Controller");
		List<ProductPrice> productprice = productDAO.getProductPriceByProductId(productid);
		return productprice;
	}

	@RequestMapping(value = "/getProductTaxByProductId", method = RequestMethod.GET, produces = "application/json")
	public List<ProductTax> getProductTaxByProductId(int productid, HttpServletRequest request) {
		logger.info("Inside Get Product Tax By Product Id Controller");
		List<ProductTax> producttax = productDAO.getProductTaxByProductId(productid);
		return producttax;
	}

	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(int categoryname, int subcategoryname, int brandname, int saletype, String productname,
			String partnumber, int hsn, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD PRODUCT *****");
		String result = null;
		String p = productname.replace("$", "&");
		String p1 = p.replace("~", "#");
		String p2 = p1.replace("!", "%");

		if (partnumber.equals("")) {
			partnumber = null;
		}
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		product = new Product(p2, partnumber, description, brandname, categoryname, subcategoryname, saletype,
				hsn, s, id, IpAddress);
		result = productDAO.addProduct(product);
		return result;
	}

	@RequestMapping(value = "addProductScopeOfSupply", method = RequestMethod.POST)
	public String addProductScopeOfSupply(int sequence, String particular, String value, int unitid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PRODUCT SCOPE OF SUPPLY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int productid = productDAO.getLastProductId();

		productScopeOfSupply = new ProductScopeOfSupply(productid, sequence, particular, value, unitid, id, IpAddress);
		productDAO.addProductScopeOfSupply(productScopeOfSupply);

		return "";
	}

	@RequestMapping(value = "addProductSpecification", method = RequestMethod.POST)
	public String addProductSpecification(int sequence, String specification, String specvalue, int unitid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PRODUCT SPECIFICATION *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int productid = productDAO.getLastProductId();

		productSpecifications = new ProductSpecifications(productid, sequence, specification, specvalue, unitid, id,
				IpAddress);
		productDAO.addProductSpecification(productSpecifications);

		return "";
	}

	@RequestMapping(value = "addProductSupplier", method = RequestMethod.POST)
	public String addProductSupplier(int supplierid, HttpServletRequest request, HttpSession session) {
		logger.info("Inside Add Product Supplier Controller");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int productid = productDAO.getLastProductId();

		productSupplier = new ProductSupplier(productid, supplierid, id, IpAddress);
		productDAO.addProductSupplier(productSupplier);

		return "";
	}

	@RequestMapping(value = "addProductPrice", method = RequestMethod.POST)
	public String addProductPrice(int pricetypeid, float price, int currencyid, HttpServletRequest request,
			HttpSession session) {
		logger.info("Inside Add Product Price Controller");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int productid = productDAO.getLastProductId();

		productPrice = new ProductPrice(productid, pricetypeid, price, currencyid, id, IpAddress);
		productDAO.addProductPrice(productPrice);

		return "";
	}

	@RequestMapping(value = "addProductTax", method = RequestMethod.POST)
	public String addProductTax(int taxid, float rate, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PRODUCT TAX *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		int productid = productDAO.getLastProductId();

		productTax = new ProductTax(taxid, rate, productid, id, IpAddress);
		productDAO.addProductTax(productTax);

		return "";
	}

	@RequestMapping(value = "editProduct", method = RequestMethod.POST)
	public String editProduct(int productid, int categoryname, int subcategoryname, int brandname, int saletype,
			String productname, String partnumber, int unitid, int hsn, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT PRODUCT *****");
		String result = null;
		String p = productname.replace("$", "&");
		String p1 = p.replace("~", "#");
		String p2 = p1.replace("!", "%");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		if (partnumber.equals("")) {
			partnumber = null;
		}
		product = new Product(productid, p2, partnumber, description, brandname, categoryname, subcategoryname,
				saletype, unitid, hsn, id, IpAddress);
		result = productDAO.editProduct(product);

		return result;
	}

	@RequestMapping(value = "/deleteProductScopeOfSupply", method = RequestMethod.DELETE)
	public void deleteProductScopeOfSupply(int productid) {
		logger.info("***** DELETE PRODUCT SCOPE OF SUPPLY *****");
		productDAO.deleteProductScopeOfSupply(productid);
	}

	@RequestMapping(value = "/deleteProductSpecifications", method = RequestMethod.DELETE)
	public void deleteProductSpecifications(int productid) {
		logger.info("Inside Delete Product Specifications Controller");
		productDAO.deleteProductSpecifications(productid);
	}

	@RequestMapping(value = "/deleteProductSupplier", method = RequestMethod.DELETE)
	public void deleteProductSupplier(int productid) {
		logger.info("Inside Delete Product Supplier Controller");
		productDAO.deleteProductSupplier(productid);
	}

	@RequestMapping(value = "/deleteProductPrice", method = RequestMethod.DELETE)
	public void deleteProductPrice(int productid) {
		logger.info("Inside Delete Product Price Controller");
		productDAO.deleteProductPrice(productid);
	}

	@RequestMapping(value = "/deleteProductTax", method = RequestMethod.DELETE)
	public void deleteProductTax(int productid) {
		logger.info("Inside Delete Product Tax Controller");
		productDAO.deleteProductTax(productid);
	}

	@RequestMapping(value = "editProductScopeOfSupply", method = RequestMethod.POST)
	public String editProductScopeOfSupply(int productid, int sequence, String particular, String value, int unitid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT PRODUCT SCOPE OF VALUE *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		productScopeOfSupply = new ProductScopeOfSupply(productid, sequence, particular, value, unitid, id, IpAddress);
		productDAO.addProductScopeOfSupply(productScopeOfSupply);

		return "";
	}

	@RequestMapping(value = "editProductSpecification", method = RequestMethod.POST)
	public String editProductSpecification(int productid, int sequence, String specification, String specvalue,
			int unitid, HttpServletRequest request, HttpSession session) {
		logger.info("+++++ EDIT PRODUCT SPECIFICATION +++++");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		productSpecifications = new ProductSpecifications(productid, sequence, specification, specvalue, unitid, id,
				IpAddress);
		productDAO.addProductSpecification(productSpecifications);

		return "";
	}

	@RequestMapping(value = "editProductSupplier", method = RequestMethod.POST)
	public String editProductSupplier(int productid, int supplierid, HttpServletRequest request, HttpSession session) {
		logger.info("Inside Edit Product Supplier Controller");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		productSupplier = new ProductSupplier(productid, supplierid, id, IpAddress);
		productDAO.addProductSupplier(productSupplier);

		return "";
	}

	@RequestMapping(value = "editProductPrice", method = RequestMethod.POST)
	public String editProductPrice(int productid, int pricetypeid, float price, int currencyid,
			HttpServletRequest request, HttpSession session) {
		logger.info("Inside Edit Product Price Controller");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		productPrice = new ProductPrice(productid, pricetypeid, price, currencyid, id, IpAddress);
		productDAO.addProductPrice(productPrice);

		return "";
	}

	@RequestMapping(value = "editProductTax", method = RequestMethod.POST)
	public String editProductTax(int productid, int taxid, float rate, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT PRODUCT TAX *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		productTax = new ProductTax(taxid, rate, productid, id, IpAddress);
		productDAO.addProductTax(productTax);

		return "";
	}

	@RequestMapping(value = "deleteProduct", method = RequestMethod.DELETE)
	public void delete(int productid) {
		logger.info("Inside Delete Product Controller...");

		productDAO.deleteProduct(productid);
	}

	@RequestMapping(value = "/getProductDetailWithSalePriceById", method = RequestMethod.GET, produces = "application/json")
	public Product getProductDetailWithSalePriceById(int productid, HttpServletRequest request) {
		logger.info("***** PRODUCT DETAIL WITH SALE PRICE BY ID *****");
		Product product = productDAO.getProductDetailWithSalePriceById(productid);
		return product;
	}

	@RequestMapping(value = "/getProductPriceById", method = RequestMethod.GET, produces = "application/json")
	public ProductPrice getProductPriceById(int pricetypeid, int productid, HttpServletRequest request) {
		logger.info("***** GET PRODUCT PRICE BY ID *****");
		ProductPrice productPrice = productDAO.getProductPriceById(pricetypeid, productid);
		return productPrice;
	}

}