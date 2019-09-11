package com.vet.clinic.app.web.rest.pet;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import lombok.extern.slf4j.Slf4j;

@RestController("/petOwners/{petOwnerId}")
@Slf4j
@Validated
public class PetController {

  @Autowired
  private PetOwnerRepository petOwnerRepository;

  @GetMapping("/pets")
  public Iterable<PetOwner> getAll() {
    return petOwnerRepository.findAll();
  }

  @GetMapping("/pets/{id}")
  public Iterable<PetOwner> getById() {
    return petOwnerRepository.findAll();
  }

//  @PostMapping("/pets")
//  public ResponseEntity<PetOwner> createPetOwner(@RequestBody PetOwner petOwner)
//      throws URISyntaxException {
//    log.debug("REST request to save PetOwner : {}", petOwner);
//
//    PetOwner result = petOwnerRepository.save(petOwner);
//    return ResponseEntity
//        .created(new URI("/PetOwners/" + result.getId())).headers(HeaderUtil
//            .createEntityCreationAlert("vsp", false, "Veterinarian", result.getId().toString()))
//        .body(result);
//  }

//  @PutMapping("/pets")
//  public ResponseEntity<PetOwner> updatePetOwner(@RequestBody PetOwner petOwner)
//      throws URISyntaxException {
//    log.debug("REST request to update PetOwner : {}", petOwner);
//    if (petOwner.getId() == null) {
//      throw new BadRequestAlertException("Invalid id", "Veterinarian", "idnull");
//    }
//    PetOwner result = petOwnerRepository.save(petOwner);
//    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("vsp", false,
//        "Veterinarian", petOwner.getId().toString())).body(result);
//  }

}
