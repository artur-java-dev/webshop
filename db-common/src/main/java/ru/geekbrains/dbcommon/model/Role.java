package ru.geekbrains.dbcommon.model;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "roles")
public class Role
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;


  public Long getId()
  {
	return id;
  }


  public String getName()
  {
	return name;
  }


  public void setName(String name)
  {
	this.name = name;
  }

}