package com.vet.clinic.app.service.appointment;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
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
    if (!CollectionUtils
        .isEmpty(appointmentRepository.findByPetAndStartTime(appointment.getPet(),
            appointment.getStartTime())))
    {
      throw new AppointmentExistsForPetException(appointment.getPet().getId());
    }

    if (!CollectionUtils.isEmpty(
        appointmentRepository.findByVeterinarianAndStartTime(appointment.getVeterinarian(),
            appointment.getStartTime())))
    {
      throw new AppointmentExistsForVetException(appointment.getVeterinarian().getId());
    }

    Appointment result = appointmentRepository.save(appointment);
    return result;
  }

}