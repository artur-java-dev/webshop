package ru.geekbrains.shopapp.services;


import ru.geekbrains.shopapp.dto.UserDTO;

import java.util.Optional;


public interface UserService
{

  Optional<UserDTO> findByUserName(String username);

  boolean create(UserDTO user);

}