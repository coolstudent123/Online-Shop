package com.uep.wap.util;

import com.uep.wap.model.Book;
import java.util.List;

public class Calculator {
    public static Double calculateTotalCost(List<Book> bookList){
        double totalCost = 0;
        for(Book book : bookList){
            totalCost += book.getCost();
        }
        return totalCost;
    }
}
