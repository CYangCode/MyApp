package com.dc.dao;

import com.tc.bean.User;

public interface UserDAO {
	/**
	 * ����user
	 * @param id
	 * @return
	 */
	public  User findUserById(int id);
	public User findUserByAccount(String uAccount);
	public  int addUser(User user);
	public User login(User user);
}
