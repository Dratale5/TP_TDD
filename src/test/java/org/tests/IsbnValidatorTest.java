package org.tests;

import org.exemple.Exceptions.IncorrectISBNCharacterException;
import org.exemple.Exceptions.IncorrectISBNSizeException;
import org.exemple.classes.IsbnValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class IsbnValidatorTest {
    @Test
    public void givenISBN10characters_whenIsValid_ShouldReturnTrue() throws Exception {
        IsbnValidator validator = new IsbnValidator();

        boolean result = validator.validateIsbn("2213731381");

        assertTrue(result);
    }

    @Test
    public void givenISBN10characters_whenIsInvalid_ShouldReturnFalse() throws Exception {
        IsbnValidator validator = new IsbnValidator();

        boolean result = validator.validateIsbn("2213731387");

        assertFalse(result);
    }

    @Test
    public void givenISBN13characters_whenIsValid_ShouldReturnTrue() throws Exception  {
        IsbnValidator validator = new IsbnValidator();

        boolean result = validator.validateIsbn("9782266332439");

        assertTrue(result);
    }

    @Test
    public void givenISBN13characters_whenIsInvalid_ShouldReturnFalse() throws Exception {
        IsbnValidator validator = new IsbnValidator();

        boolean result = validator.validateIsbn("9782266332437");
        assertFalse(result);
    }

    @Test
    public void whenWrongLength_ShouldReturnException() throws Exception {
        IsbnValidator validator = new IsbnValidator();
        assertThrows(IncorrectISBNSizeException.class, () -> validator.validateIsbn("22137313877"));
    }

    @Test
    public void givenISBN10characters_WithLetterX_ShouldReturnTrue() throws Exception {
        IsbnValidator validator = new IsbnValidator();

        boolean result = validator.validateIsbn("043942089X");

        assertTrue(result);
    }

    @Test
    public void givenWrongISBN10characters_WithLetterX_ShouldReturnFalse() throws Exception {
        IsbnValidator validator = new IsbnValidator();
        
        boolean result = validator.validateIsbn("053942089X");

        assertFalse(result);
    }

    @Test
    public void WithLetterOtherThanX_ShouldReturnFalse() throws Exception {
        IsbnValidator validator = new IsbnValidator();

        assertThrows(IncorrectISBNCharacterException.class, () -> validator.validateIsbn("0393A4002X"));
    }
}
