package com.vet.clinic.app.domain.pet;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.common.BaseEntity;
import com.vet.clinic.app.domain.common.BaseEntityListener;
import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners({ BaseEntityListener.class })
public class Pet extends BaseEntity {
  
  
  public Pet(){
    
  }

  /**
   * 
   */
  private static final long serialVersionUID = -4585684365380781274L;


  @Getter
  @Setter
  @Column
  private String name;
  
  @Getter
  @Setter
  @Column
  private Species spiecesType;

  @Getter
  @Setter
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "PET_OWNER_ID", insertable = true, updatable = true)
  private PetOwner petOwner;

  @Getter
  @Setter
  @Column
  private int age;

//  @Getter
//  @Setter
//  @OneToMany
//  private Set<Appointment> appointments;

  @Override
  public String toString() {
    return "PetOwner []";
  }

}
