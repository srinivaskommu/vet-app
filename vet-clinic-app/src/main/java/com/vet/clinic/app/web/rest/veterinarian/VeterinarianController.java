package com.vet.clinic.app.web.rest.veterinarian;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;
import com.vet.clinic.app.web.rest.common.Utils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class VeterinarianController {

  @Autowired
  private VeterinarianRepository veterinarianRepository;

  @GetMapping("/veterinarians")
  public Iterable<Veterinarian> getAll(Pageable pageable) {
    return veterinarianRepository.findAll();
  }

  @GetMapping("/veterinarians/{id}")
  public ResponseEntity<Veterinarian> getVeterinarian(@PathVariable Long id) {
    log.debug("REST request to get Employee : {}", id);
    Veterinarian veterinarian = veterinarianRepository.loadEntityById(id);
    return new ResponseEntity<>(veterinarian, Utils.headers(), HttpStatus.OK);
  }

  @PostMapping("/veterinarians")
  public ResponseEntity<Veterinarian> createVeternarian(@Valid @RequestBody Veterinarian veterinarian)
      throws URISyntaxException {
    Veterinarian result = veterinarianRepository.save(veterinarian);
    
    return new ResponseEntity<>(result, Utils.headers(), HttpStatus.CREATED);
  }

  @PutMapping("/veterinarians/{id}")
  public ResponseEntity<Veterinarian> updateVeternarian(@RequestBody Veterinarian veterinarian,
      @PathVariable Long id) throws URISyntaxException {
    
    Veterinarian result = veterinarianRepository.save(veterinarian);
    return new ResponseEntity<>(result, Utils.headers(), HttpStatus.OK);
  }

  @DeleteMapping("/veterinarians/{id}")
  public ResponseEntity<Veterinarian> deleteVeternarian(@PathVariable Long id)
      throws URISyntaxException {
    
    Veterinarian veterinarian = veterinarianRepository.loadEntityById(id);

      veterinarianRepository.deleteById(veterinarian.getId());

    return new ResponseEntity<>(null, Utils.headers(), HttpStatus.OK);
  }

}
