package ru.geekbrains.proxy;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import static org.springframework.boot.SpringApplication.run;


@EnableZuulProxy
@SpringBootApplication
public class ProxyApplication
{

  public static void main(String... args)
  {
	run(ProxyApplication.class, args);
  }

}