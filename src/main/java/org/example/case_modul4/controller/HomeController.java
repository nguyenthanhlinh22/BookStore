package org.example.case_modul4.controller;

import org.example.case_modul4.model.Book;
import org.example.case_modul4.model.Category;
import org.example.case_modul4.model.User;
import org.example.case_modul4.service.BookService;
import org.example.case_modul4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @ModelAttribute
    public void addCategoriesToModel(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
    }

    @GetMapping("/static")
    public String homePage(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "static";
    }

    @GetMapping("/categories/{categoryName}")
    public String getBooksByCategory(@PathVariable String categoryName, Model model) {
        Category selectedCategory = categoryService.getCategoryByName(categoryName);
        if (selectedCategory == null) {
            model.addAttribute("errorMessage", "Danh mục không tồn tại.");
            return "error";
        }
        List<Book> books = bookService.getBooksByCategory(selectedCategory);
        model.addAttribute("selectedCategory", selectedCategory);
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable int id, Model model) {
        Book book = bookService.getBookDetails(id);
        model.addAttribute("book", book);
        model.addAttribute("category", book.getCategory());
        List<Book> suggestedBooks = bookService.findSuggestedBooks(book.getCategory());
        model.addAttribute("suggestedBooks", suggestedBooks);
        return "bookDetail";
    }



}
