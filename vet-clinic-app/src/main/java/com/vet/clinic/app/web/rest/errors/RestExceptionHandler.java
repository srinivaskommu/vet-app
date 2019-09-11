package com.vet.clinic.app.web.rest.errors;

import static org.springframework.http.ResponseEntity.notFound;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.vet.clinic.app.web.rest.common.Utils;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

  @ExceptionHandler(value = {VeterinarianNotFoundException.class})
  @ResponseBody
  public ResponseEntity<?> veterinarianNotFound(VeterinarianNotFoundException ex,
      WebRequest request) {
    return notFound().build();
  }

  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseBody
  public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex,
      WebRequest request) {
    List<String> errors = new ArrayList<String>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
          + violation.getMessage());
    }

    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

    return new ResponseEntity<Object>(apiError, Utils.headers(), apiError.getStatus());
  }



}
