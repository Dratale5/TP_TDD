package org.exemple.Services;

import java.util.List;

import org.exemple.Models.Adherant;
import org.exemple.Models.Reservation;

public interface MailService {
    void sendMail(Adherant _adherant, List<Reservation> reservationsConcernees);
}
