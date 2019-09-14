package com.vet.clinic.app.web.rest.pet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vet.clinic.app.domain.pet.Species;
import com.vet.clinic.app.web.rest.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

public class PetDto extends BaseDto
{

  /**
   * 
   */
  private static final long serialVersionUID = -4585684365380781274L;

  @Getter
  @Setter
  @NotBlank
  @Size(min = 3, max = 50)
  private String name;

  @Getter
  @Setter
  private Species spiecesType;

  @Getter
  @Setter
  private Long petOwnerId;

  @Getter
  @Setter
  @NotNull
  private Integer age;

}
