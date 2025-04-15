package com.example.dni_validation.utils.validations;

import com.example.dni_validation.utils.annotations.ValidSpanishDocument;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SpanishDocumentValidator implements ConstraintValidator<ValidSpanishDocument, String> {

    private static final String CONTROL_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return false;

        String doc = value.toUpperCase().replaceAll("\\s+", "");
        if (doc.length() != 9) return false;

        String numberPart;
        char expectedLetter;

        if (doc.matches("^[XYZ]\\d{7}[A-Z]$")) {
            // NIE
            char first = doc.charAt(0);
            int prefix = switch (first) {
                case 'X' -> 0;
                case 'Y' -> 1;
                case 'Z' -> 2;
                default -> -1;
            };
            numberPart = prefix + doc.substring(1, 8);
            expectedLetter = doc.charAt(8);
        } else if (doc.matches("^\\d{8}[A-Z]$")) {
            // DNI
            numberPart = doc.substring(0, 8);
            expectedLetter = doc.charAt(8);
        } else {
            return false;
        }

        int index = Integer.parseInt(numberPart) % 23;
        char actualLetter = CONTROL_LETTERS.charAt(index);

        return actualLetter == expectedLetter;
    }
}
