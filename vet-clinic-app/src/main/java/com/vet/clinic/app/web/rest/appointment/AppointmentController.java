package com.vet.clinic.app.web.rest.appointment;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.appointment.AppointmentSearchRepository;
import com.vet.clinic.app.domain.common.VspSortFilter;
import com.vet.clinic.app.service.appointment.AppointmentCancelDto;
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
  public final Integer MAX_DEFAULT = 30;

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private AppointmentService appointmentService;
  
  @Autowired
  AppointmentSearchRepository appointmentSearchRepository;

  
  @Autowired
  AppointmentMapper appointmentMapper;

  @Transactional(propagation = REQUIRED, rollbackFor = Exception.class, readOnly = false)
  @PostMapping("/appointments")
  public ResponseEntity<AppointmentDto> createAppointment(
      @Valid @RequestBody AppointmentDto aAppointmentDto, HttpServletRequest request)
      throws URISyntaxException
  {
    Appointment appointment = appointmentService.create(appointmentMapper.fromDto(aAppointmentDto));

    return new ResponseEntity<>(appointmentMapper.toDto(appointment), Utils.headers(), HttpStatus.CREATED);
  }

  @GetMapping("/appointments")
  public List<AppointmentDto> getAllAppointments(@RequestParam(required = false) Long vetId,
      @RequestParam(required = false) Long petId,
      @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer perPage,
      @RequestParam(required = false) String sortField,
      @RequestParam(required = false) String sortDir,
      @RequestHeader HttpHeaders headers)
  {

    Properties searchProperties = new Properties();
    if (petId != null)
      searchProperties.setProperty("petId", petId.toString());
    if (vetId != null)
      searchProperties.setProperty("vetId", vetId.toString());

    if (page == null)
    {
      page = 1;
    }

    if (perPage == null)
    {
      perPage = MAX_DEFAULT;
    }

    // Setting first and max results based on page number and number of records on one page.
    int start = (page - 1) * perPage;
    Integer count = 0;
    if ((count = appointmentSearchRepository.count(searchProperties)) != null)
    {

      headers.add("X-TOTAL", count.toString());
    }

    return appointmentSearchRepository
        .search(searchProperties, VspSortFilter.toSortFilter(sortField, sortDir), start, perPage).stream()
        .map(appointmentMapper::toDto).collect(Collectors.toList());

  }

  @GetMapping("/appointments/{id}")
  public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id)
  {
    Appointment appointment = appointmentRepository.loadEntityById(id);

    return ResponseUtil.wrapOrNotFound(Optional.of(appointmentMapper.toDto(appointment)));
  }

  @PutMapping("/appointments/{id}")
  public ResponseEntity<AppointmentDto> rescheduleAppointment(@Valid @RequestBody AppointmentDto aAppointmentDto,
      @PathVariable Long id)
  {
    return null;
  }

  @PatchMapping("/appointments/{id}")
  public ResponseEntity<?> cancelAppointment(@RequestBody AppointmentCancelDto dto,
      @PathVariable Long id)
  {
    dto.setId(id);
    Appointment appointment = appointmentService.cancel(dto);
    return new ResponseEntity<>(appointmentMapper.toDto(appointment), Utils.headers(), HttpStatus.CREATED);
  }

  @Transactional(propagation = REQUIRED, rollbackFor = Exception.class, readOnly = false)
  @DeleteMapping("/appointments/{id}")
  public ResponseEntity<?> deleteAppointment(@PathVariable Long id)
  {
    Appointment appointment = appointmentRepository.loadEntityById(id);
    appointmentRepository.deleteById(appointment.getId());

    return new ResponseEntity<>(ResponseEntity.noContent().build(), Utils.headers(), HttpStatus.OK);
  }

}