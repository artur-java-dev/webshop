package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "order_items")
public class OrderItem
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;


  @OneToOne(optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "count", nullable = false)
  private Integer count;

  @Column(name = "total_cost", nullable = false)
  private BigDecimal totalCost;


  @ManyToOne(optional = false)
  @JoinColumn(name = "order_id")
  private Order order;


  public OrderItem()
  {
  }


  public Long getId()
  {
	return id;
  }


  public Product getProduct()
  {
	return product;
  }


  public Integer getCount()
  {
	return count;
  }


  public Order getOrder()
  {
	return order;
  }


  public BigDecimal getTotalCost()
  {
	return totalCost;
  }


  public void setProduct(Product product)
  {
	this.product = product;
  }


  public void setCount(Integer count)
  {
	this.count = count;
  }


  public void setTotalCost(BigDecimal totalCost)
  {
	this.totalCost = totalCost;
  }

}