package net.etfbl.ip.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6996617316192292021L;

	private Integer id;
	private Integer id_parent;
	private String name;
	private boolean isDeleted;
	private List<Attribute> attributes = new ArrayList<>();
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(Integer id, Integer id_parent, String name, boolean isDeleted) {
		super();
		this.id = id;
		this.id_parent = id_parent;
		this.name = name;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_parent() {
		return id_parent;
	}

	public void setId_parent(Integer id_parent) {
		this.id_parent = id_parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, id_parent, isDeleted, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(id_parent, other.id_parent)
				&& isDeleted == other.isDeleted && Objects.equals(name, other.name);
	}

}
