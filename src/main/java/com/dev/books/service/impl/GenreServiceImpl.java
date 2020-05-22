package com.dev.books.service.impl;

import com.dev.books.dao.GenreDao;
import com.dev.books.lib.Inject;
import com.dev.books.lib.Service;
import com.dev.books.model.Genre;
import com.dev.books.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }
}
