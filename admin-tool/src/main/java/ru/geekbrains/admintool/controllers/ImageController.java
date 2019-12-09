package ru.geekbrains.admintool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.admintool.services.ProductService;
import ru.geekbrains.dbcommon.model.Image;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/images")
public class ImageController
{

  private ProductService prodService;


  @Autowired
  public void setProductService(ProductService service)
  {
	prodService = service;
  }


  @GetMapping("/{id}")
  public void getProductImage(@PathVariable("id") Long id, HttpServletResponse res)
  throws IOException
  {
	Optional<Image> img = prodService.findImageById(id);

	if (img.isPresent())
	{
	  String contType = img.get().getContentType();
	  res.setContentType(contType);

	  byte[] data = img.get().getImageData().getData();
	  res.getOutputStream().write(data);
	}
  }

}