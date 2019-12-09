package ru.geekbrains.admintool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.admintool.services.ProductService;
import ru.geekbrains.dbcommon.model.Category;


@Controller
@RequestMapping("/categories")
public class CategoriesController
{

  private ProductService prodService;


  @Autowired
  public void setProductService(ProductService service)
  {
	prodService = service;
  }


  @GetMapping("")
  public String categoriesPage(Model model)
  {
	model.addAttribute("categories", prodService.findAllCategories());

	return "categories";
  }


  @GetMapping("/create")
  public String createCategory(Model model)
  {
	model.addAttribute("create", true);
	model.addAttribute("category", new Category());

	return "category_form";
  }


  @GetMapping("/edit/{id}")
  public String editCategory(Model model, @PathVariable("id") Long id)
  {
	model.addAttribute("edit", true);

	Category cat = prodService.findCategoryById(id)
							  .orElseThrow(IllegalStateException::new);
	model.addAttribute("category", cat);

	return "category_form";
  }


  @PostMapping("/save")
  public String saveCategory(RedirectAttributes redirAttr, Category category)
  {
	try
	{
	  prodService.saveCategory(category);
	}
	catch (Exception e)
	{
	  redirAttr.addFlashAttribute("error", true);

	  if (category.getId() == null)
		return "redirect:/create";

	  return "redirect:/edit" + category.getId();
	}

	return "redirect:/";
  }


  @GetMapping("/delete/{id}")
  public String deleteCategory(@PathVariable("id") Long id)
  {
	prodService.deleteCategory(id);

	return "redirect:/";
  }

}