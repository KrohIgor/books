package com.dev.books.service.impl;

import com.dev.books.dao.AuthorDao;
import com.dev.books.lib.Inject;
import com.dev.books.lib.Service;
import com.dev.books.model.Author;
import com.dev.books.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }
}
