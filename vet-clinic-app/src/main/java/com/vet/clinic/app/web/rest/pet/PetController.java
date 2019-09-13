package com.vet.clinic.app.web.rest.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import com.vet.clinic.app.web.rest.common.Utils;

import lombok.extern.slf4j.Slf4j;

@RestController("/petOwners/{petOwnerId}")
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class PetController
{

  @Autowired
  private PetOwnerRepository petOwnerRepository;

  @GetMapping("/pets")
  public Iterable<Pet> getAll(@PathVariable Long petOwnerId)
  {
    return petOwnerRepository.loadEntityById(petOwnerId).getPets();
  }

  @PostMapping("/pets")
  public ResponseEntity<PetDto> createPet(@RequestBody PetDto petDto, @PathVariable Long petOwnerId)
  {
    PetOwner owner = petOwnerRepository.loadEntityById(petOwnerId);
    Pet petDomain = toPet(petDto);
    petDomain.setPetOwner(owner);
    owner.getPets().add(petDomain);

    PetOwner result = petOwnerRepository.save(owner);

    Pet resultPet = result.getPets().stream().filter(pe -> pe.getName().equals(petDto.getName()))
        .findFirst().get();

    return new ResponseEntity<>(toDto(resultPet), Utils.headers(), HttpStatus.CREATED);

  }
  
  private Pet toPet(PetDto petOwner) {
    Pet pet = new Pet();
    pet.setAge(petOwner.getAge());
    pet.setId(petOwner.getId());
    pet.setName(petOwner.getName());
    pet.setSpiecesType(petOwner.getSpiecesType());

    return pet;
  }
  
  private PetDto toDto(Pet pet) {

    PetDto petDto = new PetDto();
    pet.setAge(pet.getAge());
    pet.setId(pet.getId());
    pet.setName(pet.getName());
    pet.setSpiecesType(pet.getSpiecesType());

    return petDto;
  }

}
