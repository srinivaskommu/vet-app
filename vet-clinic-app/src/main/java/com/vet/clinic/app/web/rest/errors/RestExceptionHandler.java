package com.vet.clinic.app.web.rest.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
  
  @ExceptionHandler(value = {RuntimeException.class})
  public ResponseEntity vehicleNotFound(RuntimeException ex, WebRequest request) {
      return notFound().build();
  }

}
