package ru.geekbrains.admintool.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.admintool.dto.UserDTO;
import ru.geekbrains.dbcommon.model.User;
import ru.geekbrains.dbcommon.repo.UserRepository;

import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceTest
{

  private UserServiceImpl service;
  private UserRepository repository;


  @BeforeEach
  public void init()
  {
	service = new UserServiceImpl();
	repository = mock(UserRepository.class);
	service.setUserRepository(repository);
  }


  @Test
  public void findUser()
  {
	User user = new User();
	user.setId(1L);
	user.setUserName("user");

	Long id = user.getId();
	String name = user.getUserName();

	when(repository.findOneByUserName(name)).thenReturn(of(user));
	when(repository.findById(id)).thenReturn(of(user));

	Optional<UserDTO> result = service.findByUserName(name);
	assertTrue(result.isPresent());
	assertEquals("user", result.get().userName);

	result = service.findUserById(id);
	assertTrue(result.isPresent());
	assertEquals(1L, result.get().id);
  }

}