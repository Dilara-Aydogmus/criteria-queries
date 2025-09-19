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
    private EntityManager em;

    @Override
    public List<Book> findBooksByTitleContains(String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root).where(cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root).where(cb.equal(root.get("author"), author));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Book> findBooksWithLongTitles(int len) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root).where(cb.gt(cb.length(root.get("title")), len));
        return em.createQuery(cq).getResultList();
    }
}
