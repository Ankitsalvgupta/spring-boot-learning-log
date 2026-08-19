package com.ankit.LibraryManagement.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {

    private Long id;

    private String title;

    private LocalDate publishedDate;

    private String isbn;

    private Long authorId;
}
