package iuh.fit.se.cau3.strategy;

public class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;
    private double processingFee = 2.0;

    public CreditCardStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public double calculateTotal(double amount, String discountCode) {
        double discount = discountCode.equals("SAVE10") ? 10.0 : 0.0;
        return amount - discount + processingFee;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + " bằng Thẻ tín dụng (" + cardNumber + ")");
    }
}