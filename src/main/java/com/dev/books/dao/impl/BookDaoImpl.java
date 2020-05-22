package com.dev.books.dao.impl;

import com.dev.books.dao.BookDao;
import com.dev.books.lib.Dao;
import com.dev.books.model.Author;
import com.dev.books.model.Book;
import com.dev.books.model.Genre;
import com.dev.books.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BookDaoImpl implements BookDao {

    @Override
    public Book add(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long bookId = (Long) session.save(book);
            transaction.commit();
            book.setId(bookId);
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert book entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder
                    .createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate predicate = criteriaBuilder.equal(root.get("title"), title);
            criteriaQuery.where(predicate);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving book", e);
        }
    }

    @Override
    public List<Book> getListOfBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder
                    .createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate predicate = criteriaBuilder.isMember(author, root.get("authorSet"));
            criteriaQuery.where(predicate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving books", e);
        }
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder
                    .createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate predicate = criteriaBuilder.equal(root.get("genre"), genre);
            criteriaQuery.where(predicate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving books", e);
        }
    }
}
