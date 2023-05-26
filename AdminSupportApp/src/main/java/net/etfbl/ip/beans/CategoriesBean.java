package net.etfbl.ip.beans;

import java.io.Serializable;
import java.util.List;

import net.etfbl.ip.dao.CategoryDAO;
import net.etfbl.ip.dto.Category;

public class CategoriesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6248596045402811233L;

	public CategoriesBean() {
		// TODO Auto-generated constructor stub
	}
	
	static public List<Category> getAllCategories() {
		return CategoryDAO.selectAllCategories();
	}
	
	public Category getCategoryById(Integer id) {
		return CategoryDAO.selectCategoryById(id);
	}
	
	public boolean isCategoryNameAllowed(String name) {
		return !CategoryDAO.selectCategoryByNameExists(name);
	}
	
	public boolean addCategory(Category category) {
		return CategoryDAO.insertCategory(category);
	}
	
	public boolean updateCategory(Category category) {
		return CategoryDAO.updateCategoryName(category);
	}
	
	public boolean deleteCategory(Integer id) {
		return CategoryDAO.deleteCategory(id);
	}
	
	public String getCategoryFullName(Category category) {
		
		if(category == null) {
			return "";
		}
		
		if(category.getId_parent() != 0 && category != null) {
			return getCategoryFullName(getCategoryById(category.getId_parent())) + " -> " + category.getName();
		} else {
			return category.getName();
		}
	}

}
