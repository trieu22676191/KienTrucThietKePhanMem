package iuh.fit.se.cau3.decorator;

public class DiscountDecorator extends PaymentDecorator {
    private double discountAmount;

    public DiscountDecorator(Payment payment, double discountAmount) {
        super(payment);
        this.discountAmount = discountAmount;
    }

    @Override
    public void pay(double amount) {
        double total = amount - discountAmount;
        System.out.println("Đã áp dụng giảm giá: " + discountAmount);
        super.pay(total);
    }
}
