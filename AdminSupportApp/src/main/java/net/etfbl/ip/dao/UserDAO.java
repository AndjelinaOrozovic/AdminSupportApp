package net.etfbl.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.etfbl.ip.dto.User;

public class UserDAO {

	private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user_account";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user_account WHERE id=?";
	private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM user_account WHERE username=?";
	private static final String SQL_INSERT_USER = "INSERT INTO user_account VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_UESR ="UPDATE user_account SET first_name=?, last_name=?, username=?, city=?, mail=?, is_activated=?, avatar=? WHERE id=?";
	private static final String SQL_DELETE_USER = "UPDATE user_account SET is_deleted=true WHERE id=?";

	public static List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL_USERS, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10) , rs.getString(11)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return users;
	}
	
	public static boolean selectUserByIdExists(Integer id) {
		boolean exists = false;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_USER_BY_ID, false, values);
			rs = ps.executeQuery();
			if(rs.next())
				exists = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return exists;
	}
	
	public static User selectUserById(Integer id) {
		User user = null;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_USER_BY_ID, false, values);
			rs = ps.executeQuery();
			if(rs.next())
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7), rs.getBoolean(9), rs.getBoolean(10) , rs.getString(11));
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return user;
	}
	
	
	public static boolean selectUserByUsernameExists(String username) {
		boolean exists = false;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { username };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_USER_BY_USERNAME, false, values);
			rs = ps.executeQuery();
			if(rs.next())
				exists = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return exists;
	}
	
	public static boolean insertUser(User user, String password, String pin) {
		boolean isInserted = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = {null, user.getFirstName(), user.getLastName(), user.getUsername(), password, user.getCity(), user.getMail(), pin, user.isActivated(), false, user.getAvatar() };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_INSERT_USER, false, values);
			ps.executeUpdate();
			isInserted = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return isInserted;
	}
	
	public static boolean deleteUser(Integer id) {
		if(!selectUserByIdExists(id))
			return false;
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_DELETE_USER, false, values);
			ps.executeUpdate();
			result = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		
		return result;
	}
	
	public static boolean updateUser(User user) {
		if(!selectUserByIdExists(user.getId())) {
			return false;
		}
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { user.getFirstName(), user.getLastName(), user.getUsername(), user.getCity(), user.getMail(), user.isActivated(), user.getAvatar(), user.getId()};
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_UPDATE_UESR, false, values);
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
