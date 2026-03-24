package iuh.fit.se.decorator;

public class BrailleBookDecorator extends BorrowingDecorator {
    private String brailleFormat;

    public BrailleBookDecorator(Borrowable borrowable) {
        super(borrowable);
        this.brailleFormat = "Braille Grade 2";
    }

    @Override
    public void borrow() {
        super.borrow();
        System.out.println("   ✓ Braille version requested: " + brailleFormat);
    }

    @Override
    public String getBorrowDetails() {
        return super.getBorrowDetails() + " | Braille format: " + brailleFormat;
    }
}
