package com.vet.clinic.app.domain.veterinarian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vet.clinic.app.domain.common.BaseEntity;
import com.vet.clinic.app.domain.common.BaseEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners({ BaseEntityListener.class })
public class Veterinarian extends BaseEntity
{

  /**
   * 
   */
  private static final long serialVersionUID = -8032080237278172048L;

  @Column
  @NotEmpty
  @Size(min = 3, max = 50)
  private String firstName;

  @Column
  @NotEmpty
  @Size(min = 3, max = 50)
  private String lastName;

  @Column
  private String phoneNumber;

  @Column
  @Email
  private String email;

  @Column
  private String city;

  @Column
  private String zipCode;

}
