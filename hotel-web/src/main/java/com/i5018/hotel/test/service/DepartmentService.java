package com.i5018.hotel.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i5018.common.service.impl.GenericServiceImpl;
import com.i5018.hotel.test.bean.Department;
import com.i5018.hotel.test.dao.DepartmentDao;

@Transactional
@Service
public class DepartmentService extends GenericServiceImpl<Department>{
	
	@Autowired
	private DepartmentDao departmentDao;
	
//	@Transactional(readOnly=true)
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
	
}
