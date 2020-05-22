package com.dev.books.dao;

import com.dev.books.model.Author;
import com.dev.books.model.Book;
import com.dev.books.model.Genre;
import java.util.List;

public interface BookDao {

    Book add(Book book);

    Book getBookByTitle(String title);

    List<Book> getListOfBooksByAuthor(Author author);

    List<Book> getAllBooksByGenre(Genre genre);
}
