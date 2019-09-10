package com.vet.clinic.app.domain.veterinarian;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.common.BaseEntity;
import com.vet.clinic.app.domain.common.BaseEntityListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners({ BaseEntityListener.class })
public class Veterinarian extends BaseEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8032080237278172048L;
	

	
	@Column
    private String firstName;
	
	
	@Column
    private String lastName;
	
	@Column
	private String phoneNumber;
	
	@Column
	@Email
    private String email;
	
	@Column
	private String city;
	
	@Column
	private String zipCode;
	
	@JsonInclude(value = Include.NON_NULL)
	@OneToMany
	private Set<Appointment> appointments;
	

}
