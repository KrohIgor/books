package com.dev.books.service;

import com.dev.books.model.Author;
import com.dev.books.model.Book;
import com.dev.books.model.Genre;
import java.util.List;

public interface BookService {

    Book add(Book book);

    Book getBookByTitle(String title);

    List<Book> getListOfBooksByAuthor(Author author);

    List<Book> getAllBooksByGenre(Genre genre);
}
