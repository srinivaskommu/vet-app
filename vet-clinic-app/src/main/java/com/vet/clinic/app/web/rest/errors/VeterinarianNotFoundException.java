package com.vet.clinic.app.web.rest.errors;

public class VeterinarianNotFoundException extends RuntimeException {
  public VeterinarianNotFoundException() {
  }

  public VeterinarianNotFoundException(Long vehicleId ) {
      super("Vehicle: " +vehicleId +" not found.");
  }
}