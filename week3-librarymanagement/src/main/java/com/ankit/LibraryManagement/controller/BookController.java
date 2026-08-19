package com.ankit.LibraryManagement.controller;

import com.ankit.LibraryManagement.dto.BookDTO;
import com.ankit.LibraryManagement.dto.BookRequestDTO;
import com.ankit.LibraryManagement.service.BookService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        bookService.createBook(bookRequestDTO);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/findByTitle/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findBooksByTitle(@PathVariable String title) {
        return bookService.findBooksByTitleSorted(title);
    }

    @GetMapping("/published-after-with-author/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findBooksPublishedAfterWithAuthor(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return bookService.findBooksPublishedAfterWithAuthor(date);
    }

    @GetMapping("/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.findBooksByAuthorId(authorId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findAll(@RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam(defaultValue = "0") Integer pageNo) {
        return bookService.findAll(sortBy, pageNo);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.updateBook(id, bookRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
