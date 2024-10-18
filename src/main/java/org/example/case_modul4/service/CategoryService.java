package org.example.case_modul4.service;

import org.example.case_modul4.model.Book;
import org.example.case_modul4.model.Category;
import org.example.case_modul4.repository.BookRepository;
import org.example.case_modul4.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
}
