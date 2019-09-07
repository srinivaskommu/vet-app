package com.vet.clinic.app.domain.pet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.vet.clinic.app.domain.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PetOwner extends BaseEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5408569517994534460L;
	

	
	@Column
    private String firstName;
	
	
	@Column
    private String lastName;
	
	@Column
	private String phoneNumber;
	
	@Column
	@Email
    private String email;

	@OneToMany
	private Set<Pet> pets;
	
	

}

