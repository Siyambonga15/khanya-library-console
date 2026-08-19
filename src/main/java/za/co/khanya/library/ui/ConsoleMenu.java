package za.co.khanya.library.ui;

import za.co.khanya.library.domain.Book;
import za.co.khanya.library.service.BookService;

import java.util.List;
import java.util.Scanner;

/**
 * The console user interface. It shows a menu, reads the librarian's choice,
 * calls the right service, and prints the result.
 *
 * ── THE MOST IMPORTANT RULE IN THIS FILE ──
 *  This class is THIN. It only reads input and prints output. It contains NO
 *  business rules. Deciding whether a book can be borrowed, or whether a member
 *  is over their limit, is the SERVICE's job — never the menu's. Keep it that way.
 *
 *  The Book options below are your worked example. When you build the Member and
 *  Loan features (LIB-5..LIB-7), replace the "not yet implemented" placeholders
 *  with real calls to your new services, following the Book methods here.
 */
public class ConsoleMenu {

    private final BookService bookService;
    private final Scanner scanner = new Scanner(System.in);

    // TODO (LIB-6/7): add MemberService and LoanService fields here, and accept
    //                 them in the constructor, once you've built them.
    public ConsoleMenu(BookService bookService) {
        this.bookService = bookService;
    }

    public void run() {
        System.out.println("========================================");
        System.out.println("   Khanya Community Library System");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addBook();
                case "2" -> viewCatalogue();
                case "3" -> findBook();
                case "4" -> removeBook();
                case "5" -> notYetImplemented("LIB-5/7", "Register a member");
                case "6" -> notYetImplemented("LIB-7", "View members");
                case "7" -> notYetImplemented("LIB-6/7", "Lend a book");
                case "8" -> notYetImplemented("LIB-6/7", "Return a book");
                case "9" -> notYetImplemented("LIB-7", "View active loans");
                case "0" -> running = false;
                default -> System.out.println(
                        ">> Unknown option: '" + choice + "'. Please choose a number from the menu.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("---- Main Menu ----");
        System.out.println(" 1. Add a book");
        System.out.println(" 2. View catalogue");
        System.out.println(" 3. Find a book by ISBN");
        System.out.println(" 4. Remove a book");
        System.out.println(" 5. Register a member      (to build: LIB-5/7)");
        System.out.println(" 6. View members           (to build: LIB-7)");
        System.out.println(" 7. Lend a book            (to build: LIB-6/7)");
        System.out.println(" 8. Return a book          (to build: LIB-6/7)");
        System.out.println(" 9. View active loans      (to build: LIB-7)");
        System.out.println(" 0. Exit");
        System.out.print("Choose an option: ");
    }

    // ─────────────── Book actions (worked example) ───────────────

    private void addBook() {
        try {
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine().trim();
            System.out.print("Title: ");
            String title = scanner.nextLine().trim();
            System.out.print("Author: ");
            String author = scanner.nextLine().trim();
            int copies = readInt("Number of copies: ");

            Book book = bookService.addBook(isbn, title, author, copies);
            System.out.println(">> Added: " + book);
        } catch (Exception e) {
            // Any business error (e.g. duplicate ISBN) becomes a friendly message,
            // not a crash. This is why we catch here, at the UI boundary.
            System.out.println(">> Could not add book: " + e.getMessage());
        }
    }

    private void viewCatalogue() {
        List<Book> books = bookService.listAll();
        if (books.isEmpty()) {
            System.out.println(">> The catalogue is empty.");
            return;
        }
        System.out.println(">> Catalogue (" + books.size() + " books):");
        for (Book book : books) {
            System.out.println("   " + book);
        }
    }

    private void findBook() {
        try {
            System.out.print("ISBN to find: ");
            String isbn = scanner.nextLine().trim();
            Book book = bookService.getByIsbn(isbn);
            System.out.println(">> Found: " + book);
        } catch (Exception e) {
            System.out.println(">> " + e.getMessage());
        }
    }

    private void removeBook() {
        try {
            System.out.print("ISBN to remove: ");
            String isbn = scanner.nextLine().trim();
            bookService.removeBook(isbn);
            System.out.println(">> Removed book with ISBN " + isbn);
        } catch (Exception e) {
            System.out.println(">> " + e.getMessage());
        }
    }

    // ─────────────── Helpers ───────────────

    /**
     * Reads a whole number safely. If the user types something that isn't a
     * number, it asks again instead of crashing (see LIB-8).
     */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(">> '" + line + "' is not a valid number. Please try again.");
            }
        }
    }

    private void notYetImplemented(String ticket, String feature) {
        System.out.println(">> '" + feature + "' is not yet implemented -- that's your job!");
        System.out.println(">> See ticket " + ticket + ". Follow the Book actions in this file as your example.");
    }
}
