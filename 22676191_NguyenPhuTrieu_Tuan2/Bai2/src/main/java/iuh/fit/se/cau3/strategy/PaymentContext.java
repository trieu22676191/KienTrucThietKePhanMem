package iuh.fit.se.cau3.strategy;

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount, String discountCode) {
        if (strategy == null) {
            System.out.println("Vui lòng chọn phương thức thanh toán!");
            return;
        }
        double finalAmount = strategy.calculateTotal(amount, discountCode);
        strategy.pay(finalAmount);
    }
}
