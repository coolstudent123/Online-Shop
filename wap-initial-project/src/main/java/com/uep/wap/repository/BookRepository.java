package com.uep.wap.repository;

import com.uep.wap.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("SELECT b FROM Book b WHERE b.receiptDate <= ?1 AND b.availability = false")
    List<Book> findUnsoldBooks(LocalDate date);

    List<Book> findAllByOrderByTitle();

    List<Book> findAllByOrderByAuthor();

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String titleKeyword, String authorKeyword);
}
