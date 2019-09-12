package com.vet.clinic.app.web.rest.appointment;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.service.appointment.AppointmentService;
import com.vet.clinic.app.web.rest.common.Utils;
import com.vet.clinic.app.web.rest.mapper.appointment.AppointmentMapper;

import io.github.jhipster.web.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class AppointmentController
{

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private AppointmentService appointmentService;

  @Autowired
  AppointmentMapper appointmentMapper;

  @Transactional(propagation = REQUIRED, rollbackFor = Exception.class, readOnly = false)
  @PostMapping("/appointments")
  public ResponseEntity<AppointmentDto> createAppointment(
      @Valid @RequestBody AppointmentDto aAppointmentDto, HttpServletRequest request)
      throws URISyntaxException
  {
    Appointment result = appointmentService.create(appointmentMapper.fromDto(aAppointmentDto));

    return created(ServletUriComponentsBuilder.fromContextPath(request).path("/appointments/{id}")
        .buildAndExpand(result.getId()).toUri()).build();

  }

  @GetMapping("/appointments")
  public List<AppointmentDto> getAllAppointments()
  {

    return StreamSupport.stream(appointmentRepository.findAll().spliterator(), false)
        .map(appointmentMapper::toDto).collect(Collectors.toList());

  }

  @GetMapping("/appointments/{id}")
  public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id)
  {
    Optional<Appointment> appointment = appointmentRepository.findById(id);

    return ResponseUtil.wrapOrNotFound(Optional.of(appointmentMapper.toDto(appointment.get())));
  }

  @PutMapping("/appointments/{id}")
  public ResponseEntity<AppointmentDto> rescheduleAppointment(@Valid @RequestBody AppointmentDto aAppointmentDto,
      @PathVariable Long id)
  {
    return null;
  }

  @PatchMapping("/appointments/{id}")
  public ResponseEntity<?> cancelAppointment(@RequestBody Map<String, String> update,
      @PathVariable Long id)
  {
    return null;
  }

  @DeleteMapping("/appointments/{id}")
  public ResponseEntity<?> deleteAppointment(@PathVariable Long id)
  {
    appointmentRepository.deleteById(id);

    return new ResponseEntity<>(ResponseEntity.noContent(), Utils.headers(), HttpStatus.OK);
  }

}
