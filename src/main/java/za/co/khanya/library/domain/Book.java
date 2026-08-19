package za.co.khanya.library.domain;

import java.util.Objects;

/**
 * Represents a single book title in the library catalogue.
 *
 * ── TEACHING NOTES (this class is your worked example — read every line) ──
 *  • Fields are 'private' — this is ENCAPSULATION. Code outside this class
 *    cannot touch the fields directly; it must go through the getters/setters
 *    below. That means the Book object stays in control of its own data.
 *  • The CONSTRUCTOR guarantees a Book is always created in a valid state:
 *    you cannot make a Book without giving it an ISBN, title, author and a
 *    number of copies.
 *  • equals()/hashCode() are based on the ISBN, because in the real world the
 *    ISBN is what uniquely identifies a book. Two Book objects with the same
 *    ISBN are "the same book" as far as our system is concerned.
 *
 *  When you build Member.java (LIB-3), copy this structure.
 */
public class Book {

    private String isbn;            // unique identifier for the book (BR-1)
    private String title;
    private String author;
    private int totalCopies;        // how many copies the library owns
    private int availableCopies;    // how many are currently on the shelf (BR-6)

    public Book(String isbn, String title, String author, int totalCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;   // all copies available to begin with
    }

    // ── Getters and setters (the controlled doorway to the private fields) ──

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    /** Convenience method: is at least one copy on the shelf? */
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    /**
     * toString() controls how a Book looks when printed. A good toString makes
     * debugging and console output far nicer.
     */
    @Override
    public String toString() {
        return String.format("[%s] \"%s\" by %s (%d of %d available)",
                isbn, title, author, availableCopies, totalCopies);
    }

    /** Two books are equal if they have the same ISBN. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
