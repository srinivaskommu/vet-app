package com.vet.clinic.app.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.vet.clinic.app.domain.user.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService
{

  private UserRepository userRepository;

  public CustomUserDetailsService(UserRepository users)
  {
    this.userRepository = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    return this.userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
  }
}
