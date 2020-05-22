package com.dev.books.dao.impl;

import com.dev.books.dao.AuthorDao;
import com.dev.books.lib.Dao;
import com.dev.books.model.Author;
import com.dev.books.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author add(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long authorId = (Long) session.save(author);
            transaction.commit();
            author.setId(authorId);
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert author entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
