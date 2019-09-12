package com.vet.clinic.app.web.rest.appointment;

import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import com.vet.clinic.app.web.rest.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ValidAppointmentSchedule
public class AppointmentDto extends BaseDto
{

  /**
   * 
   */
  private static final long serialVersionUID = -6252935308118786796L;

  private Long id;

  @NotNull
  @ValidAppointmentDate
  private OffsetDateTime startTime;

  @NotNull
  @ValidAppointmentDate
  private OffsetDateTime endTime;

  private String description;

  @NotNull
  private Long petId;

  @NotNull
  private Long veterinarian;

}
