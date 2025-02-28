package org.tests;

import java.time.LocalDate;

import org.exemple.Enums.BookType;
import org.exemple.Exceptions.BookNotAvailableException;
import org.exemple.Models.Adherant;
import org.exemple.Models.Book;
import org.exemple.Services.ReservationService;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservationServiceTest {
    private ReservationService reservationService;
    @BeforeEach
    void setUp() {
        reservationService = new ReservationService();
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
    void givenAdherant_WhenCreatingFourthReservation_ShouldReturnException() throws TooMuchReservationsException {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book Firstbook = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);
        Book Secondbook = new Book("9782958608613", "Manuscripto: Les lettres masquées de Venise", "Bruno Scudeller", "Inconnue", BookType.Roman);
        Book Thirdbook = new Book("9782290233795", "Le tatoueur d'Auschwitz", "Morris Heather", "J'ai lu", BookType.Roman);
        Book Fourthbook = new Book("9782824638874", "La psy", "Freida McFadden", "City Edition", BookType.BD);

        reservationService.creerReservation(adherant, Firstbook);
        reservationService.creerReservation(adherant, Secondbook);
        reservationService.creerReservation(adherant, Thirdbook);

        assertThrows(TooMuchReservationsException.class, () -> creerReservation(adherant, FourthBook));
    }
}
