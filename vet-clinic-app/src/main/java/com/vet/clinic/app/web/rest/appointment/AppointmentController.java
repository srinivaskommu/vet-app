package com.vet.clinic.app.web.rest.appointment;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class AppointmentController {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @GetMapping("/appointments")
  public List<Appointment> getAllAppointments() {
    return (List<Appointment>) appointmentRepository.findAll();
  }

  @GetMapping("/appointments/{id}")
  public List<Appointment> getAppointmentById(@PathVariable Long id) {
    return (List<Appointment>) appointmentRepository.findAll();
  }


  @PostMapping("/appointments")
  public List<Appointment> createAppointment() {
    return (List<Appointment>) appointmentRepository.findAll();
  }

  @PatchMapping("/appointments/{id}")
  public List<Appointment> updateAppointment(@RequestBody Map<String, String> update,
      @PathVariable Long id) {
    return (List<Appointment>) appointmentRepository.findAll();
  }


  @DeleteMapping("/appointments/{id}")
  public List<Appointment> deleteAppointment(@PathVariable Long id) {
    return (List<Appointment>) appointmentRepository.findAll();
  }



}
