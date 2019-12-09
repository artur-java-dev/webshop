package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "categories")
public class Category
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @OneToMany(mappedBy = "category", cascade = ALL)
  private List<Product> products;


  public Category()
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


  public List<Product> getProducts()
  {
	return products;
  }


  public void setName(String name)
  {
	this.name = name;
  }

}