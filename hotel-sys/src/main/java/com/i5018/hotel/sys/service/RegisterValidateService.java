package com.i5018.hotel.sys.service;

import java.text.ParseException;
import com.i5018.hotel.sys.exception.ServiceException;

public interface RegisterValidateService {
	
	public void processregister(String email);

	/**
	 * 处理激活
	 * 
	 * @throws ParseException
	 */
	public void processActivate(String email, String validateCode)
			throws ServiceException, ParseException;

}
