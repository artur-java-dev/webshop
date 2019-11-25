package ru.geekbrains.admintool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.admintool.dto.UserDTO;
import ru.geekbrains.dbcommon.model.Role;
import ru.geekbrains.dbcommon.model.User;
import ru.geekbrains.dbcommon.repo.RoleRepository;
import ru.geekbrains.dbcommon.repo.UserRepository;

import java.util.Collection;

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
  public void setUserRepository(UserRepository repository)
  {
	userRepository = repository;
  }


  @Autowired
  public void setRoleRepository(RoleRepository repository)
  {
	roleRepository = repository;
  }


  @Autowired
  public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder)
  {
	this.passwordEncoder = passwordEncoder;
  }


  @Override
  @Transactional
  public User findByUserName(String username)
  {
	return userRepository.findOneByUserName(username);
  }


  @Override
  @Transactional
  public boolean save(UserDTO dto)
  {
	User user = new User();

	if (findByUserName(dto.userName) != null)
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
  public UserDetails loadUserByUsername(String userName)
  throws UsernameNotFoundException
  {
	User user = userRepository.findOneByUserName(userName);

	if (user == null)
	  throw new UsernameNotFoundException("Invalid username or password");

	String nm = user.getUserName();
	String pw = user.getPassword();
	Collection<GrantedAuthority> auths = mapRolesToAuthorities(user.getRoles());

	UserDetails ud = new org.springframework.security.core.userdetails.User(nm, pw, auths);

	return ud;
  }


  private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
  {
	return roles.stream()
				.map(x -> new SimpleGrantedAuthority(x.getName()))
				.collect(toList());
  }

}