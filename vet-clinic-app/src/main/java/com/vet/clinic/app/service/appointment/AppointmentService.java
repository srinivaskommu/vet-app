package com.vet.clinic.app.service.appointment;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.appointment.AppointmentStatus;
import com.vet.clinic.app.domain.pet.PetRepository;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;
import com.vet.clinic.app.web.rest.appointment.AppointmentDto;
import com.vet.clinic.app.web.rest.errors.AppointmentExistsForPetException;
import com.vet.clinic.app.web.rest.errors.AppointmentExistsForVetException;

@Service
@Validated
public class AppointmentService
{

  @Autowired
  private AppointmentRepository appointmentRepository;
  
  @Autowired
  private VeterinarianRepository veterinarianRepository;
  
  @Autowired
  private PetRepository petRepository;

  @Transactional(propagation = REQUIRED, rollbackFor = Exception.class, readOnly = false)
  public Appointment create(@Valid @NotNull Appointment appointment)
  {
    if (anyConflictForPet(appointment))
    {
      throw new AppointmentExistsForPetException(appointment.getPet().getId());
    }

    if (anyConflictForVet(appointment))
    {
      throw new AppointmentExistsForVetException(appointment.getVeterinarian().getId());
    }

    appointment.setStatus(AppointmentStatus.ACTIVE);
    Appointment result = appointmentRepository.save(appointment);
    return result;
  }

  @Transactional(propagation = REQUIRED, rollbackFor = Exception.class, readOnly = false)
  public Appointment cancel(@Valid @NotNull AppointmentCancelDto appointment)
  {
    Appointment result = appointmentRepository.loadEntityById(appointment.getId());

    if(result.getEndTime().isBefore(OffsetDateTime.now())) {
      result.setStatus(AppointmentStatus.CANCELLED);      
    }

    return result;

  }
  
  @Transactional(propagation = REQUIRED, rollbackFor = Exception.class, readOnly = false)
  public Appointment reschedule(@Valid @NotNull AppointmentDto appointment)
  {
    Appointment result = appointmentRepository.loadEntityById(appointment.getId());


    return result;

  }
  
  public List<Appointment> search(Long vetId,
      Long petId,
      Integer page,
      Integer perPage)
  {

    if (petId != null && petRepository.findById(petId).isPresent())
    {
      return appointmentRepository.findByPet(petRepository.findById(petId).get());
    }
    else if (vetId != null && veterinarianRepository.findById(vetId).isPresent())
    {
      return appointmentRepository.findByVeterinarian(veterinarianRepository.findById(vetId).get());
    }
    else
    {
      return StreamSupport.stream(appointmentRepository.findAll().spliterator(), false)
          .collect(Collectors.toList());

    }

  }

  private boolean anyConflictForPet(@Valid @NotNull Appointment appointment)
  {
    return nullSafeStream(appointmentRepository.findByPetAndStartTimeBetween(appointment.getPet(),
        appointment.getStartTime(),appointment.getEndTime()))
            .filter(Objects::nonNull)
            .anyMatch(app -> AppointmentStatus.ACTIVE == app.getStatus()) ||

            nullSafeStream(appointmentRepository.findByPetAndEndTimeBetween(appointment.getPet(),
                appointment.getStartTime(),appointment.getEndTime()))
                    .filter(Objects::nonNull)
                    .anyMatch(app -> AppointmentStatus.ACTIVE == app.getStatus())           ;
  }

  private boolean anyConflictForVet(@Valid @NotNull Appointment appointment)
  {
    return nullSafeStream(appointmentRepository.findByVeterinarianAndStartTimeBetween(appointment.getVeterinarian(),
        appointment.getStartTime(),appointment.getEndTime()))
            .filter(Objects::nonNull)
            .anyMatch(app -> AppointmentStatus.ACTIVE == app.getStatus()) || 
            
            nullSafeStream(appointmentRepository.findByVeterinarianAndEndTimeBetween(appointment.getVeterinarian(),
                appointment.getStartTime(),appointment.getEndTime()))
                    .filter(Objects::nonNull)
                    .anyMatch(app -> AppointmentStatus.ACTIVE == app.getStatus())            ;

  }

  public static <T> Stream<T> nullSafeStream(final Collection<T> collection)
  {
    return Optional
        .ofNullable(collection)
        .orElse(Collections.emptySet())
        .stream();
  }

}