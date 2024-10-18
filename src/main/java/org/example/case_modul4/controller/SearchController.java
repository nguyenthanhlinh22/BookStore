package org.example.case_modul4.controller;

import org.example.case_modul4.model.Book;
import org.example.case_modul4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private BookService bookService;

    @GetMapping("/suggestions")
    public ResponseEntity<List<Book>> getSuggestions(@RequestParam String query) {
        List<Book> suggestions = bookService.findBooksByTitleContaining(query);
        return ResponseEntity.ok(suggestions);
    }
}
