package ru.geekbrains.dbcommon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "order_details")
public class OrderDetails
		implements Serializable
{

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;


  @Column(name = "order_number", nullable = false, unique = true)
  private String number;

  @Column(name = "delivery_date", nullable = false, columnDefinition = "DATE")
  private LocalDate deliveryDate;

  @OneToOne
  @JoinColumn(name = "delivery_address_id", nullable = false)
  private Address deliveryAddress;

  @OneToOne
  @JoinColumn(name = "addressee_id", nullable = false)
  private Customer addressee;

  @OneToOne
  @JoinColumn(name = "delivery_method_id", nullable = false)
  private DeliveryMethod deliveryMethod;

  @OneToOne
  @JoinColumn(name = "payment_method_id", nullable = false)
  private PaymentMethod paymentMethod;


  @OneToOne(mappedBy = "details")
  private Order order;


  public OrderDetails()
  {
  }


  public Long getId()
  {
	return id;
  }


  public String getNumber()
  {
	return number;
  }


  public LocalDate getDeliveryDate()
  {
	return deliveryDate;
  }


  public Address getDeliveryAddress()
  {
	return deliveryAddress;
  }


  public Customer getAddressee()
  {
	return addressee;
  }


  public DeliveryMethod getDeliveryMethod()
  {
	return deliveryMethod;
  }


  public PaymentMethod getPaymentMethod()
  {
	return paymentMethod;
  }


  public Order getOrder()
  {
	return order;
  }


  public void setNumber(String number)
  {
	this.number = number;
  }


  public void setDeliveryDate(LocalDate deliveryDate)
  {
	this.deliveryDate = deliveryDate;
  }


  public void setDeliveryAddress(Address deliveryAddress)
  {
	this.deliveryAddress = deliveryAddress;
  }


  public void setAddressee(Customer addressee)
  {
	this.addressee = addressee;
  }


  public void setDeliveryMethod(DeliveryMethod deliveryMethod)
  {
	this.deliveryMethod = deliveryMethod;
  }


  public void setPaymentMethod(PaymentMethod paymentMethod)
  {
	this.paymentMethod = paymentMethod;
  }


  public void setOrder(Order order)
  {
	this.order = order;
  }

}