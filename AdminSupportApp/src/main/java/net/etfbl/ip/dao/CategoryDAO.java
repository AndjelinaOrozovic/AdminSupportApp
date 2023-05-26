package net.etfbl.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.ip.dto.Category;

public class CategoryDAO {

	private static final String SQL_SELECT_ALL_CATEGORIES = "SELECT * FROM category";
	private static final String SQL_SELECT_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name=?";
	private static final String SQL_SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";
	private static final String SQL_INSERT_CATEGORY = "INSERT INTO category VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE_CATEGORY_NAME = "UPDATE category SET name=? WHERE id=?";
	private static final String SQL_DELETE_CATEGORY_BY_ID = "UPDATE category SET is_deleted=true WHERE id=?";
	
	public static List<Category> selectAllCategories() {
		
		List<Category> categories = new ArrayList<>();
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL_CATEGORIES, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4));
				category.setAttributes(AttributeDAO.selectAttributeByCategory(category.getId()));
				categories.add(category);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return categories;
		
	}
	
	public static boolean selectCategoryByNameExists(String name) {
		boolean exists = false;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { name };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_CATEGORY_BY_NAME, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				exists = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return exists;
	}
	
	public static Category selectCategoryById(Integer id) {
		Category category = null;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_CATEGORY_BY_ID, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				category = new Category(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4));
				category.setAttributes(AttributeDAO.selectAttributeByCategory(category.getId()));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return category;
	}
	
	public static boolean insertCategory(Category category) {
		boolean isInserted = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { null, category.getId_parent(), category.getName(), category.isDeleted() };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_INSERT_CATEGORY, false, values);
			ps.executeUpdate();
			isInserted = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return isInserted;
	}
	
	public static boolean updateCategoryName(Category category) {
		if(selectCategoryById(category.getId()) == null) {
			return false;
		}
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { category.getName(), category.getId() };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_UPDATE_CATEGORY_NAME, false, values);
			ps.executeUpdate();
			result = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return result;
	}
	
	public static boolean deleteCategory(Integer id) {
		if(selectCategoryById(id) == null) {
			return false;
		}
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_DELETE_CATEGORY_BY_ID, false, values);
			ps.executeUpdate();
			result = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return result;
	}
}
