package iuh.fit.se.singleton;

import iuh.fit.se.factory.Book;
import iuh.fit.se.observer.Observer;
import iuh.fit.se.observer.Subject;
import iuh.fit.se.strategy.SearchByTitle;
import iuh.fit.se.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements Subject {
    private static Library instance;
    private List<Book> books;
    private List<Observer> observers;
    private SearchStrategy searchStrategy;

    private Library() {
        books = new ArrayList<>();
        observers = new ArrayList<>();
        searchStrategy = new SearchByTitle(); // default strategy
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers("New book added: " + book.getTitle() + " by " + book.getAuthor());
    }

    public void removeBook(String bookId) {
        books.removeIf(book -> book.getId().equals(bookId));
        notifyObservers("Book removed: " + bookId);
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public void setSearchStrategy(SearchStrategy strategy) {
        this.searchStrategy = strategy;
    }

    public List<Book> searchBooks(String keyword) {
        return searchStrategy.search(books, keyword);
    }

    public void processOverdueBooks() {
        notifyObservers("Checking for overdue books...");
    }

    // Observer pattern methods
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
