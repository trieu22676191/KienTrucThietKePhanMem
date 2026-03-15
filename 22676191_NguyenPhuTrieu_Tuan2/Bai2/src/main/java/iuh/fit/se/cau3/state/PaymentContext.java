package iuh.fit.se.cau3.state;

public class PaymentContext {
    private PaymentState currentState;
    private double amount;
    private double discount = 0;

    public PaymentContext(double amount) {
        this.amount = amount;
    }

    public void setState(PaymentState state) {
        this.currentState = state;
    }

    public void applyDiscount(String code) {
        if ("GIAMGIA10".equals(code)) {
            this.discount = 10.0;
            System.out.println("Đã áp dụng mã giảm giá 10$");
        } else {
            System.out.println("Mã giảm giá không hợp lệ");
        }
    }

    public void process() {
        if (currentState != null) {
            double fee = currentState.calculateFee(this.amount);
            double total = this.amount + fee - this.discount;

            System.out.println("--- Thông tin hóa đơn ---");
            System.out.println("Số tiền gốc: " + amount);
            System.out.println("Phí xử lý: " + fee);
            System.out.println("Giảm giá: " + discount);
            System.out.println("Tổng thanh toán: " + total);

            currentState.handlePayment(this);
        } else {
            System.out.println("Vui lòng chọn phương thức thanh toán!");
        }
    }
}
