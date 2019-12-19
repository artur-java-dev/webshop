package ru.geekbrains.shopapp.security;


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
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service("authService")
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
	Optional<User> user = userRepo.findOneByUserName(userName);

	if (!user.isPresent())
	  throw new UsernameNotFoundException("Invalid username or password");

	String nm = user.get().getUserName();
	String pw = user.get().getPassword();
	List<Role> roles = user.get().getRoles();
	Collection<GrantedAuthority> auths = mapRolesToAuthorities(roles);

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