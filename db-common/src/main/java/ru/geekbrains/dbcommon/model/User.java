package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "users")
public class User
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @ManyToMany(fetch = LAZY)
  @JoinTable(name = "users_roles",
			 joinColumns = @JoinColumn(name = "user_id"),
			 inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;


  public User()
  {
  }


  public Long getId()
  {
	return id;
  }


  public void setUserName(String name)
  {
	userName = name;
  }


  public String getUserName()
  {
	return userName;
  }


  public void setPassword(String pswd)
  {
	password = pswd;
  }


  public String getPassword()
  {
	return password;
  }


  public void setFirstName(String name)
  {
	firstName = name;
  }


  public String getFirstName()
  {
	return firstName;
  }


  public void setLastName(String name)
  {
	lastName = name;
  }


  public String getLastName()
  {
	return lastName;
  }


  public void setEmail(String email)
  {
	this.email = email;
  }


  public String getEmail()
  {
	return email;
  }


  public List<Role> getRoles()
  {
	return roles;
  }


  public void setRoles(List<Role> roles)
  {
	this.roles = roles;
  }

}