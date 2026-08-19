package com.ankit.LibraryManagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {

    private Long id;
    private String name;
    private String email;
    private List<String> bookTitles;
}
