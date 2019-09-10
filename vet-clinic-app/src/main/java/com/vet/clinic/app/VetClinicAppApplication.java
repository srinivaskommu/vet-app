package com.vet.clinic.app;

import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.pet.PetOwner;
import com.vet.clinic.app.domain.pet.PetOwnerRepository;
import com.vet.clinic.app.domain.pet.Species;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;

@SpringBootApplication
public class VetClinicAppApplication implements CommandLineRunner{

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	
	@Autowired
	private VeterinarianRepository veterinarianRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(VetClinicAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Veterinarian john = new Veterinarian();
		john.setFirstName("john");
		john.setLastName("john-j");
		john.setEmail("hdsjhkjj@gmail.com");
		john.setPhoneNumber("903-25753");
		john.setCity("SF");
		john.setZipCode("94230");

		veterinarianRepository.save(john);
		
		PetOwner owner = new PetOwner();
		owner.setFirstName("mike767867");
		owner.setLastName("smith8888");
		owner.setEmail("hdsjhkjj@gmail.com");
		owner.setPhoneNumber("903-25753");
		
		Pet dog = new Pet();
		dog.setAge(10);
		dog.setName("rin0");
		dog.setPetOwner(owner);
		dog.setSpiecesType(Species.DOG);
		
		Appointment app = new Appointment();
		app.setDescription("basic description");
		app.setPet(dog);
		app.setVeterinarian(john);
		app.setStartTime(OffsetDateTime.now());
        app.setEndTime(OffsetDateTime.now());
		

		
		owner.getPets().add(dog);
		
		petOwnerRepository.save(owner);
		
		appointmentRepository.save(app);

	}

}
