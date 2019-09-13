package com.vet.clinic.app;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VetClinicAppApplicationTests
{
  @Autowired
  EntityManager entityManager;

  @Autowired
  DataSource datesource;

  @Test
  public void dependecies_must_be_loaded_on_start_up()
  {
    assertNotNull(entityManager);
  }

  @Test
  public void connection_pool_must_be_available()
  {
    assertNotNull(datesource);
  }

}
