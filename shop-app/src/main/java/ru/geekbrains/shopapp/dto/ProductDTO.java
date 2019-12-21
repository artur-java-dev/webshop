package ru.geekbrains.shopapp.dto;


import ru.geekbrains.dbcommon.model.Image;
import ru.geekbrains.dbcommon.model.Product;
import ru.geekbrains.dbcommon.model.ProductDetails;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class ProductDTO
{


  public Long id;
  public String title;
  public BigDecimal price;
  public String description;
  public String category;
  public List<Long> imageIds;
  public ProductDetails details;


  public ProductDTO(Product prod)
  {
	id = prod.getId();
	title = prod.getTitle();
	price = prod.getPrice();
	description = prod.getDescription();
	category = prod.getCategory().getName();
	List<Image> img = prod.getImages();
	imageIds = img.stream().map(Image::getId).collect(toList());
	details = prod.getDetails();
  }


  public ProductDTO()
  {
  }

}