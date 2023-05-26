package net.etfbl.ip.beans;

import java.io.Serializable;

import net.etfbl.ip.dao.AdminDAO;
import net.etfbl.ip.dto.Admin;

public class AdminBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4416882921120321330L;
	private Admin admin = new Admin();
	
	private boolean isLoggedIn = false;

	public AdminBean() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean logIn(String username, String password) {
		
		if((admin = AdminDAO.checkAccount(username, password)) != null) {
			isLoggedIn = true;
		}
		
		return isLoggedIn;
		
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public Admin getAdmin() {
		return admin;
	}

}
