package com.uep.wap.model;
import com.uep.wap.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(BookDto book, int quantity) {
        for (CartItem item : items) {
            if (item.getBook().getId().equals(book.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(book, quantity));
    }

    public double getTotalSum() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() {
        items.clear();
    }

    public static class CartItem {
        private BookDto book;
        private int quantity;

        // Constructors, getters, and setters
        public CartItem(BookDto book, int quantity) {
            this.book = book;
            this.quantity = quantity;
        }

        public BookDto getBook() {
            return book;
        }

        public void setBook(BookDto book) {
            this.book = book;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return book.getCost() * quantity;
        }
    }
}