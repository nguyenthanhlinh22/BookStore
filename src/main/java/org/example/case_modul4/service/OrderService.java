package org.example.case_modul4.service;

import org.example.case_modul4.model.BookOrder;
import org.example.case_modul4.repository.BookOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private BookOrderRepository bookOrderRepository;

    public BookOrder getOrderDetails(Integer orderId) {
        // Lấy thông tin đơn hàng theo ID
        Optional<BookOrder> order = bookOrderRepository.findById(orderId);
        return order.orElse(null); // Trả về null nếu không tìm thấy đơn hàng
    }

    public BookOrder createOrder(BookOrder bookOrder) {
        // Tạo mới một đơn hàng
        return bookOrderRepository.save(bookOrder);
    }

    // Các phương thức khác như cập nhật, xóa đơn hàng có thể được thêm vào đây
}