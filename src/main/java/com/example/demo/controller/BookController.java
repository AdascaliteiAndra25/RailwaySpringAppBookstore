package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String displayAllBooks(Model model) {
        model.addAttribute("title", "All Books");
        model.addAttribute("books", bookService.findAllBooks());
        return "books/index";
    }

    @GetMapping("create")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String displayCreateBookForm(Model model) {
        model.addAttribute("title", "Create Book");
        model.addAttribute("book", new BookDto(null, "", "", 0.0));
        return "books/create";
    }

    @PostMapping("create")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String processCreateBookForm(@ModelAttribute  BookDto bookDto,
                                     Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Books");
            return "books/create";
        }

        bookService.addBook(bookDto);
        return "redirect:/books";
    }

    @GetMapping("buy")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String displayBuyBookForm(Model model) {
        model.addAttribute("title", "Delete Books");
        model.addAttribute("books", bookService.findAllBooks());
        return "books/buy";
    }

    @PostMapping("buy")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String processBuyBooksForm(@RequestParam(required = false) Long[] bookIds) {
        if (bookIds != null) {
            for (Long id : bookIds) {
                bookService.deleteBook(id);
            }
        }
        return "redirect:/books";
    }



   /* @GetMapping("detail")
    public String displayBookDetails(@RequestParam Long bookId, Model model) {
        BookDto bookDto = bookService.findBookById(bookId);
        if (bookDto == null) {
            model.addAttribute("title", "Invalid Book ID: " + bookId);
        } else {
            model.addAttribute("title", "Book Details: " + bookDto.getTitle());
            model.addAttribute("book", bookDto);
        }
        return "books/detail";
    }*/



}
