package com.example.demo.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    public BookDto bookEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
    }

    public Book bookDtoToEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.id())
                .title(bookDto.title())
                .author(bookDto.author())
                .price(bookDto.price())
                .build();
    }

    public List<BookDto> bookListEntityToDto(List<Book> books) {
        return books.stream()
                .map(this::bookEntityToDto)
                .toList();
    }

    public List<Book> bookListDtoToEntity(List<BookDto> bookDtos) {
        return bookDtos.stream()
                .map(this::bookDtoToEntity)
                .toList();
    }


}
