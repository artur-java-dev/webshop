package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "addresses")
public class Address
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "region", nullable = false)
  private String region;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "building_number", nullable = false)
  private Integer buildingNum;

  @Column(name = "apartment_number", nullable = false)
  private Integer apartmentNum;


  public Address()
  {
  }


  public Long getId()
  {
	return id;
  }


  public String getRegion()
  {
	return region;
  }


  public String getCity()
  {
	return city;
  }


  public String getStreet()
  {
	return street;
  }


  public Integer getBuildingNum()
  {
	return buildingNum;
  }


  public Integer getApartmentNum()
  {
	return apartmentNum;
  }


  public void setRegion(String region)
  {
	this.region = region;
  }


  public void setCity(String city)
  {
	this.city = city;
  }


  public void setStreet(String street)
  {
	this.street = street;
  }


  public void setBuildingNum(Integer buildingNum)
  {
	this.buildingNum = buildingNum;
  }


  public void setApartmentNum(Integer apartmentNum)
  {
	this.apartmentNum = apartmentNum;
  }

}