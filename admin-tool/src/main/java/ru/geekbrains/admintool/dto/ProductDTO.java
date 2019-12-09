package ru.geekbrains.admintool.dto;


import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.dbcommon.model.Image;
import ru.geekbrains.dbcommon.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class ProductDTO
{


  public Long id;
  public String title;
  public BigDecimal price;
  public String category;
  public List<Long> imageIds;
  public List<MultipartFile> newImages;


  public ProductDTO(Product prod)
  {
	id = prod.getId();
	title = prod.getTitle();
	price = prod.getPrice();
	category = prod.getCategory().getName();

	List<Image> img = prod.getImages();
	imageIds = img.stream().map(Image::getId).collect(toList());

	newImages = new ArrayList<>();
  }


  public ProductDTO()
  {
  }

}