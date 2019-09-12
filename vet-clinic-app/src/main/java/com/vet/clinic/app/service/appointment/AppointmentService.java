package com.vet.clinic.app.service.appointment;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.appointment.AppointmentStatus;
import com.vet.clinic.app.web.rest.errors.AppointmentExistsForPetException;
import com.vet.clinic.app.web.rest.errors.AppointmentExistsForVetException;

@Service
@Validated
public class AppointmentService
{

  @Autowired
  private AppointmentRepository appointmentRepository;

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

  private boolean anyConflictForPet(@Valid @NotNull Appointment appointment)
  {
    return nullSafeStream(appointmentRepository.findByPetAndStartTime(appointment.getPet(),
        appointment.getStartTime()))
            .filter(Objects::nonNull)
            .anyMatch(app -> AppointmentStatus.ACTIVE == app.getStatus());
  }

  private boolean anyConflictForVet(@Valid @NotNull Appointment appointment)
  {
    return nullSafeStream(appointmentRepository.findByVeterinarianAndStartTime(appointment.getVeterinarian(),
        appointment.getStartTime()))
            .filter(Objects::nonNull)
            .anyMatch(app -> AppointmentStatus.ACTIVE == app.getStatus());

  }

  public static <T> Stream<T> nullSafeStream(final Collection<T> collection)
  {
    return Optional
        .ofNullable(collection)
        .orElse(Collections.emptySet())
        .stream();
  }

}