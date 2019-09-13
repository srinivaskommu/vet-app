package com.vet.clinic.app.domain.appointment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.Properties;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import com.vet.clinic.app.domain.pet.Species;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests
{
  @Autowired
  private PetOwnerRepository petOwnerRepository;
  
  @Autowired
  EntityManager entityManager;
  
  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private VeterinarianRepository veterinarianRepository;
  
  @Autowired(required=true)
  AppointmentSearchRepository appointmentSearchRepository;
  
  

	@Test
	public void test() 
	{
    Veterinarian john = new Veterinarian();
    john.setFirstName("john");
    john.setLastName("john-j");
    john.setEmail("hdsjhkjj@gmail.com");
    john.setPhoneNumber("903-25753");
    john.setCity("SF");
    john.setZipCode("94230");

    veterinarianRepository.save(john);

    PetOwner owner = new PetOwner();
    owner.setFirstName("mike767867");
    owner.setLastName("smith8888");
    owner.setEmail("hdsjhkjj@gmail.com");
    owner.setPhoneNumber("903-25753");

    Pet dog = new Pet();
    dog.setAge(10);
    dog.setName("rin0");
    dog.setPetOwner(owner);
    dog.setSpiecesType(Species.DOG);

    owner.getPets().add(dog);

    petOwnerRepository.save(owner);

    Appointment appointment = new Appointment();
    appointment.setPet(dog);
    appointment.setVeterinarian(john);
    appointment.setStartTime(OffsetDateTime.now());
    appointment.setEndTime(OffsetDateTime.now());
    
    Appointment result = appointmentRepository.save(appointment);
    
    entityManager.flush();
    entityManager.clear();
    
    
    Properties prop = new Properties();
    prop.setProperty("vetId", result.getVeterinarian().getId().toString());
    
    assertThat(appointmentSearchRepository.count(prop)).isEqualTo(1);

    assertThat(appointmentSearchRepository.search(prop, null, 0, 100).size()).isEqualTo(1);
    
	}

}
