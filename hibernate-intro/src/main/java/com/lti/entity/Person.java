package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_person")
public class Person {

	@Id
	@SequenceGenerator(sequenceName = "personseq", allocationSize = 1, name = "seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private int id;
	
	private String name;
	private LocalDate dateOfBirth;
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.MERGE) 
	//owner --which end is owning the relationship; owner is Passport
	//mappedBy value = object name of Person class in Passport class
	private Passport passport;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Passport getPassport() {
		return passport;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	
}
