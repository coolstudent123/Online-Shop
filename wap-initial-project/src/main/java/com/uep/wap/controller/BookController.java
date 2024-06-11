package com.uep.wap.controller;
import com.uep.wap.dto.BookDto;
import com.uep.wap.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        return "/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.createBook(bookDto);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Integer id, Model model) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Integer id, @ModelAttribute("book") BookDto bookDto) {
        bookDto.setId(id); // Set the ID from path variable
        bookService.updateBook(bookDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }

}
