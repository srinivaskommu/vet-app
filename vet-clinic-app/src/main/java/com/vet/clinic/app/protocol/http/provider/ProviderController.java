package com.vet.clinic.app.protocol.http.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.app.domain.provider.Provider;
import com.vet.clinic.app.domain.provider.ProviderRepository;

@RestController
public class ProviderController {
	
	@Autowired
	private ProviderRepository providerRepository;

	@GetMapping("/providers")
	public Iterable<Provider> getAll(){
	return  providerRepository.findAll();	
	}

}
