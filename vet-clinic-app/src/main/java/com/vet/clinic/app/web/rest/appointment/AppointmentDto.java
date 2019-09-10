package com.vet.clinic.app.web.rest.appointment;

import java.time.OffsetDateTime;
import com.vet.clinic.app.web.rest.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDto extends BaseDto {
  

  /**
   * 
   */
  private static final long serialVersionUID = -6252935308118786796L;


  private Long id;
  
  
  private OffsetDateTime startTime;
  
  
  private OffsetDateTime endTime;
  
  
  private String description;
  
  
  private Long petId;
  
  
  private Long veterinarian;

}
