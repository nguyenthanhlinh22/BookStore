package org.example.case_modul4.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.case_modul4.model.Book;
import org.example.case_modul4.model.Category;
import org.example.case_modul4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }
    public Book getBookDetails(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sách không tồn tại"));

        if (book.getCategory() != null) {
            String categoryName = book.getCategory().getCategoryName();

        }

        return book;
    }


    public List<Book> findSuggestedBooks(Category category) {

        return bookRepository.findByCategory(category);
    }
    public List<String> findGenresByBookId(int categoryId) {
        String sql = "SELECT category_name FROM categories WHERE id = ?";
        List<String> genres = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, categoryId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                genres.add(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }
    public List<Book> findBooksByTitleContaining(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }



}
