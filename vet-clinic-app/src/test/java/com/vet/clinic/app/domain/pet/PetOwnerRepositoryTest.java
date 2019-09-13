package com.vet.clinic.app.domain.pet;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PetOwnerRepositoryTest
{
  @Autowired
  private PetOwnerRepository petOwnerRepository;
  
  @Autowired
  EntityManager entityManager;
  

  @Test
  public void save_and_verify()
  {
    PetOwner owner = new PetOwner();
    owner.setFirstName("mike767867"+Instant.now());
    owner.setLastName("smith8888"+Instant.now());
    owner.setEmail("hdsjhkjj@gmail.com");
    owner.setPhoneNumber("444-25753");

    Pet dog = new Pet();
    dog.setAge(10);
    dog.setName("rin0"+Instant.now());
    dog.setPetOwner(owner);
    dog.setSpiecesType(Species.DOG);

    owner.getPets().add(dog);

    PetOwner result =  petOwnerRepository.save(owner);
    
    entityManager.flush();
    entityManager.clear();
    
    PetOwner petOwner = petOwnerRepository.loadEntityById(result.getId());
    
    assertThat(petOwner.getFirstName()).isEqualTo(owner.getFirstName());
    assertThat(petOwner.getId()).isNotNull();
    assertThat(petOwner.getId()).isGreaterThan(0);
    assertThat(petOwner.getPets()).size().isEqualTo(1);
    
  }

}
