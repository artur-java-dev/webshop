package ru.geekbrains.admintool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.admintool.dto.UserDTO;
import ru.geekbrains.admintool.services.UserService;
import ru.geekbrains.dbcommon.model.User;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController
{

  private UserService userService;


  @Autowired
  public void setUserService(UserService service)
  {
	userService = service;
  }


  @GetMapping("/login")
  public String loginPage()
  {
	return "login";
  }


  @GetMapping("")
  public String usersPage(Model model)
  {
	model.addAttribute("users", userService.findAllUsers());

	return "users";
  }


  @GetMapping("/create")
  public String createUser(Model model)
  {
	model.addAttribute("create", true);
	model.addAttribute("user", new User());
	model.addAttribute("roles", userService.findAllRoles());

	return "user_form";
  }


  @GetMapping("/edit/{id}")
  public String editUser(Model model, @PathVariable("id") Long id)
  {
	model.addAttribute("edit", true);
	UserDTO user = userService.findUserById(id).orElseThrow(IllegalStateException::new);
	model.addAttribute("user", user);
	model.addAttribute("roles", userService.findAllRoles());

	return "user_form";
  }


  @PostMapping("/save")
  public String saveUser(@Valid UserDTO user, BindingResult bindRes)
  {
	if (bindRes.hasErrors())
	  return "user_form";

	userService.save(user);

	return "redirect:/users";
  }


  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") Long id)
  {
	userService.delete(id);

	return "redirect:/users";
  }


  @GetMapping("/roles")
  public String rolesPage(Model model)
  {
	model.addAttribute("roles", userService.findAllRoles());

	return "roles";
  }

}