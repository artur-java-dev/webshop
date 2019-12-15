package ru.geekbrains.eureka;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static org.springframework.boot.SpringApplication.run;


@EnableEurekaServer
@SpringBootApplication
public class RegistryServerApplication
{

  public static void main(String... args)
  {
	run(RegistryServerApplication.class, args);
  }

}