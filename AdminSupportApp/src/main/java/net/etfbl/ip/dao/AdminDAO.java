package net.etfbl.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.etfbl.ip.dto.Admin;

public class AdminDAO {

	private static final String SQL_SELECT_ADMIN_ACCOUNT = "SELECT * FROM account WHERE username=? AND password=? AND id_account_type=2";
	
	public static Admin checkAccount(String username, String password) {
		Admin user = null;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = {username, password};
		
		try {
			c = DBUtil.getConnection();
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ADMIN_ACCOUNT, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new Admin(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return user;
	}
	
}
