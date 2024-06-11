package com.uep.wap.model;

import com.uep.wap.dto.BookDto;
import com.uep.wap.interfaces.Identified;
import com.uep.wap.util.Calculator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Identified<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "order")//, cascade = CascadeType.ALL)
    private List<Book> bookList;
    @Column (name = "creation_date")
    private LocalDate creationDate;
    @Column (name = "completion_date")
    private LocalDate completionDate;
    @Column (name = "total_cost")
    private Double totalCost;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status
    {
        NEW(1),
        COMPLETED(2),
        CANCELED(3);

        private final int severity;

        Status(int severity) {
            this.severity = severity;
        }

        public int getSeverity() {
            return severity;
        }

    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order() {

    }

    public Order(Customer customer, List<Book> bookList, LocalDate creationDate) {
        this.customer = customer;
        this.bookList = bookList;
        this.creationDate = creationDate;
        this.completionDate = LocalDate.of(1970, 1, 1);
        this.totalCost = Calculator.calculateTotalCost(bookList);
        this.status = Status.NEW;
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
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId())
                && Objects.equals(getCustomer(), order.getCustomer())
                && Objects.equals(getBookList(), order.getBookList())
                && Objects.equals(getCreationDate(), order.getCreationDate())
                && Objects.equals(getCompletionDate(), order.getCompletionDate())
                && Objects.equals(getTotalCost(), order.getTotalCost())
                && getStatus() == order.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getBookList(), getCreationDate(),
                getCompletionDate(), getTotalCost(), getStatus());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Order #" + getId() + " \nCustomer \n[" + getCustomer().getName()
                + " " + getCustomer().getSurname() + ", telephone number " + getCustomer().getPhoneNumber()
                + "]\nOrdered books ");
        for (Book book : bookList) {
            str.append("\n[").append(book.getTitle()).append(" ").append(book.getAuthor())
                    .append(" price ").append(book.getCost()).append("]");
        }
        str.append("\nTotal price ").append(totalCost).append(" status = ").append(status);
        return str.toString();
    }
}
