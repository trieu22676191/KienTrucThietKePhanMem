package iuh.fit.se.cau3.state;

public class CreditCardState implements PaymentState {
    @Override
    public void handlePayment(PaymentContext context) {
        System.out.println("Đang xử lý thanh toán qua Thẻ tín dụng...");
        context.setState(new ProcessingState()); // Chuyển sang trạng thái hoàn tất
    }

    @Override
    public double calculateFee(double amount) {
        return amount * 0.02; // Phí 2% cho thẻ tín dụng
    }
}