package com.vet.clinic.app.web.rest.appointment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.clinic.app.domain.appointment.Appointment;
import com.vet.clinic.app.domain.appointment.AppointmentRepository;
import com.vet.clinic.app.domain.appointment.AppointmentSearchRepository;
import com.vet.clinic.app.service.appointment.AppointmentService;
import com.vet.clinic.app.web.rest.mapper.appointment.AppointmentMapper;

@WebMvcTest(controllers = AppointmentController.class, secure = false)
@RunWith(SpringRunner.class)
public class AppointmentControllerTest
{
  @MockBean
  private AppointmentRepository appointmentRepository;

  @MockBean
  private AppointmentService appointmentService;

  @MockBean
  AppointmentSearchRepository appointmentSearchRepository;

  @MockBean
  AppointmentMapper appointmentMapper;
  
  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  MockMvc mockMvc;
  
  @Before
  public void setUp()
  {

    given(appointmentService.create(any(Appointment.class)))
        .willReturn(new Appointment());

    given(appointmentMapper.fromDto(any(AppointmentDto.class)))
        .willReturn(new Appointment());

    given(appointmentMapper.toDto(any(Appointment.class)))
        .willReturn(new AppointmentDto());
  }

  @Test
  public void to_search_should_also_call_count_internally() throws Exception
  {

    this.mockMvc
        .perform(
            get("/appointments")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(this.appointmentSearchRepository, times(1)).search(any(), any(), any(Integer.class), any(Integer.class));
    verify(this.appointmentSearchRepository, times(1)).count(any());
    verifyNoMoreInteractions(this.appointmentSearchRepository);
  }
  
  @Test
  public void create_appointment_with_wrong_schedule() throws Exception {
    
    AppointmentDto request = new AppointmentDto();
    request.setVeterinarian(1l);
    request.setPetId(1l);
    request.setStartTime(OffsetDateTime.now());
    request.setEndTime(OffsetDateTime.now());
    

      this.mockMvc
          .perform(
              post("/appointments")
                  .content(this.objectMapper.writeValueAsBytes(request))
                  .contentType(MediaType.APPLICATION_JSON)
          )
          .andExpect(status().isBadRequest());

      verifyNoMoreInteractions(appointmentService);
      verifyNoMoreInteractions(appointmentMapper);
      
  }
  
  @Test
  public void create_appointment_with_correct_schedule() throws Exception {
    
    AppointmentDto request = new AppointmentDto();
    request.setVeterinarian(1l);
    request.setPetId(1l);
    request.setStartTime(OffsetDateTime.now().plusDays(3));
    request.setEndTime(OffsetDateTime.now().plusDays(4));
    

      this.mockMvc
          .perform(
              post("/appointments")
                  .content(this.objectMapper.writeValueAsBytes(request))
                  .contentType(MediaType.APPLICATION_JSON)
          )
          .andExpect(status().isCreated());

      
      verify(appointmentService, times(1)).create(any(Appointment.class));
      verify(appointmentMapper, times(1)).toDto(any(Appointment.class));
      verify(appointmentMapper, times(1)).fromDto(any(AppointmentDto.class));
      verifyNoMoreInteractions(appointmentService);
      verifyNoMoreInteractions(appointmentMapper);

  }

}
