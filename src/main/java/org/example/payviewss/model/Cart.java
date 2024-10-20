package org.example.payviewss.model;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public double getTotalPrice() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }
}
