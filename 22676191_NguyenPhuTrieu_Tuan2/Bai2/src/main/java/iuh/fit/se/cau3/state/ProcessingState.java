package iuh.fit.se.cau3.state;

class ProcessingState implements PaymentState {
    @Override
    public void handlePayment(PaymentContext context) {
        System.out.println("Giao dịch đã được xử lý thành công!");
    }

    @Override
    public double calculateFee(double amount) {
        return 0;
    }
}
