package org.exemple.Models;

import org.exemple.Enums.BookType;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final String editor;
    private boolean isAvailable;
    private BookType type;

    public Book(String _isbn, String _title, String _author, String _editor, BookType _type) {
        isbn = _isbn;
        title = _title;
        author = _author;
        isAvailable = true;
        editor = _editor;
        type=_type;
    }

}
