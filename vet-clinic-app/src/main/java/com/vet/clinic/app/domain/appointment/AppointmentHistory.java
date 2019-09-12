package com.vet.clinic.app.domain.appointment;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import com.vet.clinic.app.domain.common.BaseEntity;
import com.vet.clinic.app.domain.common.BaseEntityListener;
import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners({ BaseEntityListener.class })
public class AppointmentHistory extends BaseEntity
{
  

}
