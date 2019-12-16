package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "customers")
public class Customer
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "firstname", nullable = false)
  private String firstname;

  @Column(name = "lastname", nullable = false)
  private String lastname;

  @Column(name = "middlename")
  private String middlename;

  @Column(name = "phone_number", unique = true, nullable = false)
  private String phoneNumber;


  public Customer()
  {
  }


  public Long getId()
  {
	return id;
  }


  public String getFirstname()
  {
	return firstname;
  }


  public String getLastname()
  {
	return lastname;
  }


  public String getMiddlename()
  {
	return middlename;
  }


  public String getPhoneNumber()
  {
	return phoneNumber;
  }


  public void setFirstname(String firstname)
  {
	this.firstname = firstname;
  }


  public void setLastname(String lastname)
  {
	this.lastname = lastname;
  }


  public void setMiddlename(String middlename)
  {
	this.middlename = middlename;
  }


  public void setPhoneNumber(String phoneNumber)
  {
	this.phoneNumber = phoneNumber;
  }

}