package iuh.fit.se.cau3.decorator;

public abstract class PaymentDecorator implements Payment {
    protected Payment wrappedPayment;

    public PaymentDecorator(Payment payment) {
        this.wrappedPayment = payment;
    }

    @Override
    public void pay(double amount) {
        wrappedPayment.pay(amount);
    }
}
