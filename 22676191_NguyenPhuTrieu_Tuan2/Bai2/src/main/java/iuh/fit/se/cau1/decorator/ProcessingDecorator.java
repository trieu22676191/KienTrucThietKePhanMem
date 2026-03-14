package iuh.fit.se.cau1.decorator;

public class ProcessingDecorator extends OrderDecorator {
    public ProcessingDecorator(Order order) {
        super(order);
    }

    @Override
    public void performAction() {
        super.performAction();
        System.out.println("-> Processing: Đóng gói và vận chuyển.");
    }
}
