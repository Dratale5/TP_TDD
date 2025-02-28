package org.exemple.Models;

import java.time.LocalDate;

public class Reservation {
    Adherant adherant;
    Book bookReserved;
    LocalDate dateReservation;

    public Reservation(Adherant _adherant, Book _bookReserved) {
        adherant = _adherant;
        bookReserved = _bookReserved;
        dateReservation = LocalDate.now();
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public Book getBookReserved() {
        return bookReserved;
    }

    public void setDateReservation(LocalDate _dateReservation) {
        dateReservation=_dateReservation;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }
}
