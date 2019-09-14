package com.vet.clinic.app.domain.pet;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import com.vet.clinic.app.domain.common.BaseEntity;
import com.vet.clinic.app.domain.common.BaseEntityListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityListeners({ BaseEntityListener.class })
@Entity
@NoArgsConstructor
public class PetOwner extends BaseEntity
{

  private static final long serialVersionUID = 5408569517994534460L;


  @Getter
  @Setter
  @Column
  private String firstName;

  @Getter
  @Setter
  @Column
  private String lastName;
  
  @Getter
  @Setter
  @Column
  private String phoneNumber;
  
  @Getter
  @Setter
  @Column
  @Email
  private String email;
  
  @Getter
  @Setter
  @OneToMany(mappedBy = "petOwner", cascade = ALL, fetch = LAZY, orphanRemoval = true)
  private Set<Pet> pets  = new HashSet<Pet>();



}

