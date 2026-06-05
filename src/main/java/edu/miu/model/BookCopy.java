package edu.miu.model;

import java.time.LocalDate;

public class BookCopy {
    private String copyNumber;
    private boolean available;
    private LocalDate acquiredDate;
    private Book book;

    public BookCopy() {
        this.available = true;
    }

    public BookCopy(String copyNumber, boolean available, LocalDate acquiredDate) {
        this.copyNumber = copyNumber;
        this.available = available;
        this.acquiredDate = acquiredDate;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getAcquiredDate() {
        return acquiredDate;
    }

    public void setAcquiredDate(LocalDate acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "copyNumber='" + copyNumber + '\'' +
                ", available=" + available +
                ", acquiredDate=" + acquiredDate +
                '}';
    }

}
