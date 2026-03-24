package iuh.fit.se.decorator;

public class ExtendedLoanDecorator extends BorrowingDecorator {
    private int extraDays;

    public ExtendedLoanDecorator(Borrowable borrowable, int extraDays) {
        super(borrowable);
        this.extraDays = extraDays;
    }

    @Override
    public void borrow() {
        super.borrow();
        System.out.println("   ✓ Extended loan period: +" + extraDays + " days");
    }

    @Override
    public String getBorrowDetails() {
        return super.getBorrowDetails() + " | Extended by " + extraDays + " days";
    }
}
