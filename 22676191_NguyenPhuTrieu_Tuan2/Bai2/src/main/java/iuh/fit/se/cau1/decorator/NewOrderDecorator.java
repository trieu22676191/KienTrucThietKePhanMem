package iuh.fit.se.cau1.decorator;

public class NewOrderDecorator extends OrderDecorator {
    public NewOrderDecorator(Order order) {
        super(order);
    }

    @Override
    public void performAction() {
        super.performAction();
        System.out.println("-> NewOrder: Kiểm tra thông tin đơn hàng.");
    }
}