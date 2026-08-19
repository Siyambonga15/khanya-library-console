package za.co.khanya.library.service;

import za.co.khanya.library.domain.Book;
import za.co.khanya.library.repository.BookRepository;
import za.co.khanya.library.service.exception.BookNotFoundException;

import java.util.List;

/**
 * Holds the BUSINESS LOGIC for books. This is the "service layer".
 *
 * ── Why have a service at all? Two big reasons: ──
 *  1. It keeps business rules in ONE findable place (not scattered in the UI).
 *  2. It sits between the UI and the repository, so neither needs to know the
 *     other's details.
 *
 * Notice the constructor takes a BookRepository. The service doesn't create its
 * own repository — it's handed one from outside. This is called DEPENDENCY
 * INJECTION, and we're doing it by hand here. In Phase 2, Spring does it for you
 * automatically. Same idea, less typing.
 *
 * When you build MemberService and LoanService (LIB-6), copy this structure.
 */
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /** Add a new book. Rejects a duplicate ISBN (business rule BR-1). */
    public Book addBook(String isbn, String title, String author, int totalCopies) {
        if (bookRepository.findByIsbn(isbn).isPresent()) {
            throw new IllegalArgumentException("A book with ISBN " + isbn + " already exists.");
        }
        Book book = new Book(isbn, title, author, totalCopies);
        return bookRepository.save(book);
    }

    /** Find one book by ISBN, or throw if it doesn't exist. */
    public Book getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    /** List every book in the catalogue. */
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    /** Update a book's details. Throws if the book doesn't exist. */
    public Book updateBook(String isbn, String title, String author, int totalCopies) {
        Book book = getByIsbn(isbn);            // throws BookNotFoundException if missing
        book.setTitle(title);
        book.setAuthor(author);
        book.setTotalCopies(totalCopies);
        // Keep the available count sensible: never above the new total.
        if (book.getAvailableCopies() > totalCopies) {
            book.setAvailableCopies(totalCopies);
        }
        return bookRepository.save(book);
    }

    /** Remove a book. Throws if it doesn't exist. */
    public void removeBook(String isbn) {
        getByIsbn(isbn);                        // throws if missing
        bookRepository.deleteByIsbn(isbn);
    }
}
