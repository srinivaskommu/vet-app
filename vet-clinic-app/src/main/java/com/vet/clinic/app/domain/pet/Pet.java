package com.vet.clinic.app.domain.pet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pet extends BaseEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4585684365380781274L;
	

	
	@Column
    private String name;
	
	@Column
    private Species spiecesType;
	
	
	@Column
	private int age;
	
	
	@OneToMany
	private Set<Appointment> appointments;
	
	

}
