package iuh.fit.se.cau3.decorator;

public class BasicPayment implements Payment {
    private String methodName;

    public BasicPayment(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + " thông qua " + methodName);
    }
}
