package org.exemple.classes;

import org.exemple.Exceptions.IncorrectISBNSizeException;

public class IsbnValidator {
    public boolean validateIsbn(String ISBN) throws Exception {
        if (ISBN.length()==10) {
            return validateIsbn10(ISBN);
        } else if (ISBN.length()==13) {
            return validateIsbn13(ISBN);
        }
        throw new IncorrectISBNSizeException("");
    }

    private boolean validateIsbn13(String ISBN) {
        int total=0;
        for (int i=0; i<13;i++) {
            total += Character.getNumericValue(ISBN.charAt(i))* (i%2==0 ? 1 : 3);
        }
        return total % 10 == 0;
    }

    private boolean validateIsbn10(String ISBN) {
        int total=0;
        for (int i=0; i<10;i++) {
            total += Character.getNumericValue(ISBN.charAt(i)) * (10 - i);
        }
        return total % 11 == 0;
    }
}
