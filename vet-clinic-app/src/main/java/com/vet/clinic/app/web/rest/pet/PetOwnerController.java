package com.vet.clinic.app.web.rest.pet;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import com.vet.clinic.app.web.rest.common.Utils;
import com.vet.clinic.app.web.rest.mapper.pet.PetOwnerMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class PetOwnerController
{

  @Autowired
  private PetOwnerRepository petOwnerRepository;

  @Autowired
  PetOwnerMapper petOwnerMapper;

  @GetMapping("/petOwners")
  public List<PetOwnerDto> getAll()
  {

    return StreamSupport.stream(petOwnerRepository.findAll().spliterator(), false)
        .map(petOwnerMapper::toPetOwnerDto).collect(Collectors.toList());

  }

  @GetMapping("/petOwners/{id}")
  public PetOwnerDto getById(@PathVariable Long id)
  {
    Optional<PetOwner> dt = petOwnerRepository.findById(id);

    if (dt.isPresent())
    {
      return petOwnerMapper.toPetOwnerDto(dt.get());
    }
    return null;
  }

  @PostMapping("/petOwners")
  public ResponseEntity<PetOwnerDto> createPetOwner(@Valid @RequestBody PetOwnerDto petOwner)
      throws URISyntaxException
  {
    PetOwner result = petOwnerRepository.save(petOwnerMapper.toPetOwner(petOwner));

    return new ResponseEntity<>(petOwnerMapper.toPetOwnerDto(result), Utils.headers(), HttpStatus.CREATED);
  }

  @PutMapping("/petOwners/{id}")
  public ResponseEntity<PetOwner> updatePetOwner(@RequestBody PetOwner petOwner,
      @PathVariable Long id) throws URISyntaxException
  {
    PetOwner result = petOwnerRepository.loadEntityById(id);
    result = petOwnerRepository.save(petOwner);

    return new ResponseEntity<>(result, Utils.headers(), HttpStatus.OK);

  }

  @DeleteMapping("/petOwners/{id}")
  public ResponseEntity<PetOwner> deleteVeternarian(@PathVariable Long id)
      throws URISyntaxException
  {
    PetOwner owner = petOwnerRepository.loadEntityById(id);

    petOwnerRepository.deleteById(owner.getId());

    return new ResponseEntity<>(null, Utils.headers(), HttpStatus.OK);
  }

}
