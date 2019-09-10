package com.vet.clinic.app.domain.common;

import java.time.OffsetDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseEntityListener {
  
  @PrePersist
  public void onPersist(BaseEntity baseEntity)
  {
    setCreateAndUpdateTimestamp(baseEntity);

    setCreateAndUpdateUser(baseEntity);

    log.debug("persisting entity " + baseEntity.getClass().getName());
  }

  @PreUpdate
  public void onPreUpdate(BaseEntity baseEntity)
  {
    updateTimestamp(baseEntity);

    updateUser(baseEntity);

    log.debug("updating entity " + baseEntity.getClass().getName());
  }
  
  private void setCreateAndUpdateTimestamp(BaseEntity baseEntity)
  {
    if (baseEntity.getCreateTime() == null)
    {
      baseEntity.setCreateTime(OffsetDateTime.now());
    }
    updateTimestamp(baseEntity);
  }

  private void updateTimestamp(BaseEntity baseEntity)
  {
    baseEntity.setUpdateTime(OffsetDateTime.now());
  }

  private void setCreateAndUpdateUser(BaseEntity baseEntity)
  {
    if (StringUtils.isEmpty(baseEntity.getCreatedBy()))
    {
      baseEntity.setCreatedBy("ADMIN_USER");
    }

    updateUser(baseEntity);
  }

  private void updateUser(BaseEntity baseEntity)
  {
    if (StringUtils.isEmpty(baseEntity.getUpdatedBy()))
    {
      baseEntity.setUpdatedBy("ADMIN_USER");
    }
  }

}
