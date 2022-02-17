package com.ui.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ui.model.PriceType;
import com.ui.model.Product;
import com.ui.model.ProductPrice;
import com.ui.model.ProductSaleType;
import com.ui.model.ProductScopeOfSupply;
import com.ui.model.ProductSpecifications;
import com.ui.model.ProductSupplier;
import com.ui.model.ProductTax;

public interface ProductDAO {
	public Map<String, ArrayList<Product>> getAllProductsForTally();

	public List<Product> getAllProducts();

	public List<Product> getProductName();

	public List<ProductSaleType> getAllProductSaleType();

	public List<PriceType> getAllPriceType();

	public String addProduct(Product p);

	public String editProduct(Product p);

	public void deleteProduct(int productid);

	public List<Product> getAllProductsByPage(int pagesize, int startindex);

	public List<Product> searchProducts(String keyword);
	
	public List<ProductScopeOfSupply> getProductScopeOfSupplyByProductId(int productid);

	public List<ProductSpecifications> getProductSpecificationsByProductId(int productid);

	public List<ProductSupplier> getProductSupplierByProductId(int productid);

	public List<ProductPrice> getProductPriceByProductId(int productid);

	public List<ProductTax> getProductTaxByProductId(int productid);

	public int getLastProductId();

	public void addProductTax(ProductTax productTax);
	
	public void deleteProductScopeOfSupply(int productid);

	public void deleteProductSpecifications(int productid);

	public void deleteProductSupplier(int productid);

	public void deleteProductPrice(int productid);

	public void deleteProductTax(int productid);
	
	public void addProductScopeOfSupply(ProductScopeOfSupply ps);

	public void addProductSpecification(ProductSpecifications ps);

	public void addProductSupplier(ProductSupplier ps);

	public void addProductPrice(ProductPrice pp);

	public Product getProductDetailById(int productid);

	public void addXmlProduct(Product p);

	public Product getProductDetailWithSalePriceById(int productid);

	public ProductPrice getProductPriceById(int pricetypeid, int productid);
	
	public List<Product> getDealerProductByDealerId(int userid);

}
