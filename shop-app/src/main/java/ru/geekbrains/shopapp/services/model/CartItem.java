package ru.geekbrains.shopapp.services.model;


import ru.geekbrains.shopapp.dto.ProductDTO;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;


public class CartItem
{

  private ProductDTO product;
  private int count;


  public CartItem(ProductDTO prod, int count)
  {
	product = prod;
	this.count = count;
  }


  public ProductDTO getProduct()
  {
	return product;
  }


  public Long getProductId()
  {
	return product.id;
  }


  public int getCount()
  {
	return count;
  }


  public void setProduct(ProductDTO prod)
  {
	product = prod;
  }


  public void increaseCountBy(int count)
  {
	this.count += count;
  }


  public void decreaseCountBy(int count)
  {
	if (count >= this.count)
	  throw new IllegalArgumentException("arg exceed count");

	this.count -= count;
  }


  public BigDecimal getTotalPrice()
  {
	BigDecimal cnt = valueOf(count);
	BigDecimal total = product.price.multiply(cnt);

	return total;
  }

}