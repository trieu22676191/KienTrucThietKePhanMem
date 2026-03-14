package iuh.fit.se.cau1.state;

public class ProcessingState implements OrderState {
    @Override
    public void handleRequest(OrderContext context) {
        System.out.println("Đang đóng gói và giao hàng... Chuyển sang Đã giao.");
        context.setState(new DeliveredState());
    }

    @Override
    public String getStatusName() {
        return "Đang xử lý";
    }
}