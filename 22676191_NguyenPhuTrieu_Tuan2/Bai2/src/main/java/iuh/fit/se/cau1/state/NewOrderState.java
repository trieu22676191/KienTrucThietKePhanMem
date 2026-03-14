package iuh.fit.se.cau1.state;

public class NewOrderState implements OrderState {
    @Override
    public void handleRequest(OrderContext context) {
        System.out.println("Đang xác nhận đơn hàng... Chuyển sang Đang xử lý.");
        context.setState(new ProcessingState());
    }

    @Override
    public String getStatusName() {
        return "Mới tạo";
    }
}