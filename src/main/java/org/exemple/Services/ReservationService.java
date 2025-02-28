package org.exemple.Services;

import java.util.ArrayList;

import org.exemple.Exceptions.BookNotAvailableException;
import org.exemple.Exceptions.TooMuchReservationsException;
import org.exemple.Models.Adherant;
import org.exemple.Models.Book;
import org.exemple.Models.Reservation;

public class ReservationService {
    ArrayList<Reservation> listeReservations;

    public ReservationService() {
        listeReservations = new ArrayList<Reservation>();
    }

    public int creerReservation(Adherant _adherant, Book _book) throws Exception {
        int counter=0;
        for (int i=0;i<listeReservations.size();i++) {
            if (_adherant.equals(listeReservations.get(i).getAdherant())) {
                counter+=1;
            }
        }
        if(counter>=3) {
            throw new TooMuchReservationsException("");
        }
        if(_book.getIsAvailable()==true) {
            _book.setIsAvailable(false);
            listeReservations.add(new Reservation(_adherant, _book));
            return listeReservations.size();
        }
        else  { throw new BookNotAvailableException(""); }
    }
}
