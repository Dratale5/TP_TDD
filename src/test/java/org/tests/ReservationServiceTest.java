package org.tests;

import java.time.LocalDate;
import java.util.ArrayList;

import org.exemple.Enums.BookType;
import org.exemple.Exceptions.BookNotAvailableException;
import org.exemple.Exceptions.TooMuchReservationsException;
import org.exemple.Models.Adherant;
import org.exemple.Models.Book;
import org.exemple.Models.Reservation;
import org.exemple.Services.MailService;
import org.exemple.Services.ReservationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {
    private ReservationService reservationService;
    private MailService mailService;

    @BeforeEach
    void setUp() {
        mailService = Mockito.mock(MailService.class);
        reservationService = new ReservationService(mailService);
    }

    @Test
    void givenAvailableBook_WhenCreatingReservation_ShouldReturnTrue() throws Exception {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book book = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);
        reservationService.creerReservation(adherant, book);
        assertFalse(book.getIsAvailable()); 
    }

    @Test
    void givenUnavailableBook_WhenCreatingReservation_ShouldReturnException() throws BookNotAvailableException {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book book = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);
        book.setIsAvailable(false);
        assertThrows(BookNotAvailableException.class, () -> reservationService.creerReservation(adherant, book));
    }

    @Test
    void givenAdherant_WhenCreatingFourthReservation_ShouldReturnException() throws Exception {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book Firstbook = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);
        Book Secondbook = new Book("9782958608613", "Manuscripto: Les lettres masquées de Venise", "Bruno Scudeller", "Inconnue", BookType.Roman);
        Book Thirdbook = new Book("9782290233795", "Le tatoueur d'Auschwitz", "Morris Heather", "J'ai lu", BookType.Roman);
        Book Fourthbook = new Book("9782824638874", "La psy", "Freida McFadden", "City Edition", BookType.BD);

        reservationService.creerReservation(adherant, Firstbook);
        reservationService.creerReservation(adherant, Secondbook);
        reservationService.creerReservation(adherant, Thirdbook);

        assertThrows(TooMuchReservationsException.class, () -> reservationService.creerReservation(adherant, Fourthbook));
    }

    @Test
    void givenAdherantAndBook_WhenCancelingReservation_ShouldReturnTrue() throws Exception {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book book = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);

        reservationService.creerReservation(adherant, book);

        assertTrue(reservationService.annulerReservation(adherant, book));
    }

    @Test
    void givenReservation_whenExpired_thenSendMailToAdherant() throws Exception {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book book = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);

        reservationService.creerReservation(adherant, book);

        ArrayList<Reservation> listeReservations = reservationService.getActualReservations();

        listeReservations.get(0).setDateReservation(LocalDate.of(2020, 4, 1));

        reservationService.checkReservationsExpired();
        
        verify(mailService, times(1)).sendMail(any(Adherant.class), anyList());
    }

    @Test
    void givenReservation_whenGettingHistoricListReservationThatIsExpired_ShouldReturnList() throws Exception {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book book = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);

        reservationService.creerReservation(adherant, book);

        reservationService.annulerReservation(adherant, book);

        ArrayList<Reservation> historicList = reservationService.getHistoricReservation();

        assertEquals(historicList.get(0).getAdherant(), adherant);
        assertEquals(historicList.get(0).getBookReserved(), book);
    }
}
