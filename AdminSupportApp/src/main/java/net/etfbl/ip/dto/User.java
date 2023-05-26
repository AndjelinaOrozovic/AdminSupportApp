package net.etfbl.ip.dto;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6573322195180433292L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String city;
	private String mail;
	private boolean isActivated;
	private boolean isDeleted;
	private String avatar;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String firstName, String lastName, String username, String city, String mail,
			boolean isActivated, boolean isDeleted, String avatar) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.city = city;
		this.mail = mail;
		this.isActivated = isActivated;
		this.isDeleted = isDeleted;
		this.avatar = avatar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avatar, city, firstName, id, isActivated, isDeleted, lastName, mail, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(avatar, other.avatar) && Objects.equals(city, other.city)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& isActivated == other.isActivated && isDeleted == other.isDeleted
				&& Objects.equals(lastName, other.lastName) && Objects.equals(mail, other.mail)
				&& Objects.equals(username, other.username);
	}

}
