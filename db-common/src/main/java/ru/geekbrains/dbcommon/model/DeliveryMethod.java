package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "delivery_methods")
public class DeliveryMethod
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", unique = true, nullable = false)
  private String name;


  public DeliveryMethod()
  {
  }


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