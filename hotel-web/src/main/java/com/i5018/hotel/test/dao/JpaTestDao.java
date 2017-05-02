package com.i5018.hotel.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i5018.common.dao.GenericRepository;
import com.i5018.hotel.test.bean.Employee;

public interface JpaTestDao extends GenericRepository<Employee, Integer> {

	Employee getByLastName(String lastName);

	@Query(value="SELECT count(id) FROM sssp_employee",nativeQuery=true)  
	public long getTotalCount(); 
/*

	//可以通过自定义的JPQL 完成update和delete操作，注意：JPQL不支持Insert操作  
	//在@Query注解中编写JPQL语句，但必须使用@Modify进行修饰，以通知SpringData，这是一个Update或者Delete  
	//Update或者delete操作，需要使用事务，此时需要定义Service层，在service层的方法上添加事务操作  
	//默认情况下，SpringData的每个方法上有事务，但都是一个只读事务，他们不能完成修改操作  
	@Modifying  
	@Query("update Person p set p.email=:email where id=:id")  
	void updatePersonEmail(@Param("id")Integer id,@Param("email")String email);  


	//查询id值最大的那个person  
	//使用@Query注解可以自定义JPQL语句，语句可以实现更灵活的查询  
	@Query("SELECT p FROM Person p WHERE p.id=(SELECT max(p2.id) FROM Person p2)")  
	Object getMaxIdPerson();  

	//为@Query注解传递参数的方式1：使用占位符  
	@Query("SELECT P FROM Person P where P.lastName=?1 AND P.email=?2")  
	List<Object> testQueryAnnotationParams1(String lastName,String email);  



	//为@Query注解传递参数的方式2:使用命名参数方式  
	@Query("SELECT P FROM Person P where P.lastName=:lastName AND P.email=:email")  
	List<Object> testQueryAnnotationParams2(@Param("email")String email,@Param("lastName")String lastName);  


	//Spring Data 运行在占位符上添加%%  
	@Query("select p from Person p where p.lastName like %?1% or p.email like %?2%")  
	List<Object> testQueryAnnotationLikeParam(String lastName,String email);  */

}
