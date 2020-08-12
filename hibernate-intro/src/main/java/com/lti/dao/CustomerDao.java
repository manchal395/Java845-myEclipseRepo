package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Customer;


public class CustomerDao {
	
	public void add(Customer customer) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro"); //<persistent-unit name="...">

		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(customer); 
		
		tx.commit();
		
		//should be in finally block
		em.close();
		emf.close();
	}
	
	public Customer fetch(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();

		Customer cust = em.find(Customer.class, id); 
		
		em.close();
		emf.close();
		
		return cust;
	}
	
	public List<Customer> fetchAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT c FROM Customer c");
		List<Customer> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}
	
	public List<String> fetchNames() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("SELECT c.name FROM Customer c");
		List<String> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}

	public List<String[]> fetchNamesEmail() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("SELECT c.name, c.email FROM Customer c");
		List<String[]> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}

	public List<Customer> fetchOnEmail(String domain) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("SELECT c FROM Customer c WHERE c.email LIKE :dm");
		q.setParameter("dm", "%"+domain+"%");
		List<Customer> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}
	
	public void update(Customer customer) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(customer);
		
		tx.commit();
		
		em.close();
		emf.close();
	}
}
