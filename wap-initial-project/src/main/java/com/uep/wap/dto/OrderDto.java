package com.uep.wap.dto;

import com.uep.wap.model.Book;
import com.uep.wap.model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    private Integer id;
    private CustomerDto customer;
    private List<BookDto> books;
    private LocalDate creationDate;
    private LocalDate completionDate;
    private Double totalCost;
    private String status;

    public OrderDto() {
        // No-arg constructor
    }

    public OrderDto(Integer id, CustomerDto customer, List<BookDto> books, LocalDate creationDate, LocalDate completionDate, Double totalCost, String status) {
        this.id = id;
        this.customer = customer;
        this.books = books;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public void setCustomerFromEntity(Customer customer) {
        this.customer = new CustomerDto(customer.getName(), customer.getSurname(), customer.getPhoneNumber());
    }

    public void setBooksFromEntities(List<Book> books) {
        this.books = books.stream()
                .map(book -> new BookDto(book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getCost(), book.getReceiptDate(), book.getAvailability()))
                .collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
