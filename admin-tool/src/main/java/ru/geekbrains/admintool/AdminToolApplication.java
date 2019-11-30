package ru.geekbrains.admintool;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;


@SpringBootApplication
@ComponentScan(basePackages = {"ru.geekbrains.dbcommon"})
public class AdminToolApplication
{

  public static void main(String[] args)
  {
	run(AdminToolApplication.class, args);
  }

}