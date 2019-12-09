package ru.geekbrains.shopapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.shopapp.dto.ProductDTO;
import ru.geekbrains.shopapp.services.CartService;
import ru.geekbrains.shopapp.services.ProductService;
import ru.geekbrains.shopapp.services.model.CartItem;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/cart")
public class CartController
{

  private CartService cartService;
  private ProductService prodService;


  @Autowired
  @Qualifier("cartService")
  public void setCartService(CartService service)
  {
	cartService = service;
  }


  @Autowired
  @Qualifier("productService")
  public void setProductService(ProductService service)
  {
	prodService = service;
  }


  @GetMapping("/")
  public String cartPage(Model model)
  {
	model.addAttribute("cartItems", cartService.getCartItems());
	model.addAttribute("subTotal", cartService.getSubTotal());

	return "cart";
  }


  @RequestMapping(value = "/", method = POST)
  public String updateCart(CartItem item)
  {
	Long id = item.getProductId();
	ProductDTO prod = prodService.findProductById(id)
								 .orElseThrow(IllegalArgumentException::new);

	item.setProduct(prod);
	cartService.updateCart(item);

	return "redirect:/cart";
  }


}