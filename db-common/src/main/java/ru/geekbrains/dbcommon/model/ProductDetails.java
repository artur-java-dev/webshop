package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "product_details")
public class ProductDetails
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "author", nullable = false)
  private String author;

  @Column(name = "language", nullable = false)
  private String language;

  @Column(name = "year", nullable = false)
  private Integer year;

  @Column(name = "pages_count", nullable = false)
  private Integer pagesCount;

  @Column(name = "cover_type", nullable = false)
  private String coverType;

  @Column(name = "publishing_house", nullable = false)
  private String publishingHouse;

  @Column(name = "printing_count", nullable = false)
  private Integer printingCount;

  @Column(name = "long_description")
  private String longDescription;


  public ProductDetails()
  {
  }


  public Long getId()
  {
	return id;
  }


  public String getAuthor()
  {
	return author;
  }


  public String getLanguage()
  {
	return language;
  }


  public Integer getYear()
  {
	return year;
  }


  public Integer getPagesCount()
  {
	return pagesCount;
  }


  public String getCoverType()
  {
	return coverType;
  }


  public String getPublishingHouse()
  {
	return publishingHouse;
  }


  public Integer getPrintingCount()
  {
	return printingCount;
  }


  public String getLongDescription()
  {
	return longDescription;
  }


  public void setAuthor(String author)
  {
	this.author = author;
  }


  public void setLanguage(String language)
  {
	this.language = language;
  }


  public void setYear(Integer year)
  {
	this.year = year;
  }


  public void setPagesCount(Integer pagesCount)
  {
	this.pagesCount = pagesCount;
  }


  public void setCoverType(String coverType)
  {
	this.coverType = coverType;
  }


  public void setPublishingHouse(String publishingHouse)
  {
	this.publishingHouse = publishingHouse;
  }


  public void setPrintingCount(Integer printingCount)
  {
	this.printingCount = printingCount;
  }


  public void setLongDescription(String longDescription)
  {
	this.longDescription = longDescription;
  }

}