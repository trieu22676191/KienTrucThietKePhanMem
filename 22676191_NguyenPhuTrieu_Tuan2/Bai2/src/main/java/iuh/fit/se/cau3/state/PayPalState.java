package iuh.fit.se.cau3.state;

public class PayPalState implements PaymentState {
    @Override
    public void handlePayment(PaymentContext context) {
        System.out.println("Đang kết nối tới cổng thanh toán PayPal...");
        context.setState(new ProcessingState());
    }

    @Override
    public double calculateFee(double amount) {
        return 0.5; // Phí cố định 0.5$ cho PayPal
    }
}