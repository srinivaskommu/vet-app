package com.vet.clinic.app.web.rest.mapper.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.pet.PetRepository;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;
import com.vet.clinic.app.web.rest.appointment.AppointmentDto;

@Service
public class AppointmentMapper
{

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private VeterinarianRepository veterinarianRepository;

  public AppointmentDto toDto(Appointment appointment)
  {
    if (appointment == null)
    {
      return null;
    }
    else
    {
      AppointmentDto dto = new AppointmentDto();
      dto.setId(appointment.getId());
      dto.setPetId(appointment.getPet().getId());
      dto.setVeterinarian(appointment.getVeterinarian().getId());
      dto.setStartTime(appointment.getStartTime());
      dto.setEndTime(appointment.getEndTime());
      dto.setDescription(appointment.getDescription());
      dto.setStatus(appointment.getStatus());

      dto.setCreatedBy(appointment.getCreatedBy());
      dto.setUpdatedBy(appointment.getUpdatedBy());
      dto.setCreateTime(appointment.getCreateTime());
      dto.setUpdateTime(appointment.getUpdateTime());

      return dto;
    }

  }

  public Appointment fromDto(AppointmentDto appointment)
  {

    if (appointment == null)
    {
      return null;
    }
    else
    {
      Appointment dto = new Appointment();
      dto.setId(appointment.getId());
      dto.setPet(petRepository.findById(appointment.getPetId()).get());
      dto.setVeterinarian(veterinarianRepository.findById(appointment.getVeterinarian()).get());
      dto.setStartTime(appointment.getStartTime());
      dto.setEndTime(appointment.getEndTime());
      dto.setDescription(appointment.getDescription());

      return dto;
    }

  }

}
