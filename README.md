# Phase 1 — Khanya Library Console (Core Java)

A library management system built with **core Java only** — no frameworks. This is
where you learn how Java itself works before Spring Boot enters the picture in
Phase 2.

## What's here

Only **one complete slice is provided: `Book`.** It's your worked example — a
reference for *how we build things here*. Everything else, you build yourself,
using that example and the business requirements as your guide.

The project is organised in **layers**, each in its own package. The `Book`
classes show you where each kind of thing lives:

```
za.co.khanya.library
├── LibraryConsoleApplication   ← main(): assembles the app and runs it (Book wired)
├── domain/                     ← the "things": Book  (you'll add Member, Loan, and a type enum)
├── repository/                 ← storage: BookRepository + InMemoryBookRepository
│                                  (you'll add the Member/Loan equivalents)
├── service/                    ← business logic: BookService
│   └── exception/              ← BookNotFoundException  (you'll add the others)
└── ui/                         ← ConsoleMenu (Book actions wired; the rest are yours)
```

**You create the Member and Loan classes yourself** — their fields come from
`docs/01-business-analysis/data-dictionary.md`, and their structure follows the
`Book` pattern. Deriving the design from the requirements (rather than being handed
stubs) is deliberate: it's exactly what the job is.

## The tickets for this phase
- **Sprint 1:** LIB-1 … LIB-4 — `docs/02-agile-delivery/tickets/sprint-1.md`
- **Sprint 2:** LIB-5 … LIB-8 — `docs/02-agile-delivery/tickets/sprint-2.md`

Always start from the ticket, not the code.

## How to run it

**In IntelliJ:** open this folder, then run `LibraryConsoleApplication` (green ▶).

**Command line with Maven:**
```bash
mvn compile
mvn exec:java
# or build a runnable jar:
mvn package
java -jar target/khanya-library-console.jar
```

**Command line without Maven (plain JDK):**
```bash
javac -d target/classes $(find src/main/java -name "*.java")
java -cp target/classes za.co.khanya.library.LibraryConsoleApplication
```

On first run you'll see a menu with three sample books already loaded. The Book
options work; the Member and Loan options print a note telling you which ticket
builds them — that's your cue.

## The big picture
Everything you build here — the repository interfaces, the service layer, the
domain objects — comes back in Phase 2, where Spring Boot does much of the heavy
lifting for you. Build it well here and Phase 2 will feel like a natural next step.
