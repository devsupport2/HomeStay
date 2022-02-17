package com.ui.model;

public class AllCount {
	public AllCount(int countryCount, int currencyCount, int financialYearCount, int measuremntUnitCount,
			int stateCount, int taxCount, int userCount, int brandCount, int categoryCount, int subcategoryCount,
			int productCount, int enquiryCount, int quotationCount) {
		super();
		this.countryCount = countryCount;
		this.currencyCount = currencyCount;
		this.financialYearCount = financialYearCount;
		this.measuremntUnitCount = measuremntUnitCount;
		this.stateCount = stateCount;
		this.taxCount = taxCount;
		this.userCount = userCount;
		this.brandCount = brandCount;
		this.categoryCount = categoryCount;
		this.subcategoryCount = subcategoryCount;
		this.productCount = productCount;
		this.enquiryCount = enquiryCount;
		this.quotationCount = quotationCount;
	}

	int countryCount;
	int currencyCount;
	int financialYearCount;
	int measuremntUnitCount;
	int stateCount;
	int taxCount;
	int userCount;
	int brandCount;
	int categoryCount;
	int subcategoryCount;
	int productCount;
	int enquiryCount;
	int quotationCount;

	public int getCountryCount() {
		return countryCount;
	}

	public int getCurrencyCount() {
		return currencyCount;
	}

	public int getFinancialYearCount() {
		return financialYearCount;
	}

	public int getMeasuremntUnitCount() {
		return measuremntUnitCount;
	}

	public int getStateCount() {
		return stateCount;
	}

	public int getTaxCount() {
		return taxCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public int getBrandCount() {
		return brandCount;
	}

	public int getCategoryCount() {
		return categoryCount;
	}

	public int getSubcategoryCount() {
		return subcategoryCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public int getEnquiryCount() {
		return enquiryCount;
	}

	public int getQuotationCount() {
		return quotationCount;
	}
}
