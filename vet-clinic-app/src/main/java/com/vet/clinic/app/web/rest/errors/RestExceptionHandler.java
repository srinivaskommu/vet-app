package com.vet.clinic.app.web.rest.errors;

import static org.springframework.http.ResponseEntity.notFound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.vet.clinic.app.web.rest.common.Utils;
import com.vet.clinic.app.web.security.jwt.InvalidJwtAuthenticationException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler
{

  @ExceptionHandler(value = { VeterinarianNotFoundException.class })
  @ResponseBody
  public ResponseEntity<?> veterinarianNotFound(VeterinarianNotFoundException ex,
      WebRequest request)
  {
    return notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseEntity<?> handleProtocolValidationExceptions(
      MethodArgumentNotValidException ex)
  {
    List<String> errors = new ArrayList<String>();

    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
        errors.add(fieldError.getObjectName() + fieldError.getField() + " " + fieldError.getDefaultMessage());
    }
    
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return new ResponseEntity<Object>(apiError, Utils.headers(), apiError.getStatus());
  }

  @ExceptionHandler({ ConstraintViolationException.class })
  @ResponseBody
  public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex)
  {
    List<String> errors = new ArrayList<String>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations())
    {
      errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
          + violation.getMessage());
    }

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return new ResponseEntity<Object>(apiError, Utils.headers(), apiError.getStatus());
  }

  @ResponseBody
  @ExceptionHandler(value = { InvalidJwtAuthenticationException.class })
  public ResponseEntity<?> invalidJwtAuthentication(InvalidJwtAuthenticationException ex)
  {
    ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), ex.getLocalizedMessage());
    return new ResponseEntity<Object>(apiError, Utils.headers(), apiError.getStatus());
  }

  @ResponseBody
  @ExceptionHandler(value = { BadCredentialsException.class })
  public ResponseEntity<?> badCreds(BadCredentialsException ex)
  {
    log.error(" InvalidJwtAuthenticationException...", ex.getMessage());
    ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), ex.getLocalizedMessage());
    return new ResponseEntity<Object>(apiError, Utils.headers(), HttpStatus.UNAUTHORIZED);
  }

  @ResponseBody
  @ExceptionHandler(value = { AppointmentExistsForPetException.class, AppointmentExistsForVetException.class })
  public ResponseEntity<?> duplicate(Exception ex)
  {
    log.error(" AppointmentExistsForPetException...", ex.getMessage());

    ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex.getLocalizedMessage());
    return new ResponseEntity<Object>(apiError, Utils.headers(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public ResponseEntity<?> handleAllExceptions( Throwable throwable)
      throws IOException
  {
    log.error(" generic error.some thing terrible happend...",
        throwable.getMessage());

    ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getLocalizedMessage(),
        throwable.getLocalizedMessage());
    return new ResponseEntity<Object>(apiError, Utils.headers(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
