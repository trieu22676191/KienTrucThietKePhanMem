package iuh.fit.se.cau1.decorator;

class DeliveredDecorator extends OrderDecorator {
    public DeliveredDecorator(Order order) {
        super(order);
    }

    @Override
    public void performAction() {
        super.performAction();
        System.out.println("-> Delivered: Cập nhật trạng thái đơn hàng là đã giao.");
    }
}