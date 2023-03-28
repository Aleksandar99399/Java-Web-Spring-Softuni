package com.softuni.web;

import com.softuni.model.Author;
import com.softuni.model.Book;
import com.softuni.repositories.AuthorRepository;
import com.softuni.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController implements AuthorNameSpace {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<Book>> getAuthorBooks(@PathVariable Long authorId) {
        Optional<Author> theAuthor = authorRepository.findById(authorId);
        return theAuthor
                .map(Author::getBooks)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long authorId,
                                        @PathVariable Long bookId) {

        Optional<Book> book = this.bookRepository.findById(bookId);

        return book
                .filter(b -> b.getAuthor().getId() == authorId)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
