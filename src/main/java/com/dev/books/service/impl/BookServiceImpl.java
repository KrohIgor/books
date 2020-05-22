package com.dev.books.service.impl;

import com.dev.books.dao.BookDao;
import com.dev.books.lib.Inject;
import com.dev.books.lib.Service;
import com.dev.books.model.Author;
import com.dev.books.model.Book;
import com.dev.books.model.Genre;
import com.dev.books.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookDao.getBookByTitle(title);
    }

    @Override
    public List<Book> getListOfBooksByAuthor(Author author) {
        return bookDao.getListOfBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.getAllBooksByGenre(genre);
    }
}
