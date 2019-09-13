package com.vet.clinic.app.domain.appointment;

import java.time.OffsetDateTime;
import java.util.List;

import com.vet.clinic.app.domain.common.VspBaseRepository;
import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;

public interface AppointmentRepository extends VspBaseRepository<Appointment, Long>
{
  List<Appointment> findByVeterinarianAndStartTimeBetween(Veterinarian veterinarian, OffsetDateTime startTime,
      OffsetDateTime endTime);

  List<Appointment> findByPetAndStartTimeBetween(Pet pet, OffsetDateTime startTime, OffsetDateTime endTime);

  List<Appointment> findByVeterinarianAndEndTimeBetween(Veterinarian veterinarian, OffsetDateTime startTime,
      OffsetDateTime endTime);

  List<Appointment> findByPetAndEndTimeBetween(Pet pet, OffsetDateTime startTime, OffsetDateTime endTime);
  
  List<Appointment> findByVeterinarian(Veterinarian veterinarian);
  
  List<Appointment> findByPet(Pet pet);

}
