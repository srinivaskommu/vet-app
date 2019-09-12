package com.vet.clinic.app.web.rest.errors;

public class AppointmentExistsForVetException extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = 3308932203011204496L;

  public AppointmentExistsForVetException()
  {
  }

  public AppointmentExistsForVetException(Long vetId)
  {
    super("Vet: " + vetId + " has appointment at the same time.");
  }
}
