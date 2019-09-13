package com.vet.clinic.app.service.appointment;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentCancelDto implements Serializable
{

  private static final long serialVersionUID = 3286141079463667344L;

  private Long id;

  private String canellarionReason;

}
