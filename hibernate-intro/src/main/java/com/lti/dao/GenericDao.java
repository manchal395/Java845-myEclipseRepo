package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//itna sahi code hai na ye!

//Person-Package
public class GenericDao {

	public void add(Object obj) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//em.persist(obj); 
		em.merge(obj); //merge can be used for insert as well as update both
		tx.commit();
		em.close();
		emf.close();
	}
	
	public Object fetchByPK(Class clazz, Object pk) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		Object obj = em.find(clazz, pk);
		em.close();
		emf.close();
		return obj;
	}
	
}
