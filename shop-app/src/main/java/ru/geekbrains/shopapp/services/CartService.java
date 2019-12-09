package ru.geekbrains.shopapp.services;


import ru.geekbrains.shopapp.dto.ProductDTO;
import ru.geekbrains.shopapp.services.model.CartItem;

import java.math.BigDecimal;
import java.util.List;


public interface CartService
{

  void addProduct(ProductDTO prod, int count);

  void removeProduct(ProductDTO prod, int count);

  void removeProduct(ProductDTO prod);

  List<CartItem> getCartItems();

  BigDecimal getSubTotal();

  void updateCart(CartItem item);

}