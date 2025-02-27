package org.tests;

import org.exemple.Enums.BookType;
import org.exemple.Interfaces.DBService;
import org.exemple.Interfaces.WebService;
import org.exemple.Models.Book;
import org.exemple.Services.LibraryService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class LibraryServiceTest {
    private DBService dbService;
    private WebService webService;
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        dbService = Mockito.mock(DBService.class);
        webService = Mockito.mock(WebService.class);
        libraryService = new LibraryService(dbService, webService);
    }

    @Test
    void givenBookIsInLocalDB_whenFindByIsbn_thenReturnBookFromLocalDB() {
        Book expectedBook = new Book("9782266332439", "Âmes animales", "José Rodrigues Dos Santos", "Pocket", BookType.BD);
        when(dbService.getBookByIsbn("9782266332439")).thenReturn(expectedBook);
        when(webService.getBookByIsbn("9782266332439")).thenReturn(expectedBook);

        Book book = libraryService.findBookByIsbn("9782266332439");

        verifyNoInteractions(webService);  
        assertEquals(expectedBook, book);
    }
}