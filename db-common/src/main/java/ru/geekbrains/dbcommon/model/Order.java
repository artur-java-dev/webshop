package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "orders")
public class Order
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;


  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;


  @OneToMany(mappedBy = "order", cascade = ALL)
  private List<OrderItem> items;


  @OneToOne(optional = false)
  @JoinColumn(name = "details_id")
  private OrderDetails details;


  @Column(name = "payment_sum", nullable = false)
  private BigDecimal paymentSum;


  public Order()
  {
  }


  public Long getId()
  {
	return id;
  }


  public User getUser()
  {
	return user;
  }


  public List<OrderItem> getItems()
  {
	return items;
  }


  public OrderDetails getDetails()
  {
	return details;
  }


  public BigDecimal getPaymentSum()
  {
	return paymentSum;
  }


  public void setUser(User user)
  {
	this.user = user;
  }


  public void setItems(List<OrderItem> items)
  {
	this.items = items;
  }


  public void setDetails(OrderDetails details)
  {
	this.details = details;
  }


  public void setPaymentSum(BigDecimal paymentSum)
  {
	this.paymentSum = paymentSum;
  }

}