package com.vet.clinic.app.web.rest.appointment;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidAppointmentDate.AppointmentDateValidator.class)
public @interface ValidAppointmentDate
{

  String message() default "{schedule wrong}";

  int errorCode() default 4001;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  static class AppointmentDateValidator
      implements ConstraintValidator<ValidAppointmentDate, OffsetDateTime>
  {
    @Override
    public void initialize(ValidAppointmentDate constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(OffsetDateTime input, ConstraintValidatorContext context)
    {
      return input.isAfter(OffsetDateTime.now())
          && isWeekDay(input)
          && isWorkHours(input);
    }

    private boolean isWorkHours(OffsetDateTime input)
    {
      return input.getHour() > 8 && input.getHour() < 17;
    }

    private boolean isWeekDay(OffsetDateTime input)
    {
      return (input.getDayOfWeek() != DayOfWeek.SATURDAY &&
          input.getDayOfWeek() != DayOfWeek.SUNDAY);
    }
  }

}
