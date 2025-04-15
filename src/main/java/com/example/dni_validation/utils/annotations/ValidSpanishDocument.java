package com.example.dni_validation.utils.annotations;

import com.example.dni_validation.utils.validations.SpanishDocumentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpanishDocumentValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSpanishDocument {

    String message() default "The document is not valid (DNI or NIE)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}