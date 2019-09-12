package com.vet.clinic.app.domain.pet;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vet.clinic.app.domain.common.VspBaseRepository;

public interface PetOwnerRepository extends VspBaseRepository<PetOwner, Long> {

}
