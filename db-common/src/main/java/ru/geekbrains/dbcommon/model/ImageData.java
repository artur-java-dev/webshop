package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "images_data")
public class ImageData
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Lob
  @Column(name = "data", nullable = false)
  private byte[] data;


  public ImageData()
  {
  }


  public ImageData(byte[] data)
  {
	this.data = data;
  }


  public Long getId()
  {
	return id;
  }


  public byte[] getData()
  {
	return data;
  }


  public void setData(byte[] data)
  {
	this.data = data;
  }

}