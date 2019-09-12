package com.vet.clinic.app;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import com.vet.clinic.app.domain.pet.Species;
import com.vet.clinic.app.domain.user.User;
import com.vet.clinic.app.domain.user.UserRepository;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner
{

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private PetOwnerRepository petOwnerRepository;

  @Autowired
  private VeterinarianRepository veterinarianRepository;

  @Override
  public void run(String... args) throws Exception
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

    userRepository.save(User.builder()
        .username("user")
        .password(this.passwordEncoder.encode("password"))
        .roles(Arrays.asList("ROLE_USER"))
        .build());

    userRepository.save(User.builder()
        .username("admin")
        .password(this.passwordEncoder.encode("password"))
        .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
        .build());

    userRepository.save(User.builder()
        .username("vet")
        .password(this.passwordEncoder.encode("password"))
        .roles(Arrays.asList("ROLE_VET"))
        .build());

    userRepository.save(User.builder()
        .username("pet")
        .password(this.passwordEncoder.encode("password"))
        .roles(Arrays.asList("ROLE_PETOWNER"))
        .build());

    log.debug("printing all users...");
    userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));

  }
}
