package com.uep.wap.controller;
import com.uep.wap.dto.BookDto;
import com.uep.wap.model.Cart;
import com.uep.wap.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/";
    private Cart cart = new Cart();
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/adminPanel")
    public String showAdminPanel(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "adminPanel"; // Make sure you have an adminPanel.html template
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        return "/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDto bookDto, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        try {
            // Save image file
            String fileName = saveImage(imageFile);
            // Save book data
            bookService.createBook(bookDto);

            return "redirect:/";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();
        String originalFilename = imageFile.getOriginalFilename();
        Path path = Paths.get(UPLOADED_FOLDER + originalFilename);
        Files.write(path, bytes);
        return originalFilename;
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
        return "redirect:/adminPanel";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("bookId") Integer bookId, @RequestParam("quantity") int quantity) {
        BookDto book = bookService.getBookById(bookId);
        cart.addItem(book, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCartPage(Model model) {
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("totalSum", cart.getTotalSum());
        return "cart";
    }

}
