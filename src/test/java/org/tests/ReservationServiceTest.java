package org.tests;

import java.time.LocalDate;

import org.exemple.Enums.BookType;
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
    void testCreerReservationAvecLivreDisponible() {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book book = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);
        reservationService.creerReservation(adherant, book);
        assertFalse(book.getIsAvailable()); 
    }

    @Test
    void testCreerReservationAvecLivreIndisponible() throws BookNotAvailableException {
        Adherant adherant = new Adherant(1, "Vigner", "Anthony", LocalDate.of(1990, 4, 1), "Mr");
        Book book = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);
        book.setIsAvailable(false);
        assertThrows(BookNotAvailableException.class, () -> reservationService.creerReservation(adherant, book));
    }
}
