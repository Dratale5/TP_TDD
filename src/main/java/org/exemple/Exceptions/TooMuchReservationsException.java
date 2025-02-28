package org.exemple.Exceptions;

public class TooMuchReservationsException extends Exception{
    public TooMuchReservationsException(String msg) {
        super(msg);
    }
}
