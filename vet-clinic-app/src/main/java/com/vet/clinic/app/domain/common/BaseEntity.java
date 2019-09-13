package com.vet.clinic.app.domain.common;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class BaseEntity implements Serializable
{
  private static final long serialVersionUID = -3068965097414471188L;

  @Id
  @Getter
  @Setter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @Getter
  @Setter
  private String createdBy;
  @Getter
  @Setter
  @Column
  private String updatedBy;
  @Getter
  @Setter
  @Column
  private OffsetDateTime createTime;

  @Getter
  @Setter
  @Column
  private OffsetDateTime updateTime;

}
