package org.exemple.Services;

import org.exemple.Models.Book;

public interface DBService {
    public Book getBookByIsbn(String isbn); 
    public Book getBookByTitle(String title); 
}
