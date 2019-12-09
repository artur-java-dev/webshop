package ru.geekbrains.admintool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.admintool.dto.ProductDTO;
import ru.geekbrains.admintool.services.ProductService;


@Controller
@RequestMapping("/products")
public class ProductsController
{

  private ProductService prodService;


  @Autowired
  public void setProductService(ProductService service)
  {
	prodService = service;
  }


  @GetMapping("")
  public String productsPage(Model model)
  {
	model.addAttribute("products", prodService.findAllProducts());

	return "products";
  }


  @GetMapping("/create")
  public String createProduct(Model model)
  {
	model.addAttribute("create", true);
	model.addAttribute("product", new ProductDTO());
	model.addAttribute("categories", prodService.findAllCategories());

	return "product_form";
  }


  @GetMapping("/edit/{id}")
  public String editProduct(Model model, @PathVariable("id") Long id)
  {
	model.addAttribute("edit", true);
	model.addAttribute("product", prodService.findProductById(id));
	model.addAttribute("categories", prodService.findAllCategories());

	return "product_form";
  }


  @PostMapping("/save")
  public String saveProduct(RedirectAttributes redirAttr, ProductDTO prod)
  {
	try
	{
	  prodService.save(prod);
	}
	catch (Exception e)
	{
	  redirAttr.addFlashAttribute("error", true);

	  if (prod.id == null)
		return "redirect:/create";

	  return "redirect:/edit/" + prod.id;
	}

	return "redirect:/";
  }


  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable("id") Long id)
  {
	prodService.delete(id);

	return "redirect:/";
  }

}