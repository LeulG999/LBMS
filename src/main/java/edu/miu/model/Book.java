package edu.miu.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private static final int RARE_COPY_LIMIT = 1;

    private String isbn;
    private String title;
    private String author;
    private double price;
    private List<BookCopy> copies;

    public Book() {
        this.copies = new ArrayList<>();
    }

    public Book(String isbn, String title, String author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.copies = new ArrayList<>();
    }

    public Book(String isbn, String title, String author, double price, List<BookCopy> copies) {
        this(isbn, title, author, price);
        setCopies(copies);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return getTotalCopies() <= RARE_COPY_LIMIT ? BookCategory.RARE : BookCategory.REGULAR;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = new ArrayList<>();
        if (copies != null) {
            for (BookCopy copy : copies) {
                addCopy(copy);
            }
        }
    }

    public void addCopy(BookCopy copy) {
        if (copy != null) {
            copy.setBook(this);
            copies.add(copy);
        }
    }

    public boolean removeCopy(BookCopy copy) {
        if (copy == null) {
            return false;
        }
        boolean removed = copies.remove(copy);
        if (removed) {
            copy.setBook(null);
        }
        return removed;
    }

    public int getTotalCopies() {
        return copies.size();
    }

    public long getAvailableCopies() {
        return copies.stream()
                .filter(BookCopy::isAvailable)
                .count();
    }

    public boolean isRare() {
        return BookCategory.RARE.equals(getCategory());
    }

    public double getInventoryValue() {
        return price * copies.size();
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category=" + getCategory() +
                ", price=" + price +
                ", copies=" + copies.size() +
                '}';
    }
}
