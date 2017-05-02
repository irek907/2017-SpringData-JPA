package com.i5018.common.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import com.i5018.common.dao.impl.GenericRepositoryImpl;

/**
 * @author lmk
 *
 */
@SuppressWarnings("all")
public class DefaultRepositoryFactory extends JpaRepositoryFactory {

	private final EntityManager entityManager;
    
	public DefaultRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		Assert.notNull(entityManager);
		this.entityManager = entityManager;
		
	}
	
	protected <T, ID extends Serializable> JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {

		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
		return new GenericRepositoryImpl(entityInformation, entityManager); // custom implementation
	}
  
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
 
    	return GenericRepositoryImpl.class;
    }
}