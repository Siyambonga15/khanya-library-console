package za.co.khanya.library.service.exception;

/**
 * Thrown when we look for a book that doesn't exist.
 *
 * ── This is your example exception. In LIB-6 you'll create the others by
 *    copying this pattern: ──
 *      • MemberNotFoundException
 *      • BookNotAvailableException
 *      • BorrowingLimitExceededException
 *
 * It extends RuntimeException (an "unchecked" exception), which means callers
 * aren't forced to wrap every call in try/catch — but they CAN catch it where
 * it makes sense (the console menu does exactly that, to show a friendly
 * message instead of crashing).
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super("No book found with ISBN: " + isbn);
    }
}
