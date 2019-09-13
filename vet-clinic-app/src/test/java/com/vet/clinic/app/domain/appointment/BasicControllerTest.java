package com.vet.clinic.app.domain.appointment;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicControllerTest
{

  private MockMvc mockMvc;

  @Autowired
  WebApplicationContext applicationContext;

  @Autowired
  ObjectMapper objectMapper;

  @Before
  public void setup()
  {
    this.mockMvc = webAppContextSetup(this.applicationContext)
        .apply(springSecurity())
        .build();
  }
  
  @Test
  public void access_to_appointments_endpoint_must_be_restricted() throws Exception
  {
    this.mockMvc
        .perform(
            get("/vsp/appointments")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }
  


}
