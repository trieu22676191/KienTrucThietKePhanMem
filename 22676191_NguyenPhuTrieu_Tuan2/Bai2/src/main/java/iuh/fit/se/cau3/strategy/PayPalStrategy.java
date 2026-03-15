package iuh.fit.se.cau3.strategy;

public class PayPalStrategy implements PaymentStrategy {
    private String email;
    private double processingFee = 1.0;

    public PayPalStrategy(String email) {
        this.email = email;
    }

    @Override
    public double calculateTotal(double amount, String discountCode) {
        double discount = discountCode.equals("SAVE10") ? 10.0 : 0.0;
        return amount - discount + processingFee;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + " bằng PayPal (" + email + ")");
    }
}