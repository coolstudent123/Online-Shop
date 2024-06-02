package com.uep.wap.model;

import com.uep.wap.interfaces.Identified;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "books")
public class Book implements Identified<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column (name = "title")
    private String title;
    @Column (name = "author")
    private String author;
    @Column (name = "publication_year")
    private Integer publicationYear;
    @Column (name = "cost")
    private Double cost;
    @Column (name = "receipt_date")
    private LocalDate receiptDate;
    @Column (name = "availability")
    private Boolean availability;
    @Column (name = "description")
    private String description;
    @OneToMany(mappedBy = "book")
    private Set<Request> request;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Request> getRequest() {
        return request;
    }

    public void setRequest(Set<Request> request) {
        this.request = request;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // No-argument constructor
    public Book() {
    }
    public Book(String title,
                String author,
                Integer publicationYear,
                Double cost,
                LocalDate receiptDate,
                Boolean availability) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.cost = cost;
        this.receiptDate = receiptDate;
        this.availability = availability;
        this.description = null;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId())
                && Objects.equals(getTitle(), book.getTitle())
                && Objects.equals(getAuthor(), book.getAuthor())
                && Objects.equals(getPublicationYear(), book.getPublicationYear())
                && Objects.equals(getCost(), book.getCost())
                && Objects.equals(getReceiptDate(), book.getReceiptDate())
                && Objects.equals(getAvailability(), book.getAvailability());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthor(), getPublicationYear(),
                getCost(), getReceiptDate(), getAvailability());
    }

    @Override
    public String toString() {
        return "Book [id " + getId() + " Name " +
                getTitle() + " Author " + getTitle() + " Year " +
                getPublicationYear() +" price " + getCost() + " Receipt date " +
                getReceiptDate() +" Accessibility  " + getAvailability() + "]";
    }
}
