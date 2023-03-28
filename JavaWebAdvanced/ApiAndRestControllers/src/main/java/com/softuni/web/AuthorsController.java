package com.softuni.web;

import com.softuni.model.Author;
import com.softuni.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorsController implements AuthorNameSpace {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorsController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long authorId) {
        Optional<Author> theAuthor = this.authorRepository.findById(authorId);
        return theAuthor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Author> create(UriComponentsBuilder uriBuilder,
                                         @RequestBody Author author) {

        Author newAuthor = authorRepository.save(author);
        return ResponseEntity
                .created(uriBuilder.path("/authors/{authorId}").buildAndExpand(newAuthor.getId()).toUri()).build();
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Author> delete(@PathVariable Long authorId) {
        authorRepository.deleteById(authorId);
        return ResponseEntity.noContent().build();
    }
}
