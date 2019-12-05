package ru.geekbrains.shopapp.dto;


import ru.geekbrains.dbcommon.model.Image;
import ru.geekbrains.dbcommon.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class ProductDTO
{


  public final Long id;
  public final String title;
  public final BigDecimal price;
  public final String category;
  public final List<Long> imageIds;


  public ProductDTO(Product prod)
  {
	id = prod.getId();
	title = prod.getTitle();
	price = prod.getPrice();
	category = prod.getCategory().getName();

	List<Image> img = prod.getImages();
	imageIds = img.stream().map(Image::getId).collect(toList());
  }

}