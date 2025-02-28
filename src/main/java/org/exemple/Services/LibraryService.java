package org.exemple.Services;

import org.exemple.Models.Book;

public class LibraryService {
    private final DBService dbservice;
    private final WebService webService;

    public LibraryService(DBService _dbservice, WebService _webService) {
        dbservice = _dbservice;
        webService = _webService;
    }

    public Book findBookByIsbn(String isbn) {
        Book book = dbservice.getBookByIsbn(isbn);
        if (book == null) {
            book = webService.getBookByIsbn(isbn);
        }
        return book;
    }

    public Book findBookByTitle(String title) {
        Book book = dbservice.getBookByTitle(title);
        return book;
    }

}
