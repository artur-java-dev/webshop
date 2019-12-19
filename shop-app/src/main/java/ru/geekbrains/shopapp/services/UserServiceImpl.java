package ru.geekbrains.shopapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.dbcommon.model.Role;
import ru.geekbrains.dbcommon.model.User;
import ru.geekbrains.dbcommon.repo.RoleRepository;
import ru.geekbrains.dbcommon.repo.UserRepository;
import ru.geekbrains.shopapp.dto.UserDTO;

import java.util.Optional;

import static java.util.Collections.singletonList;


@Service("userService")
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
  public Optional<UserDTO> findByUserName(String username)
  {
	return userRepository.findOneByUserName(username)
						 .map(UserDTO::new);
  }


  @Override
  @Transactional
  public boolean create(UserDTO dto)
  {
	User user = new User();

	if (userRepository.findOneByUserName(dto.userName).isPresent())
	  return false;

	user.setUserName(dto.userName);
	String pwhash = passwordEncoder.encode(dto.pword);
	user.setPassword(pwhash);
	user.setFirstName(dto.firstName);
	user.setLastName(dto.lastName);
	user.setEmail(dto.email);
	Role role = roleRepository.findOneByName("ROLE_CLIENT");
	user.setRoles(singletonList(role));

	userRepository.save(user);

	return true;
  }

}