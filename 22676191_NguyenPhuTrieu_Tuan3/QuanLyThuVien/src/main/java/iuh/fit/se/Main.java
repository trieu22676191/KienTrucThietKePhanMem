package iuh.fit.se;

import iuh.fit.se.decorator.Borrowable;
import iuh.fit.se.decorator.BrailleBookDecorator;
import iuh.fit.se.decorator.ExtendedLoanDecorator;
import iuh.fit.se.decorator.TranslatedBookDecorator;
import iuh.fit.se.factory.*;
import iuh.fit.se.observer.Librarian;
import iuh.fit.se.observer.Student;
import iuh.fit.se.singleton.Library;
import iuh.fit.se.strategy.SearchByAuthor;
import iuh.fit.se.strategy.SearchByCategory;
import iuh.fit.se.strategy.SearchByTitle;

public class Main {
        public static void main(String[] args) {
                System.out.println("==========================================");
                System.out.println("LIBRARY MANAGEMENT SYSTEM");
                System.out.println("==========================================\n");

                Library library = Library.getInstance();

                Librarian librarian1 = new Librarian("John");
                Librarian librarian2 = new Librarian("Sarah");
                Student student1 = new Student("Mike", "S12345");
                Student student2 = new Student("Emma", "S12346");

                library.registerObserver(librarian1);
                library.registerObserver(librarian2);
                library.registerObserver(student1);
                library.registerObserver(student2);

                System.out.println("=== 1. FACTORY METHOD PATTERN ===");
                System.out.println("Creating books using different factories:\n");

                BookFactory physicalFactory = new PhysicalBookFactory();
                BookFactory ebookFactory = new EBookFactory();
                BookFactory audioFactory = new AudioBookFactory();

                Book book1 = physicalFactory.createBook("B001", "Clean Code", "Robert Martin", "Programming", 464,
                                "Shelf A1");
                Book book2 = ebookFactory.createBook("B002", "Design Patterns", "Erich Gamma", "Programming", 5.2,
                                "EPUB");
                Book book3 = audioFactory.createBook("B003", "The Hobbit", "J.R.R. Tolkien", "Fantasy", 660,
                                "Rob Inglis");
                Book book4 = physicalFactory.createBook("B004", "The Pragmatic Programmer", "David Thomas",
                                "Programming", 352, "Shelf A2");
                Book book5 = ebookFactory.createBook("B005", "Harry Potter", "J.K. Rowling", "Fantasy", 3.8, "PDF");

                library.addBook(book1);
                library.addBook(book2);
                library.addBook(book3);
                library.addBook(book4);
                library.addBook(book5);

                System.out.println("\n=== 2. STRATEGY PATTERN ===");
                System.out.println("Demonstrating different search strategies:\n");
                
                library.setSearchStrategy(new SearchByTitle());
                System.out.println("Search by title 'Clean':");
                library.searchBooks("Clean")
                                .forEach(b -> System.out.println("   • " + b.getTitle() + " by " + b.getAuthor()));

                library.setSearchStrategy(new SearchByAuthor());
                System.out.println("\nSearch by author 'Rowling':");
                library.searchBooks("Rowling")
                                .forEach(b -> System.out.println("   • " + b.getTitle() + " by " + b.getAuthor()));

                library.setSearchStrategy(new SearchByCategory());
                System.out.println("\nSearch by category 'Fantasy':");
                library.searchBooks("Fantasy").forEach(
                                b -> System.out.println("   • " + b.getTitle() + " - Category: " + b.getCategory()));

                System.out.println("\n=== 3. DECORATOR PATTERN ===");
                System.out.println("Adding special features to borrowing:\n");

                // Borrow books with decorators
                Borrowable borrowBook1 = new ExtendedLoanDecorator((Borrowable) book1, 7);
                Borrowable borrowBook2 = new BrailleBookDecorator((Borrowable) book2);
                Borrowable borrowBook3 = new TranslatedBookDecorator((Borrowable) book3, "Vietnamese");
                Borrowable borrowBook4 = new TranslatedBookDecorator(
                                new ExtendedLoanDecorator((Borrowable) book4, 14), "Spanish");

                System.out.println("Borrowing 'Clean Code' with extended loan:");
                borrowBook1.borrow();
                System.out.println("   Details: " + borrowBook1.getBorrowDetails());

                System.out.println("\nBorrowing 'Design Patterns' with braille format:");
                borrowBook2.borrow();

                System.out.println("\nBorrowing 'The Hobbit' with Vietnamese translation:");
                borrowBook3.borrow();

                System.out.println("\nBorrowing with multiple features (Extended + Translation):");
                borrowBook4.borrow();

                System.out.println("\n=== 4. OBSERVER PATTERN ===");
                System.out.println("Notification system in action:\n");

                // Demonstrate observer pattern
                library.processOverdueBooks();

                System.out.println("\nAdding a new book triggers notification:");
                Book newBook = physicalFactory.createBook("B006", "The Art of Programming", "Donald Knuth",
                                "Computer Science", 800, "Shelf B1");
                library.addBook(newBook);

                System.out.println("\n=== 5. SINGLETON PATTERN ===");
                System.out.println("Verifying only one library instance exists:\n");

                Library anotherReference = Library.getInstance();
                System.out.println("   First instance: " + library.hashCode());
                System.out.println("   Second instance: " + anotherReference.hashCode());
                System.out.println("   Are they the same? " + (library == anotherReference));

                System.out.println("\n=== CURRENT AVAILABLE BOOKS ===");
                System.out.println("List of all available books:\n");
                library.getAvailableBooks().forEach(book -> System.out.println("   " + book.getDetails()));

                System.out.println("\n=== RETURNING A BOOK ===");
                System.out.println("Returning 'Clean Code' to library:\n");
                book1.return_();
                System.out.println("   ✓ Book returned: " + book1.getTitle());

                System.out.println("\n=== UPDATED AVAILABLE BOOKS ===");
                System.out.println("After return:\n");
                library.getAvailableBooks().forEach(book -> System.out.println("   " + book.getDetails()));

                System.out.println("\n==========================================");
                System.out.println("SYSTEM DEMONSTRATION COMPLETED");
                System.out.println("==========================================");
        }
}