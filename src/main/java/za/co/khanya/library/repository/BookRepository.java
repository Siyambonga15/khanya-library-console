package za.co.khanya.library.repository;

import za.co.khanya.library.domain.Book;

import java.util.List;
import java.util.Optional;

/**
 * The doorway to stored books.
 *
 * ── REMEMBER THIS FILE. It is the setup for the biggest "aha" of the whole
 *    programme. ──
 *
 * Notice this is an INTERFACE. It describes WHAT storage operations exist
 * (save a book, find one, list them, delete one) but says nothing about HOW
 * they work. In Phase 1 we back it with a simple in-memory HashMap
 * (see InMemoryBookRepository).
 *
 * In Phase 2, Spring Data JPA will implement this exact *kind* of interface for
 * you automatically, against a real database — you'll write the interface and
 * get all the operations for free. You built the manual version first, so that
 * "magic" won't be magic; it'll be something you already understand.
 *
 * Note 'Optional<Book>': it means "maybe a book, maybe nothing". It's safer
 * than returning null, because it forces the caller to handle the "not found"
 * case on purpose.
 */
public interface BookRepository {

    Book save(Book book);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    void deleteByIsbn(String isbn);
}
