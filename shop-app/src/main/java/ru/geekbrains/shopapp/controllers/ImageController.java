package ru.geekbrains.shopapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.dbcommon.model.Image;
import ru.geekbrains.dbcommon.model.ImageData;
import ru.geekbrains.dbcommon.repo.ImageRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/image")
public class ImageController
{

  private ImageRepository repo;


  @Autowired
  @Qualifier("imageRepository")
  public void setImageRepository(ImageRepository ir)
  {
	repo = ir;
  }


  @GetMapping("/{imageId}")
  public void getProductImage(@PathVariable("imageId") Long id, HttpServletResponse resp)
  throws IOException
  {
	Optional<Image> img = repo.findById(id);

	if (!img.isPresent())
	  return;

	resp.setContentType(img.get().getContentType());

	ImageData data = img.get().getImageData();
	resp.getOutputStream().write(data.getData());
  }


}