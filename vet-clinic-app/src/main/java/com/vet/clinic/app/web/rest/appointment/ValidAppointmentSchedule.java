package com.vet.clinic.app.web.rest.appointment;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ANNOTATION_TYPE, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = AppointmentScheduleValidator.class)
public @interface ValidAppointmentSchedule
{

  String message() default "schedule wrong";

  int errorCode() default 4000;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
