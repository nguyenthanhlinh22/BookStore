package org.example.payviewss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/checkout")
    public String checkout(@ModelAttribute("cart") Cart cart, @RequestParam("stripeToken") String stripeToken, Model model) {
        try {
            paymentService.charge(stripeToken, cart.getTotalPrice());
            // Sau khi thanh toán thành công, xóa giỏ hàng
            cart.getBooks().clear();
            model.addAttribute("message", "Thanh toán thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Thanh toán thất bại!");
        }
        return "confirmation"; // Chỉ đến trang xác nhận thanh toán
    }
}
