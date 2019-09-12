package com.vet.clinic.app.domain.veterinarian;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vet.clinic.app.domain.common.VspBaseRepository;

public interface VeterinarianRepository extends VspBaseRepository<Veterinarian, Long> {

}
