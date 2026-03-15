package iuh.fit.se.cau3.state;

public interface PaymentState {
    void handlePayment(PaymentContext context);
    double calculateFee(double amount);
}