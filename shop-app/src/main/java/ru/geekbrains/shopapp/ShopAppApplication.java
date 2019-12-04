package ru.geekbrains.shopapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"ru.geekbrains.dbcommon"})
public class ShopAppApplication
{

  public static void main(String[] args)
  {
	SpringApplication.run(ShopAppApplication.class, args);
  }

}
