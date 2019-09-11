package com.vet.clinic.app.web.rest.appointment;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
import com.vet.clinic.app.web.rest.mapper.appointment.AppointmentMapper;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;
import com.vet.clinic.app.web.rest.errors.VeterinarianNotFoundException;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class AppointmentController {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  AppointmentMapper appointmentMapper;



  @PostMapping("/appointments")
  public ResponseEntity<AppointmentDto> createAppointment(
      @Valid @RequestBody AppointmentDto aAppointmentDto, HttpServletRequest request)
      throws URISyntaxException {
    

      Appointment result = appointmentRepository.save(appointmentMapper.fromDto(aAppointmentDto));

      return created(ServletUriComponentsBuilder.fromContextPath(request).path("/appointments/{id}")
          .buildAndExpand(result.getId()).toUri()).build();      


  }
  


  @GetMapping("/appointments")
  public List<AppointmentDto> getAllAppointments() {

    return StreamSupport.stream(appointmentRepository.findAll().spliterator(), false)
        .map(appointmentMapper::toDto).collect(Collectors.toList());

  }



  @GetMapping("/appointments/{id}")
  public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
    Optional<Appointment> appointment = appointmentRepository.findById(id);

    return ResponseUtil.wrapOrNotFound(Optional.of(appointmentMapper.toDto(appointment.get())));
  }

  // @PatchMapping("/appointments/{id}")
  // public List<AppointmentDto> updateAppointment(@RequestBody Map<String, String> update,
  // @PathVariable Long id) {
  // return (List<AppointmentDto>) appointmentRepository.findAll();
  // }
  //
  //
  // @DeleteMapping("/appointments/{id}")
  // public List<AppointmentDto> deleteAppointment(@PathVariable Long id) {
  // return (List<AppointmentDto>) appointmentRepository.findAll();
  // }





}
