package com.vet.clinic.app.web.rest.errors;

public class AppointmentExistsForPetException extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = 3308932203011204496L;

  public AppointmentExistsForPetException()
  {
  }

  public AppointmentExistsForPetException(Long petId)
  {
    super("Pet: " + petId + " has appointment at the same time.");
  }
}
