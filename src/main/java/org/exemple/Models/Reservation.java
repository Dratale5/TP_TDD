package org.exemple.Models;

public class Reservation {
    Adherant adherant;
    Book livreReserve;

    public Reservation(Adherant _adherant, Book _livreReserve) {
        adherant = _adherant;
        livreReserve = _livreReserve;
    }
}
