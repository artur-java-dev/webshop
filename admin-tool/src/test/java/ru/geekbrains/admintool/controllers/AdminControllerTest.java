package ru.geekbrains.admintool.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class AdminControllerTest
{

  @Autowired
  private MockMvc mvc;


  @Test
  public void testAdminPageWithoutLogin()
  throws Exception
  {
	MockHttpServletRequestBuilder reqBuild = get("/").contentType(TEXT_HTML);

	mvc.perform(reqBuild)
	   .andExpect(status().is3xxRedirection())
	   .andExpect(redirectedUrlTemplate("http://localhost/login"));
  }


  @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
  @Test
  public void testAdminPageWithLogin()
  throws Exception
  {
	MockHttpServletRequestBuilder reqBuild = get("/").contentType(TEXT_HTML);

	mvc.perform(reqBuild)
	   .andExpect(status().isOk())
	   .andExpect(view().name("index"));
  }

}