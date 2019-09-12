package com.vet.clinic.app.domain.appointment;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;

public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, Long>
{

  List<Appointment> findByVeterinarianAndStartTime(Veterinarian veterinarian, OffsetDateTime startTime);

  List<Appointment> findByPetAndStartTime(Pet pet, OffsetDateTime startTime);

}
