package org.example.case_modul4.repository;

import org.example.case_modul4.model.Book;
import org.example.case_modul4.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findTop10ByOrderByBuyTurnDesc();
    List<Book> findByCategory(Category category);
    List<Book> findByTitleContainingIgnoreCase(String title);


}
