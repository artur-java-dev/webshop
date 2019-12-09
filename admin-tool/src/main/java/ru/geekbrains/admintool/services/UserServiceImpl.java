package ru.geekbrains.admintool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.admintool.dto.UserDTO;
import ru.geekbrains.dbcommon.model.Role;
import ru.geekbrains.dbcommon.model.User;
import ru.geekbrains.dbcommon.repo.RoleRepository;
import ru.geekbrains.dbcommon.repo.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;


@Service
public class UserServiceImpl
		implements UserService
{

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private BCryptPasswordEncoder passwordEncoder;


  @Autowired
  @Qualifier("userRepository")
  public void setUserRepository(UserRepository urepo)
  {
	userRepository = urepo;
  }


  @Autowired
  @Qualifier("roleRepository")
  public void setRoleRepository(RoleRepository rrepo)
  {
	roleRepository = rrepo;
  }


  @Autowired
  public void setPasswordEncoder(BCryptPasswordEncoder encoder)
  {
	passwordEncoder = encoder;
  }


  @Override
  @Transactional
  public List<UserDTO> findAllUsers()
  {
	return userRepository.findAll().stream()
						 .map(UserDTO::new)
						 .collect(toList());
  }


  @Override
  @Transactional
  public Optional<UserDTO> findUserById(Long id)
  {
	return userRepository.findById(id)
						 .map(UserDTO::new);
  }


  @Override
  @Transactional
  public Optional<UserDTO> findByUserName(String username)
  {
	return userRepository.findOneByUserName(username)
						 .map(UserDTO::new);
  }


  @Override
  @Transactional
  public boolean save(UserDTO dto)
  {
	User user = new User();

	if (userRepository.findOneByUserName(dto.userName).isPresent())
	  return false;

	user.setUserName(dto.userName);
	String pwhash = passwordEncoder.encode(dto.password);
	user.setPassword(pwhash);
	user.setFirstName(dto.firstName);
	user.setLastName(dto.lastName);
	user.setEmail(dto.email);
	Role role = roleRepository.findOneByName("ROLE_CLIENT");
	user.setRoles(singletonList(role));

	userRepository.save(user);

	return true;
  }


  @Override
  @Transactional
  public boolean delete(Long id)
  {
	if (!userRepository.findById(id).isPresent())
	  return false;

	userRepository.deleteById(id);

	return true;
  }


  @Override
  @Transactional
  public List<Role> findAllRoles()
  {
	return roleRepository.findAll();
  }

}