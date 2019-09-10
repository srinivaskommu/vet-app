package com.vet.clinic.app.web.rest.pet;

import com.vet.clinic.app.domain.pet.Species;
import com.vet.clinic.app.web.rest.common.BaseDto;
import lombok.Getter;
import lombok.Setter;


public class PetDto extends BaseDto {


  public PetDto(){
    
  }

  /**
   * 
   */
  private static final long serialVersionUID = -4585684365380781274L;


  @Getter
  @Setter

  private String name;

  @Getter
  @Setter
  private Species spiecesType;

  @Getter
  @Setter
  private Long petOwnerId;

  @Getter
  @Setter
  private int age;


  @Override
  public String toString() {
    return "PetOwner []";
  }

}
