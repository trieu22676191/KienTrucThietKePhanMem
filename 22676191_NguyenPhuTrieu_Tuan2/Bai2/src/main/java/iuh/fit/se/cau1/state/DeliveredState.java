package iuh.fit.se.cau1.state;

public class DeliveredState implements OrderState {
    @Override
    public void handleRequest(OrderContext context) {
        System.out.println("Đơn hàng đã được giao thành công. Không thể chuyển trạng thái nữa.");
    }

    @Override
    public String getStatusName() {
        return "Đã giao";
    }
}