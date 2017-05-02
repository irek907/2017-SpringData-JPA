package com.i5018.hotel.sys.dao;

import java.text.ParseException;

import com.i5018.hotel.sys.bean.UserModel;

public interface UserDao {

	public void save(UserModel user);

	/**
	 * @更新 user
	 */
	public void update(UserModel user);

	/**
	 * @throws ParseException
	 * @查找信息
	 */
	public UserModel find(String email) throws ParseException;

}
