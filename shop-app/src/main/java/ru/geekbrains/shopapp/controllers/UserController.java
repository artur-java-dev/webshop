package ru.geekbrains.shopapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.shopapp.dto.UserDTO;
import ru.geekbrains.shopapp.security.AuthService;
import ru.geekbrains.shopapp.services.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController
{

  private UserService userService;
  private AuthService authService;


  @Autowired
  @Qualifier("userService")
  public void setUserService(UserService service)
  {
	userService = service;
  }


  @Autowired
  @Qualifier("authService")
  public void setAuthService(AuthService service)
  {
	authService = service;
  }


  @GetMapping("/login")
  public String loginPage()
  {
	return "login";
  }


  @GetMapping("/authUser")
  public String authUser()
  {
	authService.loadUserByUsername();
	return "login";
  }


  @GetMapping("/registration")
  public String regPage(Model model)
  {
	model.addAttribute("user", new UserDTO());

	return "registration";
  }


  @PostMapping("/registration")
  public String createUser(@ModelAttribute("user") @Valid UserDTO user,
						   BindingResult result)
  {
	if (result.hasErrors())
	  return "registration";

	if (!user.pword.equals(user.pwordRepeat))
	{
	  result.rejectValue("pword", "", "Repeated password not matching");
	  result.rejectValue("pwordRepeat", "");
	  return "registration";
	}

	boolean success = userService.create(user);

	if (!success)
	{
	  result.rejectValue("userName", "", "Such user name exist already");
	  return "registration";
	}

	return "redirect:/login";
  }

}