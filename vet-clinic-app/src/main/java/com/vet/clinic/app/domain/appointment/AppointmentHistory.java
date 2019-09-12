package com.vet.clinic.app.domain.appointment;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

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
public class AppointmentHistory extends BaseEntity
{
  @Id
  private Long id;

  @Column
  private OffsetDateTime startTime;

  @Column
  private OffsetDateTime endTime;

  @Column
  private String description;
  
  
  

}