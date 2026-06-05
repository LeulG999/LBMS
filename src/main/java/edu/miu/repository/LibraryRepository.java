package edu.miu.repository;

import edu.miu.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {
    private List<Book> books;

    public LibraryRepository() {
        this.books = new ArrayList<>();
    }

    public List<Book> findAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
