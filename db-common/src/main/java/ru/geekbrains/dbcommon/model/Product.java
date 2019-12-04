package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "products")
public class Product
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "short_description")
  private String description;

  @OneToOne
  @JoinColumn(name = "details_id")
  private ProductDetails details;


  @ManyToMany(fetch = LAZY, cascade = ALL)
  @JoinTable(name = "products_images",
			 joinColumns = @JoinColumn(name = "product_id"),
			 inverseJoinColumns = @JoinColumn(name = "image_id"))
  private List<Image> images;


  @ManyToOne(optional = false)
  private Category category;


  public Product()
  {
  }


  public Long getId()
  {
	return id;
  }


  public String getTitle()
  {
	return title;
  }


  public BigDecimal getPrice()
  {
	return price;
  }


  public String getDescription()
  {
	return description;
  }


  public ProductDetails getDetails()
  {
	return details;
  }


  public List<Image> getImages()
  {
	return images;
  }


  public Category getCategory()
  {
	return category;
  }


  public void setTitle(String title)
  {
	this.title = title;
  }


  public void setPrice(BigDecimal price)
  {
	this.price = price;
  }


  public void setDescription(String description)
  {
	this.description = description;
  }


  public void setDetails(ProductDetails details)
  {
	this.details = details;
  }


  public void setImages(List<Image> images)
  {
	this.images = images;
  }


  public void setCategory(Category category)
  {
	this.category = category;
  }

}