package com.vet.clinic.app.domain.pet;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetOwnerRepository extends PagingAndSortingRepository<PetOwner, Long> {

}
