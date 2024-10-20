package org.example.case_modul4.controller;

import org.example.case_modul4.model.Book;
import org.example.case_modul4.model.Cart;
import org.example.case_modul4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private BookService bookService;

    @ModelAttribute("cart")
    public Cart getCart() {
        return new Cart();
    }

    @GetMapping("/cart/add/{bookId}")
    public String addToCart(@PathVariable int bookId, @ModelAttribute("cart") Cart cart, Model model) {
        Book book = bookService.getBookDetails(bookId);
        cart.addBook(book);
        model.addAttribute("cart", cart);
        return "cart"; // Chỉ đến giao diện giỏ hàng (cart.html)
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        return "cart"; // Chỉ đến giao diện giỏ hàng
    }
}