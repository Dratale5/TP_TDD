package org.exemple.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exemple.Exceptions.BookNotAvailableException;
import org.exemple.Exceptions.TooMuchReservationsException;
import org.exemple.Models.Adherant;
import org.exemple.Models.Book;
import org.exemple.Models.Reservation;

public class ReservationService {
    ArrayList<Reservation> historiqueListeReservations;
    ArrayList<Reservation> listeReservations;
    MailService mailservice;

    public ReservationService(MailService _mailservice) {
        historiqueListeReservations = new ArrayList<Reservation>();
        listeReservations = new ArrayList<Reservation>();
        mailservice = _mailservice;
    }

    public boolean  creerReservation(Adherant _adherant, Book _book) throws Exception {
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
            historiqueListeReservations.add(listeReservations.get(listeReservations.size()-1));
            return true;
        }
        else  { throw new BookNotAvailableException(""); }
    }

    public boolean annulerReservation(Adherant _adherant, Book _book) {
        for (int i = 0; i < listeReservations.size(); i++) {
            Reservation reservation = listeReservations.get(i);
            if (reservation.getAdherant().equals(_adherant) && reservation.getBookReserved().equals(_book)) {
                listeReservations.remove(i); 
                _book.setIsAvailable(true); 
                return true;
            }
        }
        return false;
    }

     public void checkReservationsExpired() {
        Map<Adherant, List<Reservation>> ReservationsOfAdherantsMap = new HashMap<>();
        List<Reservation> expiredReservations = new ArrayList<>();

        for (int i = 0; i < listeReservations.size(); i++) {
            if (ChronoUnit.MONTHS.between(listeReservations.get(i).getDateReservation(), LocalDate.now()) > 4) {
                expiredReservations.add(listeReservations.get(i));

                ReservationsOfAdherantsMap
                    .computeIfAbsent(listeReservations.get(i).getAdherant(), k -> new ArrayList<>())
                    .add(listeReservations.get(i));
            }
        }

        for (Map.Entry<Adherant, List<Reservation>> entry : ReservationsOfAdherantsMap.entrySet()) {
            mailservice.sendMail(entry.getKey(), entry.getValue());
        }

        listeReservations.removeAll(expiredReservations);
        for (Reservation res : expiredReservations) {
            res.getBookReserved().setIsAvailable(true);
        }
     }
    public ArrayList<Reservation> getActualReservations() {
        return listeReservations;
    }

    public ArrayList<Reservation> getHistoricReservation(Adherant _adherant) {
        ArrayList<Reservation> ListeRetour = new ArrayList<Reservation>();
        for (int i = 0; i < historiqueListeReservations.size(); i++) {
            Reservation reservation = historiqueListeReservations.get(i);
            if (reservation.getAdherant().equals(_adherant)) {
                ListeRetour.add(historiqueListeReservations.get(i));
            }
        }
        return ListeRetour;
    }
}
