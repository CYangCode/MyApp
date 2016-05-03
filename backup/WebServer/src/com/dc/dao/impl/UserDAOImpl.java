package com.dc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dc.dao.UserDAO;
import com.tc.bean.User;
import com.tc.util.DButil;

public class UserDAOImpl implements UserDAO {

	@Override
	public User findUserById(int id) {
		User user = null;
		Connection conn = DButil.getConnection();
		String sql = "select * from users where id=?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				String uAccount = rs.getString("u_account");
				String uPassword = rs.getString("u_password");
				String uName = rs.getString("u_name");
				String uSex = rs.getString("u_sex");
				String uContent = rs.getString("u_content");
				user = new User(id, uAccount, uPassword, uName, uSex, uContent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(ps, conn);
		}
		return user;
	}

	@Override
	public User findUserByAccount(String uAccount) {
		// TODO Auto-generated method stub
		User user = null;
		Connection conn = DButil.getConnection();
		String sql = "select * from users where u_account=?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uAccount);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String uPassword = rs.getString("u_password");
				String uName = rs.getString("u_name");
				String uSex = rs.getString("u_position");
				String uContent = rs.getString("u_content");
				user = new User(id, uAccount, uPassword, uName, uSex, uContent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(ps, conn);
		}
		return user;
	}
	
	@Override
	public int addUser(User user) {
		Connection conn = DButil.getConnection();
		String sql = "insert  into users(u_account,u_password,u_name,u_position,u_content) values(?,?,?,?,?)";
		PreparedStatement ps= null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getuAccount());
			ps.setString(2, user.getuPassword());
			ps.setString(3, user.getuName());
			ps.setString(4, user.getuPosition());
			ps.setString(5, user.getuContent());
			int rs  = ps.executeUpdate();
			if(rs > 0){
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.close(ps, conn);
		}
		return 0;
	}

	@Override
	public User login(User user) {
		Connection conn = DButil.getConnection();
		String sql = "select id,u_account,u_password,u_name,u_sex,u_content "
				+ " from users where  u_account=? and u_password=?";
		PreparedStatement ps= null;
		ResultSet rs = null;
		User rsUser = new User(0);
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getuAccount());
			ps.setString(2, user.getuPassword());
			rs = ps.executeQuery();
			if(rs.next()){
				rsUser.setId(rs.getInt("id"));
				rsUser.setuAccount(rs.getString("u_account"));
				rsUser.setuPassword(rs.getString("u_password"));
				rsUser.setuName(rs.getString("u_name"));
				rsUser.setuPosition(rs.getString("u_sex"));
				rsUser.setuContent(rs.getString("u_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(rs, ps, conn);
		}
		return rsUser;
	}
}
