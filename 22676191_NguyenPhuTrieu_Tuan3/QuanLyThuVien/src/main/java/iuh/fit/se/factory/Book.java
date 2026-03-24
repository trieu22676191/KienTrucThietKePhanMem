package iuh.fit.se.factory;

import iuh.fit.se.decorator.Borrowable;

import java.util.Date;

public abstract class Book implements Borrowable {
    protected String id;
    protected String title;
    protected String author;
    protected String category;
    protected boolean isAvailable;
    protected String borrowDetails;

    public Book(String id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
        this.borrowDetails = "";
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return isAvailable; }

    public abstract String getDetails();

    @Override
    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            borrowDetails = "Borrowed on: " + new Date();
        } else {
            throw new IllegalStateException("Book is not available");
        }
    }

    @Override
    public void return_() {
        isAvailable = true;
        borrowDetails = "Returned on: " + new Date();
    }

    @Override
    public String getBorrowDetails() {
        return borrowDetails;
    }
}
