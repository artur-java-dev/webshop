package ru.geekbrains.shopapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {
		"ru.geekbrains.dbcommon",
		"ru.geekbrains.shopapp.controllers",
		"ru.geekbrains.shopapp.services",
})
public class ShopAppApplication
{

  public static void main(String[] args)
  {
	SpringApplication.run(ShopAppApplication.class, args);
  }

}
