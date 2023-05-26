package net.etfbl.ip.beans;

import java.io.Serializable;
import java.util.List;

import net.etfbl.ip.dao.UserDAO;
import net.etfbl.ip.dto.User;

public class UsersBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9064514970253004486L;
	
	private static final String DEFAULT_PASSWORD = "password";
	private static final String DEFAULT_PIN = "1234";

	public UsersBean() {
		// TODO Auto-generated constructor stub
	}
	
	static public List<User> getUsers() {
		return UserDAO.selectAllUsers();
	}
	
	public User getById(Integer id) {
		return UserDAO.selectUserById(id);
	}
	
	public boolean isUsernameAllowed(String username) {
		return !UserDAO.selectUserByUsernameExists(username);
	}
	
	public boolean addUser(User user) {
		return UserDAO.insertUser(user, DEFAULT_PASSWORD, DEFAULT_PIN);
	}
	
	public boolean updateUser(User user) {
		return UserDAO.updateUser(user);
	}
	
	public boolean deleteUser(Integer id) {
		return UserDAO.deleteUser(id);
	}

}
