package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    @Override
    public List<BookDto> findAllBooks() {
        return bookMapper.bookListEntityToDto(bookRepository.findAll());
    }

    @Override
    public BookDto findBookById(Long id) {
        return bookMapper.bookEntityToDto(bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found")));
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToEntity(bookDto);
        return bookMapper.bookEntityToDto(bookRepository.save(book));

    }

    @Override
    public void updateBook(Long id, BookDto bookDto) {

    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);

    }
}
