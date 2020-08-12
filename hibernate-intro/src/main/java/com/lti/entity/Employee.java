package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_employee")
public class Employee {

	@Id
	//@GeneratedValue
	private int psno;
	
	private String name;
	
	private LocalDate dateOfJoining;
	
	@Column(name = "total_salary")
	private double salary;
	
//	never keep the foreign key column to be be used
//	keep the reference table/class
//	has-a one-to-one in this case
//	paradigm-mismatch : search and read
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "addr_id")	//specify name of foreign key column -> primary key of reference table
	//@JoinColumn(name = "pin_code", referencedColumnName = "picode")	//if want to refer to some column other than pk column of reference table
	private Address address;

	public int getPsno() {
		return psno;
	}
	public void setPsno(int psno) {
		this.psno = psno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}
