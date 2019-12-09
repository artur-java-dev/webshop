package ru.geekbrains.admintool.services;


import ru.geekbrains.admintool.dto.UserDTO;
import ru.geekbrains.dbcommon.model.Role;

import java.util.List;
import java.util.Optional;


public interface UserService
{

  List<UserDTO> findAllUsers();

  Optional<UserDTO> findUserById(Long id);

  Optional<UserDTO> findByUserName(String username);

  boolean save(UserDTO user);

  boolean delete(Long id);

  List<Role> findAllRoles();

}