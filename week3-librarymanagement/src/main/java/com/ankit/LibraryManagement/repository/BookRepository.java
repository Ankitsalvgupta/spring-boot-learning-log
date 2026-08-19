package com.ankit.LibraryManagement.repository;

import com.ankit.LibraryManagement.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title% ORDER BY b.title ASC")
    List<Book> findBooksByTitleSorted(@Param("title") String title);

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.publishedDate > :date ORDER BY b.publishedDate DESC")
    List<Book> findBooksPublishedAfterWithAuthor(@Param("date") LocalDate date);

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.author.id = :authorId ORDER BY b.publishedDate DESC")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);

    @EntityGraph(attributePaths = "author")
    Page<Book> findAll(Pageable pageable);
}
