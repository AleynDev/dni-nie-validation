package com.example.dni_validation.utils.annotations;

import com.example.dni_validation.utils.validations.SpanishDocumentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom validation annotation for Spanish documents.
 * <p>
 * This annotation can be applied to fields to validate whether the value
 * is a valid Spanish DNI or NIE. It uses the {@link SpanishDocumentValidator} class
 * to perform the actual validation logic.
 * <p>
 * Usage example:
 * <pre>
 * &#64;ValidSpanishDocument
 * private String document;
 * </pre>
 *
 * If the validation fails, the default message is: "The document is not valid (DNI or NIE)".
 */
@Documented
@Constraint(validatedBy = SpanishDocumentValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSpanishDocument {

    String message() default "The document is not valid (DNI or NIE)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}