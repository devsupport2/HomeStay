package com.ui.dao;

import java.util.List;

import com.ui.model.Category;

public interface CategoryDAO {
	public List<Category> getAllCategories();

	public String addCategory(Category c);

	public String editCategory(Category c);

	public void deleteCategory(int categoryid);

	public List<Category> getAllCategoriesByPage(int pagesize, int startindex);

	public List<Category> searchCategories(String keyword);

}
