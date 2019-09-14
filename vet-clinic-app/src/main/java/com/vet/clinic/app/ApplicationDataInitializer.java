package com.vet.clinic.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vet.clinic.app.domain.user.User;
import com.vet.clinic.app.domain.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationDataInitializer implements CommandLineRunner
{

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;


  @Override
  public void run(String... args) throws Exception
  {

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
