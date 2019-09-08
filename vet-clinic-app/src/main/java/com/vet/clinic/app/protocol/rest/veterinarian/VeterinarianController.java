package com.vet.clinic.app.protocol.rest.veterinarian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;

@RestController
public class VeterinarianController {

	@Autowired
	private VeterinarianRepository veterinarianRepository;

	@GetMapping("/providers")
	public Iterable<Veterinarian> getAll() {
		return veterinarianRepository.findAll();
	}

}
