package net.etfbl.ip.dto;

import java.io.Serializable;
import java.util.Objects;

public class Log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2214206120275418043L;
	
	private Integer id;
	private String content;
	private String dateAndTime;

	public Log() {
		// TODO Auto-generated constructor stub
	}

	public Log(Integer id, String content, String dateAndTime) {
		super();
		this.id = id;
		this.content = content;
		this.dateAndTime = dateAndTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, dateAndTime, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		return Objects.equals(content, other.content) && Objects.equals(dateAndTime, other.dateAndTime)
				&& Objects.equals(id, other.id);
	}

}
