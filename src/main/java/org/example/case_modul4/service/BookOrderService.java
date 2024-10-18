package org.example.case_modul4.service;

import org.example.case_modul4.model.BookOrder;
import org.example.case_modul4.repository.BookOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOrderService {

    @Autowired
    private BookOrderRepository bookOrderRepository;

    public List<BookOrder> getAllOrders() {
        return bookOrderRepository.findAll();
    }

    public BookOrder getOrderById(Integer id) {
        return bookOrderRepository.findById(id).orElse(null);
    }

}
