package com.vet.clinic.app.web.rest.pet;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.vet.clinic.app.web.rest.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

public class PetOwnerDto extends BaseDto
{
  public PetOwnerDto()
  {

  }

  @Override
  public String toString()
  {
    return "PetOwner []";
  }

  /**
   * 
   */
  private static final long serialVersionUID = 5408569517994534460L;

  @Getter
  @Setter
  @NotEmpty
  @Size(min = 3, max = 50)
  private String firstName;

  @Getter
  @Setter
  @NotEmpty
  @Size(min = 3, max = 50)
  private String lastName;

  @Getter
  @Setter
  @NotNull
  @Range(min = 10)
  private String phoneNumber;

  @Getter
  @Setter

  @Email
  @NotNull
  private String email;

  @Getter
  @Setter
  @Valid
  private Set<PetDto> pets = new HashSet<>();

}
