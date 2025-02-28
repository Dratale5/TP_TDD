package org.exemple.Models;

public class Reservation {
    Adherant adherant;
    Book bookReserved;

    public Reservation(Adherant _adherant, Book _bookReserved) {
        adherant = _adherant;
        bookReserved = _bookReserved;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public Book getBookReserved() {
        return bookReserved;
    }
}
