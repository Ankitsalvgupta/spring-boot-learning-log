package com.ankit.LibraryManagement.controller;

import com.ankit.LibraryManagement.dto.AuthorDTO;
import com.ankit.LibraryManagement.dto.AuthorRequestDTO;
import com.ankit.LibraryManagement.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        authorService.createAuthor(authorRequestDTO);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO findById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @GetMapping("/findAuthor/{name}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO findAuthorByName(@PathVariable String name) {
        return authorService.findAuthorByName(name);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> findAll(@RequestParam(defaultValue = "id") String sortBy,
                                   @RequestParam(defaultValue = "0") Integer pageNo) {
        return authorService.findAll(sortBy, pageNo);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO update(@RequestBody AuthorRequestDTO authorRequestDTO, @PathVariable Long id){
        return authorService.update(authorRequestDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
