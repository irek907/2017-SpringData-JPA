package com.i5018.hotel.test.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.i5018.common.dao.GenericRepository;
import com.i5018.hotel.test.bean.Department;

public interface DepartmentDao extends GenericRepository<Department, Integer> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	@Query("FROM Department d")
	List<Department> getAll();

}
