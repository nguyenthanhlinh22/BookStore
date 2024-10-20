package org.example.payviewss.repository;

import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    Order findTopByOrderByCreatedAtDesc();
}