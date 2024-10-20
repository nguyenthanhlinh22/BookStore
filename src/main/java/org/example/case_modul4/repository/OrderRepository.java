package org.example.case_modul4.repository;


import jakarta.persistence.criteria.Order;
import org.example.case_modul4.model.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    Order findTopByOrderByCreatedAtDesc();
}