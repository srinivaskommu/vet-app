package com.vet.clinic.app.web.rest.security;

import static org.springframework.http.ResponseEntity.ok;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vet.clinic.app.domain.user.UserRepository;
import com.vet.clinic.app.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class UserAuthenticationController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  TokenProvider jwtTokenProvider;

  @Autowired
  UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity signin(@RequestBody UserAuthenticationDto data) {

    try {
      String username = data.getUsername();
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
      String token = jwtTokenProvider.createToken(username,
          this.userRepository.findByUsername(username)
              .orElseThrow(
                  () -> new UsernameNotFoundException("Username " + username + "not found"))
              .getRoles());

      Map<Object, Object> model = new HashMap<>();
      model.put("username", username);
      model.put("token", token);
      return ok(model);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username/password supplied");
    }
  }


}
