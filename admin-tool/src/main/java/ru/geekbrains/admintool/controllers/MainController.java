package ru.geekbrains.admintool.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class MainController
{

  @RequestMapping("")
  public String indexPage()
  {
	return "index";
  }

}