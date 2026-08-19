package za.co.khanya.library;

import za.co.khanya.library.repository.BookRepository;
import za.co.khanya.library.repository.InMemoryBookRepository;
import za.co.khanya.library.service.BookService;
import za.co.khanya.library.ui.ConsoleMenu;

/**
 * The front door of the application — every Java program starts at main().
 *
 * Here we "wire up" the application by hand:
 *   1. create the repository (where books are stored),
 *   2. create the service (which holds the business logic), handing it the repo,
 *   3. create the menu (the UI), handing it the service,
 *   4. start the menu.
 *
 * This wiring-by-hand is exactly what Spring's dependency injection will do for
 * you automatically in Phase 2. Doing it manually once makes the framework
 * version make sense.
 */
public class LibraryConsoleApplication {

    public static void main(String[] args) {
        // 1 & 2: storage + business logic
        BookRepository bookRepository = new InMemoryBookRepository();
        BookService bookService = new BookService(bookRepository);

        // A little seed data so there's something to look at on first run.
        seedSampleBooks(bookService);

        // TODO (LIB-5/6): when you build the member and loan features, create
        //   MemberRepository/MemberService and LoanRepository/LoanService here,
        //   and pass them into the ConsoleMenu constructor as well.

        // 3 & 4: the UI, then run it
        ConsoleMenu menu = new ConsoleMenu(bookService);
        menu.run();
    }

    private static void seedSampleBooks(BookService bookService) {
        bookService.addBook("978-0134685991", "Effective Java", "Joshua Bloch", 3);
        bookService.addBook("978-0596009205", "Head First Java", "Kathy Sierra", 5);
        bookService.addBook("978-0132350884", "Clean Code", "Robert C. Martin", 2);
    }
}
