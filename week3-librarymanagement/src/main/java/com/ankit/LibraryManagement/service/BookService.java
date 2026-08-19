package com.ankit.LibraryManagement.service;

import com.ankit.LibraryManagement.dto.BookDTO;
import com.ankit.LibraryManagement.dto.BookRequestDTO;
import com.ankit.LibraryManagement.entity.Author;
import com.ankit.LibraryManagement.entity.Book;
import com.ankit.LibraryManagement.exception.ResourceNotFoundException;
import com.ankit.LibraryManagement.repository.AuthorRepository;
import com.ankit.LibraryManagement.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    private final int PAGE_SIZE = 5;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public void createBook(BookRequestDTO bookRequestDTO) {
        Author author = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id : " + bookRequestDTO.getAuthorId()));

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Transactional
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found with id: " + id));
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        bookDTO.setAuthorId(book.getAuthor().getId());
        return bookDTO;
    }

    @Transactional
    public List<BookDTO> findAll(String sortBy, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(sortBy));
        List<Book> books = bookRepository.findAll(pageable).getContent();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for(Book b: books) {
            BookDTO bookDTO = modelMapper.map(b, BookDTO.class);
            bookDTO.setAuthorId(b.getAuthor().getId());
            bookDTOs.add(bookDTO);
        }
        return bookDTOs;
    }

    @Transactional
    public BookDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setTitle(bookRequestDTO.getTitle());
        book.setIsbn(bookRequestDTO.getIsbn());

        // only reassign author if it actually changed — avoids an unnecessary lookup/update
        if (!book.getAuthor().getId().equals(bookRequestDTO.getAuthorId())) {
            Author newAuthor = authorRepository.findById(bookRequestDTO.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookRequestDTO.getAuthorId()));
            book.setAuthor(newAuthor);
        }

        Book updated = bookRepository.save(book);

        BookDTO dto = modelMapper.map(updated, BookDTO.class);
        dto.setAuthorId(updated.getAuthor().getId());
        return dto;
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not Found with id: " + id));
        bookRepository.deleteById(id);
    }

    @Transactional
    public List<BookDTO> findBooksByTitleSorted(String title) {
        List<Book> books = bookRepository.findBooksByTitleSorted(title);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found with title containing: '" + title + "'");
        }
        List<BookDTO> listBooks = new ArrayList<>();
        for(Book book : books) {
            BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
            if (book.getAuthor() != null) {
                bookDTO.setAuthorId(book.getAuthor().getId());
            }
            listBooks.add(bookDTO);
        }
        return listBooks;
    }

    @Transactional
    public List<BookDTO> findBooksPublishedAfterWithAuthor(LocalDate date) {
        List<Book> books = bookRepository.findBooksPublishedAfterWithAuthor(date);
        List<BookDTO> listBooksDTO = new ArrayList<>();
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found published after: " + date);
        }

        for(Book book: books) {
            BookDTO bookDTO = modelMapper.map(book, BookDTO.class);

            // Set author ID from the eagerly loaded author
            if (book.getAuthor() != null) {
                bookDTO.setAuthorId(book.getAuthor().getId());
            }
            listBooksDTO.add(bookDTO);
        }
        return listBooksDTO;
    }

    @Transactional
    public List<BookDTO> findBooksByAuthorId(Long authorId) {
        // Verify author exists first
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author not found with ID: " + authorId);
        }

        // Single optimized query with JOIN FETCH
        List<Book> books = bookRepository.findBooksByAuthorId(authorId);
        List<BookDTO> listBooksDTO = new ArrayList<>();
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found for author with ID: " + authorId);
        }
        for(Book book: books) {
            BookDTO bookDTO = modelMapper.map(book, BookDTO.class);

            // Set author ID from the eagerly loaded author
            if (book.getAuthor() != null) {
                bookDTO.setAuthorId(book.getAuthor().getId());
            }
            listBooksDTO.add(bookDTO);
        }
        return listBooksDTO;
    }
}
