package za.co.khanya.library.repository;

import za.co.khanya.library.domain.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An in-memory implementation of BookRepository, backed by a Map.
 *
 * The Map's KEY is the book's ISBN; the VALUE is the Book object. A Map gives us
 * fast lookup by ISBN — exactly what findByIsbn needs.
 *
 * "In-memory" means the data lives only in RAM while the program runs; close the
 * app and it's gone. That's fine for Phase 1 (learning core Java). In Phase 2 a
 * real database takes over this job.
 *
 * When you build InMemoryMemberRepository (LIB-5), copy this structure.
 */
public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> booksByIsbn = new HashMap<>();

    @Override
    public Book save(Book book) {
        // put() adds a new entry, or replaces the existing one with the same key.
        booksByIsbn.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        // ofNullable() gives an empty Optional if the key isn't present.
        return Optional.ofNullable(booksByIsbn.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        // Return a new list (a copy) so callers can't modify our internal store.
        return new ArrayList<>(booksByIsbn.values());
    }

    @Override
    public void deleteByIsbn(String isbn) {
        booksByIsbn.remove(isbn);
    }
}
