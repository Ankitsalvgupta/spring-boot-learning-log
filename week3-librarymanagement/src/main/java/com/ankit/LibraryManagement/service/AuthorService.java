package com.ankit.LibraryManagement.service;

import com.ankit.LibraryManagement.dto.AuthorDTO;
import com.ankit.LibraryManagement.dto.AuthorRequestDTO;
import com.ankit.LibraryManagement.entity.Author;
import com.ankit.LibraryManagement.entity.Book;
import com.ankit.LibraryManagement.exception.ResourceNotFoundException;
import com.ankit.LibraryManagement.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    private final int PAGE_SIZE = 5;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public void createAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = modelMapper.map(authorRequestDTO, Author.class);
        authorRepository.save(author);
    }

    @Transactional
    public AuthorDTO findById(Long id) {
        Author author = authorRepository.findByIdWithBooks(id).orElseThrow(() -> new ResourceNotFoundException("Not found with id: " + id));
        AuthorDTO dto = modelMapper.map(author, AuthorDTO.class); // maps id, name, email — NOT bookTitles correctly

        List<String> titles = author.getBooks().stream()
                .map(Book::getTitle) //.map(book -> book.getTitle())
                .toList();
        dto.setBookTitles(titles);

        // what stream is doing:
        // List<String> titles = new ArrayList<>();
        //for (Book book : author.getBooks()) {
        //    titles.add(book.getTitle());
        //}

        return dto;
    }

    @Transactional
    public List<AuthorDTO> findAll(String sortBy, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(sortBy));
        List<Author> author = authorRepository.findAll(pageable).getContent();
        List<AuthorDTO> authorDTOs = new ArrayList<>();
        for(Author auth : author){
            AuthorDTO authorDTO = modelMapper.map(auth, AuthorDTO.class);
            List<String> titles = auth.getBooks().stream()
                    .map(Book::getTitle) //.map(book -> book.getTitle())
                    .toList();
            authorDTO.setBookTitles(titles);
            authorDTOs.add(authorDTO);
        }
        return authorDTOs;
    }

    @Transactional
    public AuthorDTO update(AuthorRequestDTO authorRequestDTO, Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found with id: " + id));
        author.setName(authorRequestDTO.getName());
        author.setEmail(authorRequestDTO.getEmail());

        Author updated = authorRepository.save(author);

        AuthorDTO dto = modelMapper.map(updated, AuthorDTO.class);
        List<String> titles = updated.getBooks().stream()
                .map(Book::getTitle)
                .toList();
        dto.setBookTitles(titles);
        return dto;
    }

    @Transactional
    public void deleteById(Long id) {
        authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found with id: " + id));
        authorRepository.deleteById(id);
    }

    @Transactional
    public AuthorDTO findAuthorByName(String name) {
        Author author = authorRepository.findAuthorByName(name).orElseThrow(()-> new ResourceNotFoundException("Author Not Found with name: " + name));
        AuthorDTO authorDTO = modelMapper.map(author, AuthorDTO.class);
        return authorDTO;
    }
}
