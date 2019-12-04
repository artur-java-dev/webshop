package ru.geekbrains.shopapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.shopapp.dto.ProductDTO;
import ru.geekbrains.shopapp.services.ProductService;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController
{

  private ProductService prodService;


  @Autowired
  @Qualifier("productService")
  public void setProductService(ProductService service)
  {
	prodService = service;
  }


  @GetMapping("/")
  public String getAllProducts(Model model)
  {
	List<ProductDTO> all = prodService.findAll();
	model.addAttribute("products", all);

	return "products";
  }


  @GetMapping("/{id}")
  public String getProduct(@PathVariable("id") Long id, Model model)
  {
	ProductDTO prod = prodService.findById(id)
								 .orElseThrow(IllegalArgumentException::new);
	model.addAttribute("product", prod);

	return "product_details";
  }

}