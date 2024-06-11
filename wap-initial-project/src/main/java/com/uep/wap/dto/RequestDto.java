package com.uep.wap.dto;

import com.uep.wap.model.Book;

import java.time.LocalDate;

public class RequestDto {
    private Integer id;
    private BookDto book;
    private LocalDate date;
    private Boolean status;

    public RequestDto() {
        // No-arg constructor
    }

    public RequestDto(Integer id, Book book, LocalDate date, Boolean status) {
        this.id = id;
        this.book = convertBookToBookDto(book);
        this.date = date;
        this.status = status;
    }

    private BookDto convertBookToBookDto(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getCost(),
                book.getReceiptDate(),
                book.getAvailability()
        );
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
