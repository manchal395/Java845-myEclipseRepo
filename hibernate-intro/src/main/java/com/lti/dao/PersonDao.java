package com.lti.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.lti.entity.Passport;
import com.lti.entity.Person;

public class PersonDao {

	public List<Person> fetchByExpiredPassport(LocalDate date) {
	//public List<Person> fetchByExpiredPassport() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		
		//sysdate, current_date works here
		String jpql = "SELECT p FROM Person p INNER JOIN p.passport pass WHERE pass.expiryDate <= :d";
		Query q = em.createQuery(jpql);
		q.setParameter("d",	date);
		
		List<Person> persons = q.getResultList();
		em.close();
		emf.close();
		return persons;
	}
	
	public Passport fetchPassportOfPerson(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "SELECT pass FROM Passport pass INNER JOIN pass.person p WHERE p.name = :n";
		Query q = em.createQuery(jpql);
		q.setParameter("n", name);
		Passport pt = (Passport) q.getSingleResult();
		
		em.close();
		emf.close();
		return pt;
	}
	
	public List<Object[]> fetchNamePassportNo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-intro");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "SELECT p.name, pass.passportNo FROM Person p INNER JOIN p.passport pass";
		Query q = em.createQuery(jpql);
		List<Object[]> res = q.getResultList();
		
		em.close();
		emf.close();
		return res;
	}
	
}
