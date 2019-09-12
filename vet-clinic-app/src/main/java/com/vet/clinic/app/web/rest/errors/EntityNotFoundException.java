package com.vet.clinic.app.web.rest.errors;

public class EntityNotFoundException extends RuntimeException
{

  private static final long serialVersionUID = 1479761934085985427L;

  public EntityNotFoundException()
  {
  }

  public EntityNotFoundException(String message)
  {
    super(message);
  }
}
