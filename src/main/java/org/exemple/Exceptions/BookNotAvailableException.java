package org.exemple.Exceptions;

public class BookNotAvailableException extends Exception{
    public BookNotAvailableException(String msg) {
        super(msg);
    }
}