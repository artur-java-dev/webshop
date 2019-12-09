package ru.geekbrains.admintool.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.dbcommon.model.Role;
import ru.geekbrains.dbcommon.model.User;
import ru.geekbrains.dbcommon.repo.UserRepository;

import java.util.Collection;

import static java.util.stream.Collectors.toList;


@Service
@Transactional(readOnly = true)
public class AuthService
		implements UserDetailsService
{

  private UserRepository userRepo;


  @Autowired
  @Qualifier("userRepository")
  public void setUserRepository(UserRepository repo)
  {
	userRepo = repo;
  }


  @Override
  @Transactional
  public UserDetails loadUserByUsername(String userName)
  throws UsernameNotFoundException
  {
	User user = userRepo.findOneByUserName(userName);

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