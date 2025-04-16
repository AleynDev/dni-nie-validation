package com.example.dni_validation.utils.validations;

import com.example.dni_validation.utils.annotations.ValidSpanishDocument;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator class for the {@link ValidSpanishDocument} annotation.
 * <p>
 * This class implements the logic to validate Spanish national identification numbers (DNI and NIE).
 * It checks the format and calculates the control letter based on the numeric part
 * to verify the integrity of the document.
 * <p>
 * Supported formats:
 * - DNI: 8 digits followed by a letter (e.g., 12345678Z)
 * - NIE: Starts with X, Y, or Z followed by 7 digits and a letter (e.g., X1234567T)
 * <p>
 * The control letter is validated using a modulus operation and a predefined character set.
 */
public class SpanishDocumentValidator implements ConstraintValidator<ValidSpanishDocument, String> {

    private static final String CONTROL_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    /**
     * Validates a Spanish DNI or NIE based on format and control letter.
     *
     * @param value   the document value to validate
     * @param context the validation context (not used)
     * @return true if the value is a valid DNI or NIE, false otherwise
     */
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
