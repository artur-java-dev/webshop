package ru.geekbrains.shopapp.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.shopapp.dto.ProductDTO;
import ru.geekbrains.shopapp.services.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CartServiceTest
{

  private CartService service;


  @BeforeEach
  public void init()
  {
	service = new CartServiceImpl();
  }


  @Test
  public void addProduct()
  {
	ProductDTO prod = new ProductDTO();
	prod.id = 1L;
	prod.title = "Product";
	prod.price = new BigDecimal(1000);

	service.addProduct(prod, 10);

	List<CartItem> items = service.getCartItems();
	assertNotNull(items);
	assertEquals(1, items.size());

	CartItem item = items.get(0);
	assertEquals(10, item.getCount());
	assertEquals(1L, item.getProductId());
	assertEquals(new BigDecimal(10000), item.getTotalPrice());

	prod = item.getProduct();
	assertEquals(1L, prod.id);
	assertEquals("Product", prod.title);
	assertEquals(new BigDecimal(1000), prod.price);
  }


  @Test
  public void removeProduct()
  {
	ProductDTO prod = new ProductDTO();
	prod.id = 1L;
	prod.title = "Product";
	prod.price = new BigDecimal(1000);

	service.addProduct(prod, 10);
	service.removeProduct(prod, 3);

	List<CartItem> items = service.getCartItems();
	assertNotNull(items);
	assertEquals(1, items.size());
	assertEquals(7, items.get(0).getCount());
  }


  @Test
  public void removeProductWithoutAdding()
  {
	ProductDTO prod = new ProductDTO();
	prod.id = 1L;
	prod.title = "Product";
	prod.price = new BigDecimal(1000);

	assertThrows(IllegalArgumentException.class,
				 () -> service.removeProduct(prod, 3));
  }

}