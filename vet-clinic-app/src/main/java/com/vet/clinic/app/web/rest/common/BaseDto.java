package com.vet.clinic.app.web.rest.common;

import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

public class BaseDto implements Serializable
{
  
  /**
   * 
   */
  private static final long serialVersionUID = -6264224153332160726L;


  @Getter
  @Setter
  private Long id;

  
  @Getter
  @Setter
  private String createdBy;
  @Getter
  @Setter
  
  private String updatedBy;
  @Getter
  @Setter
  
  private OffsetDateTime createTime;
  
  @Getter
  @Setter
  
  private OffsetDateTime updateTime;

}
