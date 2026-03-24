package iuh.fit.se.decorator;

public class TranslatedBookDecorator extends BorrowingDecorator {
    private String language;

    public TranslatedBookDecorator(Borrowable borrowable, String language) {
        super(borrowable);
        this.language = language;
    }

    @Override
    public void borrow() {
        super.borrow();
        System.out.println("   ✓ Translation requested: " + language);
    }

    @Override
    public String getBorrowDetails() {
        return super.getBorrowDetails() + " | Translated to: " + language;
    }
}
