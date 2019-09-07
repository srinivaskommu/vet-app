package com.vet.clinic.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import com.vet.clinic.app.domain.provider.Provider;
import com.vet.clinic.app.domain.provider.ProviderRepository;

@SpringBootApplication
public class VetClinicAppApplication implements CommandLineRunner{

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(VetClinicAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Provider john = new Provider();
		john.setFirstName("john");
		john.setFirstName("john-j");
		john.setEmail("hdsjhkjj@gmail.com");
		john.setPhoneNumber("903-25753");
		providerRepository.save(john);
		
		PetOwner owner = new PetOwner();
		owner.setFirstName("mike");
		owner.setLastName("smith");
		
		Pet dog = new Pet();
		dog.setAge(10);
		dog.setName("rin0");
		
//		Set<Pet> dogs = new HashSet<>();
//		dogs.add(dog);
//		
//		owner.setPets(dogs);
		
		
		petOwnerRepository.save(owner);

	}

}
