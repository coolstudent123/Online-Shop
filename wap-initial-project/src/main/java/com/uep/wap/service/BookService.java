package com.uep.wap.service;

import com.uep.wap.dto.BookDto;
import com.uep.wap.mappers.BookMapper;
import com.uep.wap.repository.BookRepository;
import com.uep.wap.model.Book;
import com.uep.wap.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        return bookMapper.bookToBookDto(book);
    }

    @Override
    public void createBook(BookDto bookDto) {
        try {
            Book book = bookMapper.bookDtoToBook(bookDto);
            if (bookDto.getImage() != null && !bookDto.getImage().isEmpty()) {
                book.setImage(bookDto.getImage().getBytes());
            }
            bookRepository.save(book);
        } catch (IOException e) {
            // Handle or log the IOException
            e.printStackTrace(); // Example of handling
        }
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        bookRepository.delete(book);
    }

    @Override
    public void updateBook(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookDto.getId()));
        // Update book properties based on bookDto
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setCost(bookDto.getCost());
        book.setReceiptDate(bookDto.getReceiptDate());
        book.setAvailability(bookDto.getAvailability());
        book.setDescription(bookDto.getDescription());
        // Save the updated book
        bookRepository.save(book);
    }

    @Override
    public void cancelBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        book.setAvailability(false);
        bookRepository.save(book);
    }

    @Override
    public void addBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        book.setAvailability(true);
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> unsoldBook(String criterion) {
        LocalDate date = LocalDate.now().minusMonths(Integer.parseInt(criterion));
        List<Book> unsoldBooks = bookRepository.findUnsoldBooks(date);
        return unsoldBooks.stream().map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getSortBooks(String criterion) {
        List<Book> sortedBooks = null;
        // Implement sorting logic based on the criterion
        switch (criterion) {
            case "title":
                sortedBooks = bookRepository.findAllByOrderByTitle();
                break;
            case "author":
                sortedBooks = bookRepository.findAllByOrderByAuthor();
                break;
            // Add more cases for other sorting criteria if needed
            default:
                // Default sorting criterion
                sortedBooks = bookRepository.findAll();
                break;
        }
        return sortedBooks.stream().map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    @Override
    public void addOrDeleteBook(Integer id, Boolean availability) {
        if (availability) {
            addBook(id);
        } else {
            cancelBook(id);
        }
    }



}
