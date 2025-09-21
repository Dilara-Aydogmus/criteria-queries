package com.example.repository;

import com.example.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksByTitleContains(String keyword) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root).where(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("author"), author));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Book> findBooksWithLongTitles(int len) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root).where(criteriaBuilder.gt(criteriaBuilder.length(root.get("title")), len));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Book> findBooksWithLong(String author, String title) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> createQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = createQuery.from(Book.class);
        if (author != null) {
            createQuery.select(book).where(criteriaBuilder.equal(book.get("author"), author));
        }
        if (title != null) {
            createQuery.select(book).where(criteriaBuilder.equal(book.get("title"), title));
        }

        return entityManager.createQuery(createQuery).getResultList();
    }

}
