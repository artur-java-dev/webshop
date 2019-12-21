package ru.geekbrains.shopapp.services;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.geekbrains.shopapp.dto.ProductDTO;
import ru.geekbrains.shopapp.services.model.CartItem;

import java.math.BigDecimal;
import java.util.*;

import static java.math.BigDecimal.ZERO;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;


@Service("cartService")
@Scope(scopeName = "session", proxyMode = TARGET_CLASS)
public class CartServiceImpl
		implements CartService
{

  private Map<Long, CartItem> itemsByProductID;


  public CartServiceImpl()
  {
	itemsByProductID = new HashMap<>();
  }


  @Override
  public void addProduct(ProductDTO prod, int count)
  {
	if (itemsByProductID.containsKey(prod.id))
	{
	  CartItem item = itemsByProductID.get(prod.id);
	  item.increaseCountBy(count);
	  return;
	}

	CartItem item = new CartItem(prod, count);
	itemsByProductID.put(prod.id, item);
  }


  @Override
  public void removeProduct(ProductDTO prod, int count)
  {
	if (!itemsByProductID.containsKey(prod.id))
	  throw new IllegalArgumentException("Cart are not contains passed product");

	CartItem item = itemsByProductID.get(prod.id);

	if (item.getCount() > count)
	  item.decreaseCountBy(count);
	else
	  itemsByProductID.remove(prod.id);
  }


  @Override
  public void removeProduct(ProductDTO prod)
  {
	if (!itemsByProductID.containsKey(prod.id))
	  throw new IllegalArgumentException("Cart are not contains passed product");

	itemsByProductID.remove(prod.id);
  }


  @Override
  public List<CartItem> getCartItems()
  {
	return new ArrayList<>(itemsByProductID.values());
  }


  @Override
  public BigDecimal getSubTotal()
  {
	return itemsByProductID.values().stream()
						   .map(CartItem::getTotalPrice)
						   .reduce(ZERO, BigDecimal::add);
  }


  @Override
  public void updateCart(CartItem item)
  {
	itemsByProductID.put(item.getProductId(), item);
  }

}