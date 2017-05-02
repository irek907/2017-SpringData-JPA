package com.i5018.hotel.test.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i5018.common.service.impl.GenericServiceImpl;
import com.i5018.hotel.test.bean.Employee;
import com.i5018.hotel.test.dao.JpaTestDao;

@Service
public class JpaTestService extends GenericServiceImpl<Employee>{

	@Autowired
	private JpaTestDao jpaTestDao;

	@Transactional(readOnly = true)
	public Page<Employee> getPage(int pageNo, int pageSize) {

		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return jpaTestDao.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Employee getByLastName(String lastName) {

		return jpaTestDao.getByLastName(lastName);
	}

	@Transactional
	public void save(Employee e) {
		if (e.getId() == null) {
			e.setCreateTime(new Date());
		}
		jpaTestDao.saveAndFlush(e);
	}

	@Transactional(readOnly = true)
	public Employee get(Integer id) {
		return jpaTestDao.findOne(id);
	}
	
	@Transactional
	public void delete(Integer id){
		jpaTestDao.delete(id);
	}
	@Transactional
	public long getRowCount(){
		long x = jpaTestDao.getTotalCount();
		return x;
	}
}
