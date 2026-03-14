package iuh.fit.se.cau1.decorator;

abstract class OrderDecorator implements Order {
    protected Order order;

    public OrderDecorator(Order order) {
        this.order = order;
    }

    @Override
    public void performAction() {
        order.performAction();
    }
}