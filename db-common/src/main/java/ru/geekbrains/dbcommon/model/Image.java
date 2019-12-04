package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "images")
public class Image
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "content_type", nullable = false)
  private String contentType;

  @OneToOne(fetch = LAZY, cascade = ALL, optional = false, orphanRemoval = true)
  @JoinColumn(name = "data_id")
  private ImageData imageData;

  @ManyToMany(mappedBy = "images")
  private List<Product> products;


  public Image()
  {
  }


  public Image(String name, String contentType, ImageData data)
  {
	this.name = name;
	this.contentType = contentType;
	this.imageData = data;
  }


  public Long getId()
  {
	return id;
  }


  public String getName()
  {
	return name;
  }


  public String getContentType()
  {
	return contentType;
  }


  public ImageData getImageData()
  {
	return imageData;
  }


  public List<Product> getProducts()
  {
	return products;
  }


  public void setName(String name)
  {
	this.name = name;
  }


  public void setContentType(String contentType)
  {
	this.contentType = contentType;
  }


  public void setImageData(ImageData imageData)
  {
	this.imageData = imageData;
  }

}