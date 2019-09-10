package com.vet.clinic.app.domain.appointment;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class Appointment extends BaseEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7461463034748017093L;
	
	@Id
	private Long id;
	
	@Column
    private OffsetDateTime startTime;
	
	@Column
    private OffsetDateTime endTime;
	
	@Column
	private String description;
	
	@OneToOne
	private Pet pet;
	
	@OneToOne
	private Veterinarian veterinarian;
	
	
}
