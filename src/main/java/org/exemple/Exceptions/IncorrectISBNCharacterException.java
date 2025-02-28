package org.exemple.Exceptions;

public class IncorrectISBNCharacterException extends Exception{
    public IncorrectISBNCharacterException(String msg) {
        super(msg);
    }
}