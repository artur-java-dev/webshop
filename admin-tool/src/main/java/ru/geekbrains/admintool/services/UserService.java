package ru.geekbrains.admintool.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.admintool.dto.UserDTO;
import ru.geekbrains.dbcommon.model.User;


public interface UserService
		extends UserDetailsService
{

  User findByUserName(String username);

  boolean save(UserDTO user);

}