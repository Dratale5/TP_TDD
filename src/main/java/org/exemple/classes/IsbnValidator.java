package org.exemple.classes;

public class IsbnValidator {
    public boolean validateIsbn(String ISBN) {
        int total=0;
        for (int i=0; i<10;i++) {
            total += Character.getNumericValue(ISBN.charAt(i)) * (10 - i);
        }
        return total % 11 == 0;
    }
}
