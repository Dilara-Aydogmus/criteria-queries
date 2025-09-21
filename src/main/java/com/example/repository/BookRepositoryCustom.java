package com.example.repository;

import com.example.model.Book;
import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findBooksByTitleContains(String keyword);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksWithLongTitles(int len);

    List<Book> findBooksWithLong(String author, String title);
}
