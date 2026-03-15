package iuh.fit.se.cau3.decorator;

public class ProcessingFeeDecorator extends PaymentDecorator {
    private double fee;

    public ProcessingFeeDecorator(Payment payment, double fee) {
        super(payment);
        this.fee = fee;
    }

    @Override
    public void pay(double amount) {
        double total = amount + fee;
        System.out.println("Đã thêm phí xử lý: " + fee);
        super.pay(total);
    }
}
