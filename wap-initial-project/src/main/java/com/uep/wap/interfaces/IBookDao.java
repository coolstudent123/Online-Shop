package com.uep.wap.interfaces;

import com.uep.wap.model.Book;
import com.uep.wap.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IBookDao extends GenericDao<Book, Integer> {

    List<Book> getSortBook(String criterion);
    List<Book> getUnsoldBook(LocalDate date, String criterion);
    void insertOrder(Book book, Order order);
}
