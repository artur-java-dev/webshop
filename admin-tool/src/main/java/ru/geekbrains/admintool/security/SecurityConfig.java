package ru.geekbrains.admintool.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig
		extends WebSecurityConfigurerAdapter
{

  private AuthService authService;


  @Autowired
  public void setAuthService(AuthService service)
  {
	authService = service;
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth)
  {
	auth.authenticationProvider(authenticationProvider());
  }


  @Override
  protected void configure(HttpSecurity http)
  throws Exception
  {
	http.authorizeRequests()
		.anyRequest()
		.permitAll()
		.antMatchers("/admin/**")
		.hasRole("ADMIN")
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/")
		.permitAll();
  }


  @Bean
  public BCryptPasswordEncoder passwordEncoder()
  {
	return new BCryptPasswordEncoder();
  }


  @Bean
  public DaoAuthenticationProvider authenticationProvider()
  {
	DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	auth.setUserDetailsService(authService);
	auth.setPasswordEncoder(passwordEncoder());

	return auth;
  }

}