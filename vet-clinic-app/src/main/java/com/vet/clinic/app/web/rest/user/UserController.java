//package com.vet.clinic.app.web.rest.user;
//
//import java.net.URISyntaxException;
//import java.util.List;
//import javax.validation.Valid;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import com.vet.clinic.app.domain.user.User;
//import com.vet.clinic.app.web.rest.veterinarian.VeterinarianController;
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@Slf4j
//@Validated
//public class UserController {
//  
//  @PostMapping("/users")
//  public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
//      log.debug("REST request to save User : {}", userDTO);
//
//      return null;
//  }
//
//  @PutMapping("/users")
//  public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
//    return null;
//  }
//
//  @GetMapping("/users")
//  public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
//    return null;
//  }
//
//  @GetMapping("/users/authorities")
//  public List<String> getAuthorities() {
//    return null;
//  }
//
//  @GetMapping("/users/{login")
//  public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
//    return null;
//  }
//
//  @DeleteMapping("/users/login")
//  public ResponseEntity<Void> deleteUser(@PathVariable String login) {
//    return null;
//  }
//
//}
