package iuh.fit.se.cau3.strategy;

public interface PaymentStrategy {
    void pay(double amount);
    double calculateTotal(double amount, String discountCode);
}
