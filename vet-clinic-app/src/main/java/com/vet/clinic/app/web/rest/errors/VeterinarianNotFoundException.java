package com.vet.clinic.app.web.rest.errors;

public class VeterinarianNotFoundException extends RuntimeException {
  public VeterinarianNotFoundException() {}

  public VeterinarianNotFoundException(Long veterinarianId) {
    super("Veterinarian: " + veterinarianId + " not found.");
  }
}
