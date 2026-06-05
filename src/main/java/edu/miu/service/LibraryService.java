package edu.miu.service;

import edu.miu.model.Book;
import edu.miu.repository.LibraryRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {
    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Book> getAllBooks() {
        return libraryRepository.findAllBooks();
    }

    public void addBook(Book book) {
        libraryRepository.addBook(book);
    }

    public List<Book> getRareBooks() {
        return libraryRepository.findAllBooks()
                .stream()
                .filter(Book::isRare)
                .collect(Collectors.toList());
    }

    public double calculateInventoryValue() {
        return libraryRepository.findAllBooks()
                .stream()
                .mapToDouble(Book::getInventoryValue)
                .sum();
    }

    public List<Book> getBooksSortedByPriceDescending() {
        return libraryRepository.findAllBooks()
                .stream()
                .sorted(Comparator.comparingDouble(Book::getPrice).reversed())
                .collect(Collectors.toList());
    }

}
