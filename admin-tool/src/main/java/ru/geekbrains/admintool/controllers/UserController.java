package ru.geekbrains.admintool.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/user")
public class UserController
{

  @RequestMapping("/login")
  public String loginPage()
  {
	return "login";
  }

}