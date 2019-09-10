package com.vet.clinic.app.web.rest.pet;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import com.vet.clinic.app.web.rest.common.BaseDto;
import lombok.Getter;
import lombok.Setter;



public class PetOwnerDto extends BaseDto
{
  public PetOwnerDto(){
    
  }


  @Override
  public String toString() {
    return "PetOwner []";
  }

  /**
   * 
   */
  private static final long serialVersionUID = 5408569517994534460L;


  @Getter
  @Setter
  
  private String firstName;

  @Getter
  @Setter
  
  private String lastName;
  
  @Getter
  @Setter
  
  private String phoneNumber;
  
  @Getter
  @Setter
  
  @Email
  private String email;
  
  @Getter
  @Setter
  private Set<PetDto> pets  = new HashSet<>();



}

