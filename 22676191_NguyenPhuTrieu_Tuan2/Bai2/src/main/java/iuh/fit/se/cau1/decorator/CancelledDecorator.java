package iuh.fit.se.cau1.decorator;

public class CancelledDecorator extends OrderDecorator {
    public CancelledDecorator(Order order) {
        super(order);
    }

    @Override
    public void performAction() {
        super.performAction();
        System.out.println("-> Cancelled: Hủy đơn hàng và hoàn tiền.");
    }
}