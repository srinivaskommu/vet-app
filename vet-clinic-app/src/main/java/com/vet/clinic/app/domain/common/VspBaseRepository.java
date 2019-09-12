package com.vet.clinic.app.domain.common;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vet.clinic.app.web.rest.errors.EntityNotFoundException;

@NoRepositoryBean
public interface VspBaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>
{

  default T loadEntityById(ID id) throws EntityNotFoundException
  {
    Optional<T> result = findById(id);

    if (result.isPresent())
    {

      return result.get();
    }
    else
    {
      throw new EntityNotFoundException("Oops it's missing.... for id : "+id);
    }
  }

}