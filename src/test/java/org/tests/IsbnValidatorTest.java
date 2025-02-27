package org.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsbnValidatorTest {
    @Test
    public void givenISBN10characters_whenIsValid_ShouldReturnTrue() {
        IsbnValidator validator = new IsbnValidator();

        boolean result = validator.validateIsbn("2213731381");

        assertTrue(result);
    }

    @Test
    public void givenISBN10characters_whenIsInvalid_ShouldReturnFalse() {
        IsbnValidator validator = new IsbnValidator();

        boolean result = validator.validateIsbn("2213731387");

        assertFalse(result);
    }
}
