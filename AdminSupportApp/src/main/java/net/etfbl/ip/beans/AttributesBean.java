package net.etfbl.ip.beans;

import java.io.Serializable;

import net.etfbl.ip.dao.AttributeDAO;
import net.etfbl.ip.dto.Attribute;

public class AttributesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7657485521752962094L;
	
	public AttributesBean() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isAttributeNameAllowed(Integer idCategory, String name) {
		return AttributeDAO.selectAttributeByIdCategoryAndName(idCategory, name) == null;
	}
	
	public boolean addAttributeForCategory(Attribute attribute) {
		return AttributeDAO.insertAttribute(attribute);
	}
	
	public boolean deleteAttribute(Integer id) {
		return AttributeDAO.deleteAttributeById(id);
	}

}
