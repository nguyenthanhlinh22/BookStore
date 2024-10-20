package org.example.case_modul4.controller;


import org.example.case_modul4.model.BookOrder;
import org.example.case_modul4.service.OrderService;
import org.example.case_modul4.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public String showPaymentPage(Model model, @RequestParam Integer orderId) {
        // Lấy thông tin đơn hàng theo ID
        BookOrder order = orderService.getOrderDetails(orderId);
        model.addAttribute("order", order);
        return "payment"; // Trả về view payment
    }

    @PostMapping("/payment/charge")
    public String charge(@RequestParam("token") String token, @RequestParam Integer orderId, Model model) {
        try {
            // Lấy thông tin đơn hàng
            BookOrder order = orderService.getOrderDetails(orderId);
            int amount = order.getTotal(); // Sử dụng getTotal() để lấy tổng số tiền

            // Thực hiện thanh toán
            paymentService.charge(token, amount);

            model.addAttribute("successMessage", "Payment successful!");
            return "paymentSuccess"; // Trang thành công
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Payment failed: " + e.getMessage());
            return "payment"; // Quay lại trang thanh toán
        }
    }
}