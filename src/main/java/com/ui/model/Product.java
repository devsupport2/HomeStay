package com.ui.model;

public class Product {
	public Product(int productId, String productName, String partNumber) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
	}

	public Product(int productId, String productName, String partNumber, String description, int brandId,
			int categoryId, int subcategoryId, int productSaleTypeId, int unitId, int hsn, float price,
			String unitName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.description = description;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.productSaleTypeId = productSaleTypeId;
		this.unitId = unitId;
		this.hsn = hsn;
		this.price = price;
		this.unitName = unitName;
	}

	public Product(int productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}

	public Product(String productName, String partNumber, String status, int createdBy, String ipAddress) {
		super();
		this.productName = productName;
		this.partNumber = partNumber;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Product() {
		super();
	}

	public Product(int productId, String productName, String partNumber, String description, int brandId,
			int categoryId, int subcategoryId, int productSaleTypeId, int unitId, int hsn) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.description = description;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.productSaleTypeId = productSaleTypeId;
		this.unitId = unitId;
		this.hsn = hsn;
	}

	public Product(int productId, String productName, String partNumber, String description, int brandId,
			int categoryId, int subcategoryId, int productSaleTypeId, int unitId, int hsn, String categoryName,
			String unitName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.description = description;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.productSaleTypeId = productSaleTypeId;
		this.unitId = unitId;
		this.hsn = hsn;
		this.categoryName = categoryName;
		this.unitName = unitName;
	}

	public Product(int productId, String productName, String partNumber, String description, int brandId,
			int categoryId, int subcategoryId, int productSaleTypeId, int unitId, int hsn, String brandName,
			String categoryName, String subcategoryName, String unitName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.description = description;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.productSaleTypeId = productSaleTypeId;
		this.unitId = unitId;
		this.hsn = hsn;
		this.brandName = brandName;
		this.categoryName = categoryName;
		this.subcategoryName = subcategoryName;
		this.unitName = unitName;
	}

	public Product(int productId, int createdBy, String ipAddress) {
		super();
		this.productId = productId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Product(int productId, String productName, String partNumber, String description, int brandId,
			int categoryId, int subcategoryId, int productSaleTypeId, int unitId, int hsn, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.description = description;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.productSaleTypeId = productSaleTypeId;
		this.unitId = unitId;
		this.hsn = hsn;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}

	public Product(int productId, String productName, String partNumber, String description, int brandId,
			int categoryId, int subcategoryId, int productSaleTypeId, int unitId, int hsn, int createdBy,
			String ipAddress) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.partNumber = partNumber;
		this.description = description;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.productSaleTypeId = productSaleTypeId;
		this.unitId = unitId;
		this.hsn = hsn;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Product(String productName, String partNumber, String description, int brandId, int categoryId,
			int subcategoryId, int productSaleTypeId,  int hsn, String status, int createdBy,
			String ipAddress) {
		super();
		this.productName = productName;
		this.partNumber = partNumber;
		this.description = description;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.productSaleTypeId = productSaleTypeId;
		//this.unitId = unitId;
		this.hsn = hsn;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}


	private int productId;
	private String productName;
	private String partNumber;
	private String description;
	private int brandId;
	private int categoryId;
	private int subcategoryId;
	private int productSaleTypeId;
	private int hsn;
	private int unitId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String brandName;
	private String categoryName;
	private String subcategoryName;
	private String unitName;
	private float price;

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getDescription() {
		return description;
	}

	public int getBrandId() {
		return brandId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public String getStatus() {
		return status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public int getProductSaleTypeId() {
		return productSaleTypeId;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public int getHsn() {
		return hsn;
	}

	public int getUnitId() {
		return unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public float getPrice() {
		return price;
	}
}
