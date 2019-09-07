package com.vet.clinic.app.protocol.http.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;

@RestController
public class AppointmentController {
	
@Autowired
private AppointmentRepository appointmentRepository;

@GetMapping("/appointments")
public List<Appointment> getAll(){
return (List<Appointment>) appointmentRepository.findAll();	
}

}
