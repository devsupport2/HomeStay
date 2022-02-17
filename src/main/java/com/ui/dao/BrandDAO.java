package com.ui.dao;

import java.util.List;

import com.ui.model.Brand;

public interface BrandDAO 
{
	public List<Brand> getAllBrands();
	public void addBrand(Brand b);
	public void editBrand(Brand b);
	public void deleteBrand(int brandid);
	public List<Brand> getAllBrandsByPage(int pagesize, int startindex);
	public List<Brand> searchBrands(String keyword);
}
