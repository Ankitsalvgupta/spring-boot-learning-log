package com.ankit.LibraryManagement.dto;

import lombok.Data;

@Data
public class BookRequestDTO {

    private String title;
    private String isbn;
    private Long authorId;
}
