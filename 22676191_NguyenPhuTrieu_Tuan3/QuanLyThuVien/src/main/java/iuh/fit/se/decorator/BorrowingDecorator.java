package iuh.fit.se.decorator;

public abstract class BorrowingDecorator implements Borrowable {
    protected Borrowable borrowable;

    public BorrowingDecorator(Borrowable borrowable) {
        this.borrowable = borrowable;
    }

    @Override
    public void borrow() {
        borrowable.borrow();
    }

    @Override
    public void return_() {
        borrowable.return_();
    }

    @Override
    public String getBorrowDetails() {
        return borrowable.getBorrowDetails();
    }
}
