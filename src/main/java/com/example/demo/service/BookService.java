package com.example.demo.service;

import com.example.demo.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findAllBooks();
    BookDto findBookById(Long id);
    BookDto addBook(BookDto bookDto);
    void updateBook(Long id, BookDto bookDto);
    void deleteBook(Long id);
}
