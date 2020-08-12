package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Address;
import com.lti.entity.Employee;

public class EmployeeDao {

	public void add(Employee e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(e); 
		
		tx.commit();

		em.close();
		emf.close();
	}
	
	public void add(Address a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(a); 
		
		tx.commit();

		em.close();
		emf.close();
	}
	
	public Employee fetchByPsno(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		
		Employee e = em.find(Employee.class, id);
		
		em.close();
		emf.close();
		
		return e;
	}
	
	public List<Employee> fetchByMonth(int mon) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		//one way: MONTH(column) = month_number
		Query q = em.createQuery("SELECT e FROM Employee e WHERE MONTH(e.dateOfJoining) = :m");
		q.setParameter("m", mon);
		List<Employee> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}
	
	public List<Employee> fetchBySalary(double sal) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT e FROM Employee e WHERE e.salary = :s");
		q.setParameter("s", sal);
		List<Employee> list = q.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}
	
	//fetch data using join
	public List<Employee> fetchByCity(String city) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "SELECT emp FROM Employee emp INNER JOIN emp.address addr WHERE addr.city = :ct";
		Query q = em.createQuery(jpql);
		q.setParameter("ct", city);
		List<Employee> list = q.getResultList();
 		
		em.close();
		emf.close();
		return list;
	}
	
	public void update(Employee e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(e);
		
		tx.commit();
		
		em.close();
		emf.close();
	}
	
}
