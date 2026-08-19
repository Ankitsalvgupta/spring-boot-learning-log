package com.ankit.LibraryManagement.repository;

import com.ankit.LibraryManagement.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.id = :id")
    Optional<Author> findByIdWithBooks(@Param("id") Long id);


    @Query("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books WHERE a.name LIKE %:name%")
    Optional<Author> findAuthorByName(@Param("name") String name);

    @EntityGraph(attributePaths = "books")
    Page<Author> findAll(Pageable pageable);
}
