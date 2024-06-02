package com.uep.wap.model;
import com.uep.wap.interfaces.Identified;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "requests")
public class Request implements Identified<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    @Column (name = "date")
    private LocalDate date;
    @Column (name = "status")
    private Boolean status;

    public Book getBook() {
        return book;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Request(Book book, LocalDate date) {
        this.book = book;
        this.date = date;
        this.status = true;
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
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Objects.equals(getId(), request.getId())
                && Objects.equals(getBook(), request.getBook())
                && Objects.equals(getDate(), request.getDate())
                && Objects.equals(getStatus(), request.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBook(), getDate(), getStatus());
    }

    @Override
    public String toString(){
        return "Request #" + getId() + " [Book " +
                book.getTitle() + " Author " + book.getAuthor() +
                " Price " + book.getCost() +
                " Date " + date +  " Status " + getStatus() + "]";
    }
}
