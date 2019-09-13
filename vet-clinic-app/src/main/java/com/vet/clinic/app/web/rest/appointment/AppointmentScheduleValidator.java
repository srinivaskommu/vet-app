package com.vet.clinic.app.web.rest.appointment;

import java.time.OffsetDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AppointmentScheduleValidator
    implements ConstraintValidator<ValidAppointmentSchedule, AppointmentDto>
{

  @Override
  public void initialize(ValidAppointmentSchedule constraintAnnotation)
  {

  }

  @Override
  public boolean isValid(AppointmentDto aAppointmentDto, ConstraintValidatorContext context)
  {
    if (aAppointmentDto.getStartTime().isAfter(aAppointmentDto.getEndTime()))
    {
      return false;
    }

    if (aAppointmentDto.getStartTime().isBefore(OffsetDateTime.now())
        || aAppointmentDto.getEndTime().isBefore(OffsetDateTime.now())
        || isLessThan15MinDuration(aAppointmentDto))
    {
      return false;
    }

    return true;
  }

  private boolean isLessThan15MinDuration(AppointmentDto aAppointmentDto)
  {
    if ((aAppointmentDto.getEndTime().toInstant().getEpochSecond()
        - aAppointmentDto.getStartTime().toInstant().getEpochSecond()) / 60 < 15)
    {
      return true;
    }
    return false;
  }

}
