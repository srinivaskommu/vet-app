package com.vet.clinic.app.web.rest.pet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import com.vet.clinic.app.web.rest.mapper.pet.PetOwnerMapper;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class PetOwnerController {

  @Autowired
  private PetOwnerRepository petOwnerRepository;

  @Autowired
  PetOwnerMapper petOwnerMapper;


  @GetMapping("/petOwners")
  public List<PetOwnerDto> getAll() {

    return StreamSupport.stream(petOwnerRepository.findAll().spliterator(), false)
        .map(petOwnerMapper::toPetOwnerDto).collect(Collectors.toList());

  }



  @GetMapping("/petOwners/{id}")
  public PetOwnerDto getById(@PathVariable Long id) {
    Optional<PetOwner> dt = petOwnerRepository.findById(id);
    
    if(dt.isPresent()) {
      return petOwnerMapper.toPetOwnerDto(dt.get());
    }
    return null;
  }

//  @PostMapping("/petOwners")
//  public ResponseEntity<PetOwnerDto> createPetOwner(@RequestBody PetOwnerDto petOwner)
//      throws URISyntaxException {
//    log.debug("REST request to save PetOwner : {}", petOwner);
//    if (petOwner.getId() != null) {
//      throw new BadRequestAlertException("A new PetOwner cannot already have an ID", "Veterinarian",
//          "idexists");
//    }
//    PetOwner result = petOwnerRepository.save(petOwnerMapper.toPetOwner(petOwner));
//    return ResponseEntity
//        .created(new URI("/petOwners/" + result.getId())).headers(HeaderUtil
//            .createEntityCreationAlert("vsp", false, "PetOwner", result.getId().toString()))
//        .body(petOwnerMapper.toPetOwnerDto(result));
//  }
//
//  @PutMapping("/petOwners/{id}")
//  public ResponseEntity<PetOwner> updatePetOwner(@RequestBody PetOwner petOwner,
//      @PathVariable Long id) throws URISyntaxException {
//    log.debug("REST request to update PetOwner : {}", petOwner);
//    if (petOwner.getId() == null) {
//      throw new BadRequestAlertException("Invalid id", "PetOwner", "idnull");
//    }
//    PetOwner result = petOwnerRepository.save(petOwner);
//    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("vsp", false,
//        "Veterinarian", petOwner.getId().toString())).body(result);
//  }
//
//  @DeleteMapping("/petOwners/{id}")
//  public ResponseEntity<PetOwner> deleteVeternarian(@PathVariable Long id)
//      throws URISyntaxException {
//
//    petOwnerRepository.deleteById(id);
//
//    return ResponseEntity.noContent()
//        .headers(HeaderUtil.createEntityDeletionAlert("vsp", false, "PetOwner", id.toString()))
//        .build();
//  }

}
