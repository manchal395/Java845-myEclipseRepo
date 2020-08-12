package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CUST")

public class Customer {

	//for composite pk we will use @EmbeddedId
	//any column constraints are added using annotations here
	@Id //primary key
	@SequenceGenerator(sequenceName = "myseq", allocationSize = 1, name = "mysq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mysq") //auto generated pk
	@Column(name = "ID")
	private int id;
	
	//cmd+click on Column annotation to see all methods
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", unique = true) //unique constraint used here
	private String email;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
