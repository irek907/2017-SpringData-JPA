package com.i5018.hotel.test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.i5018.hotel.test.bean.Department;
import com.i5018.hotel.test.dao.DepartmentDao;

public class DatabaseTest {

	private ApplicationContext atx = null;
	private DepartmentDao departmentDao;
	
	
	{
		atx = new ClassPathXmlApplicationContext(
				"config/applicationContext.xml");
		departmentDao = atx.getBean(DepartmentDao.class);
	}

	@SuppressWarnings("unused")
	@Test
	public void testCache(){
		List<Department> list = departmentDao.getAll();
		list = departmentDao.getAll();
	}
	
	
	@Test
	public void test() throws SQLException {
		DataSource d = atx.getBean(DataSource.class);
		System.out.println(d.getConnection());
	}

}
