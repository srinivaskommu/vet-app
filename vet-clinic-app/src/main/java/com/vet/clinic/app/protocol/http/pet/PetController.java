package com.vet.clinic.app.protocol.http.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;

@RestController
public class PetController {
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;

	@GetMapping("/pets")
	public Iterable<PetOwner> getAll(){
	return  petOwnerRepository.findAll();	
	}


}
