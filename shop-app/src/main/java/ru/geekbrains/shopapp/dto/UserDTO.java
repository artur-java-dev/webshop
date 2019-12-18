package ru.geekbrains.shopapp.dto;


import ru.geekbrains.dbcommon.model.Role;
import ru.geekbrains.dbcommon.model.User;

import java.util.List;


public class UserDTO
{

  public Long id;
  public String userName;
  public String pword;
  public String pwordRepeat;
  public String firstName;
  public String lastName;
  public String email;
  public List<Role> roles;


  public UserDTO(User user)
  {
	id = user.getId();
	userName = user.getUserName();
	pword = user.getPassword();
	firstName = user.getFirstName();
	lastName = user.getLastName();
	email = user.getEmail();
	roles = user.getRoles();
  }


  public UserDTO()
  {
  }

}