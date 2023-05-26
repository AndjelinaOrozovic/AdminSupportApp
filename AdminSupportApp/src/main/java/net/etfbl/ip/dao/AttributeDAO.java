package net.etfbl.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.ip.dto.Attribute;

public class AttributeDAO {

	private static final String SQL_SELECT_ATTRIBUTE_BY_ID_CATEGORY = "SELECT * FROM attribute WHERE id_category=?";
	private static final String SQL_SELECT_ATTRIBUTE_BY_ID_CATEGORY_AND_NAME = "SELECT * FROM attribute WHERE id_category=? AND name=?";
	private static final String SQL_INSERT_ATTRIBUTE = "INSERT INTO attribute VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_ATTRIBUTE = "UPDATE attribute SET is_deleted=true WHERE id=?";
	
	public static List<Attribute> selectAttributeByCategory(Integer idCategory) {
		
		List<Attribute> attributes = new ArrayList<>();
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { idCategory };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ATTRIBUTE_BY_ID_CATEGORY, false, values);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				attributes.add(new Attribute(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return attributes;
		
	}
	
	public static Attribute selectAttributeByIdCategoryAndName(Integer idCategory, String name) {
		
		Attribute attribute = null;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { idCategory, name };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ATTRIBUTE_BY_ID_CATEGORY_AND_NAME, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				attribute = new Attribute(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return attribute;
		
	}
	
	public static boolean insertAttribute(Attribute attribute) {
		boolean isInserted = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { null, attribute.getIdCategory(), attribute.getName(), attribute.isDeleted() };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_INSERT_ATTRIBUTE, false, values);
			ps.executeUpdate();
			isInserted = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return isInserted;
	}
	
public static boolean deleteAttributeById(Integer id) {
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_DELETE_ATTRIBUTE, false, values);
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
