package com.vet.clinic.app.web.rest.mapper.pet;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.web.rest.pet.PetDto;
import com.vet.clinic.app.web.rest.pet.PetOwnerDto;

@Service
public class PetOwnerMapper {


  public PetOwnerDto toPetOwnerDto(PetOwner petOwner) {
    if (petOwner == null) {
      return null;
    } else {

      PetOwnerDto dto = new PetOwnerDto();
      dto.setId(petOwner.getId());
      dto.setFirstName(petOwner.getFirstName());
      dto.setLastName(petOwner.getLastName());
      dto.setEmail(petOwner.getEmail());

      dto.getPets()
          .addAll(petOwner.getPets().stream().map(this::convert).collect(Collectors.toSet()));

      return dto;
    }


  }

  private PetDto convert(Pet petOwner) {
    PetDto pet = new PetDto();
    pet.setAge(petOwner.getAge());
    pet.setId(petOwner.getId());


    return pet;
  }
  
  
  public PetOwner toPetOwner(PetOwnerDto petOwner) {
    if (petOwner == null) {
      return null;
    } else {

      PetOwner dto = new PetOwner();
      dto.setId(petOwner.getId());
      dto.setFirstName(petOwner.getFirstName());
      dto.setLastName(petOwner.getLastName());
      dto.setEmail(petOwner.getEmail());

      dto.getPets()
          .addAll(petOwner.getPets().stream().map(this::toPet).collect(Collectors.toSet()));

      dto.getPets().stream().forEach(e -> e.setPetOwner(dto));

      return dto;
    }


  }

  private Pet toPet(PetDto petOwner) {
    Pet pet = new Pet();
    pet.setAge(petOwner.getAge());
    pet.setId(petOwner.getId());

    return pet;
  }


}
