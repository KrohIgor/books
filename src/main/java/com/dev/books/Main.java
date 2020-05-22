package com.dev.books;

import com.dev.books.lib.Injector;
import com.dev.books.model.Author;
import com.dev.books.model.Book;
import com.dev.books.model.Genre;
import com.dev.books.service.AuthorService;
import com.dev.books.service.BookService;
import com.dev.books.service.GenreService;
import java.util.Set;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.books");

    public static void main(String[] args) {
        Genre fantasy = new Genre();
        fantasy.setName("Fantasy");
        Genre thriller = new Genre();
        thriller.setName("Thriller");
        Genre drama = new Genre();
        drama.setName("Drama");
        GenreService genreService = (GenreService) INJECTOR.getInstance(GenreService.class);
        genreService.add(fantasy);
        genreService.add(thriller);
        genreService.add(drama);

        Author shevchenko = new Author();
        shevchenko.setName("Shevchenko");
        Author franko = new Author();
        franko.setName("Franko");
        Author king = new Author();
        king.setName("King");
        AuthorService authorService = (AuthorService) INJECTOR.getInstance(AuthorService.class);
        authorService.add(shevchenko);
        authorService.add(franko);
        authorService.add(king);

        Book kobzar = new Book();
        kobzar.setTitle("Kobzar");
        kobzar.setAuthorSet(Set.of(shevchenko));
        kobzar.setGenre(drama);

        Book killer = new Book();
        killer.setTitle("Killer");
        killer.setAuthorSet(Set.of(shevchenko, franko));
        killer.setGenre(thriller);

        Book ring = new Book();
        ring.setTitle("Lord of the Rings");
        ring.setAuthorSet(Set.of(franko, king));
        ring.setGenre(fantasy);

        BookService bookService = (BookService) INJECTOR.getInstance(BookService.class);
        bookService.add(kobzar);
        bookService.add(killer);
        bookService.add(ring);

        System.out.println(bookService.getBookByTitle("Killer"));

        bookService.getAllBooksByGenre(fantasy).forEach(System.out::println);

        bookService.getListOfBooksByAuthor(shevchenko).forEach(System.out::println);
    }
}
