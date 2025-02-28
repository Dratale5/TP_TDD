package org.exemple.Services;

import java.util.ArrayList;

import org.exemple.Models.Adherant;
import org.exemple.Models.Book;
import org.exemple.Models.Reservation;

public class ReservationService {
    ArrayList<Reservation> listeReservations;

    public ReservationService() {
        listeReservations = new ArrayList<Reservation>();
    }

    

    public int creerReservation(Adherant _adherant, Book _book) {
        _book.setIsAvailable(false);
        listeReservations.add(new Reservation(_adherant, _book));
        return listeReservations.size();
    }
}
