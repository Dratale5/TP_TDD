package org.exemple.Interfaces;

import org.exemple.Models.Book;

public interface DBService {
    public Book getBookByIsbn(String isbn); 
}
