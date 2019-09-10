package com.vet.clinic.app.web.rest.appointment;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.web.rest.mapper.appointment.AppointmentMapper;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class AppointmentController {

  @Autowired
  private AppointmentRepository appointmentRepository;
  
  @Autowired
  AppointmentMapper appointmentMapper;

//  @GetMapping("/appointments")
//  public List<AppointmentDto> getAllAppointments() {
//    return (List<AppointmentDto>) appointmentRepository.findAll();
//  }



  @PostMapping("/appointments")
  public ResponseEntity<AppointmentDto>  createAppointment(@RequestBody AppointmentDto aAppointmentDto) throws URISyntaxException 
  {
    Appointment appointment =   appointmentRepository.save(appointmentMapper.fromDto(aAppointmentDto));
    
    return ResponseEntity
        .created(new URI("/appointments/" + appointment.getId())).headers(HeaderUtil
            .createEntityCreationAlert("vsp", false, "Appointment", appointment.getId().toString()))
        .body(appointmentMapper.toDto(appointment));
  }

//  @PatchMapping("/appointments/{id}")
//  public List<AppointmentDto> updateAppointment(@RequestBody Map<String, String> update,
//      @PathVariable Long id) {
//    return (List<AppointmentDto>) appointmentRepository.findAll();
//  }
//
//
//  @DeleteMapping("/appointments/{id}")
//  public List<AppointmentDto> deleteAppointment(@PathVariable Long id) {
//    return (List<AppointmentDto>) appointmentRepository.findAll();
//  }
  
@GetMapping("/appointments/{id}")
public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
  Optional<Appointment> appointment = appointmentRepository.findById(id);

  return ResponseUtil.wrapOrNotFound(Optional.of(appointmentMapper.toDto(appointment.get())));
}






}
