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


@Controller
@RequestMapping("/catalog")
public class ProductController
{

  private ProductService prodService;


  @Autowired
  @Qualifier("productService")
  public void setProductService(ProductService service)
  {
	prodService = service;
  }


  @GetMapping("")
  public String getAllProducts(Model model)
  {
	model.addAttribute("products", prodService.findAllProducts());
	model.addAttribute("categories", prodService.findAllCategories());

	return "catalog";
  }


  @GetMapping("/{id}")
  public String getProduct(@PathVariable("id") Long id, Model model)
  {
	ProductDTO prod = prodService.findProductById(id)
								 .orElseThrow(IllegalArgumentException::new);
	model.addAttribute("product", prod);

	return "product_details";
  }


  @GetMapping("/category/{id}")
  public String getProductsInCategory(@PathVariable("id") Long id, Model model)
  {
	model.addAttribute("products", prodService.findProductsByCategory(id));

	return "catalog";
  }

}